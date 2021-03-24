package cj.netos.fission.service;

import cj.netos.fission.IFissionAccountService;
import cj.netos.fission.IOutAbsorbActivityController;
import cj.netos.fission.model.AbsorbOutRecord;
import cj.netos.fission.model.FissionAccount;
import cj.netos.fission.util.CashierUtils;
import cj.netos.fission.util.IdWorker;
import cj.netos.rabbitmq.IRabbitMQProducer;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import com.rabbitmq.client.AMQP;

import java.util.HashMap;

@CjService(name = "outAbsorbActivityController")
public class OutAbsorbActivityController implements IOutAbsorbActivityController {
    @CjServiceRef(refByName = "@.rabbitmq.producer.out-absorb-to-robot")
    IRabbitMQProducer rabbitMQProducer;
    @CjServiceRef(refByName = "records.fissionRecordService")
    IFissionRecordService fissionRecordService;
    @CjServiceRef
    IFissionAccountService fissionAccountService;

    @Override
    public AbsorbOutRecord receipt() {
        FissionAccount account = fissionAccountService.getAndInitAbsorb();
        AbsorbOutRecord record = new AbsorbOutRecord();
        record.setAmount(account.getBalance());
        record.setCtime(CashierUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        record.setCurrency("CNY");
        record.setState(200);
        record.setMessage("ok");
        record.setSn(new IdWorker().nextId());
        record.setState(0);
        record.setPerson("absorbRobot@system.netos");
        record.setNickName("洇取机器");
        fissionRecordService.outAbsorb(record);
        return record;
    }

    @Override
    public void out(AbsorbOutRecord outRecord) throws CircuitException {
        fissionAccountService.outAbsorb(outRecord);
        fissionRecordService.outAbsorbSucceed(outRecord);
    }


    @Override
    public void error(AbsorbOutRecord outRecord, int status, String message) {
        if (outRecord == null) {
            return;
        }
        fissionRecordService.outAbsorbError(outRecord, status, message);
    }


    @Override
    public void pushToRobot(AbsorbOutRecord outRecord) throws CircuitException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .type("/robot/trade/settle.mq")
                .headers(new HashMap() {
                    {
                        put("command", "outAbsorb");
                    }
                }).build();
        byte[] body = new Gson().toJson(outRecord).getBytes();
        rabbitMQProducer.publish("robot", properties, body);
    }
}
