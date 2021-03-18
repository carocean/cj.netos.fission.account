package cj.netos.fission.cmd;

import cj.netos.fission.IIncomeActivityController;
import cj.netos.fission.model.IncomeRecord;
import cj.netos.rabbitmq.CjConsumer;
import cj.netos.rabbitmq.RabbitMQException;
import cj.netos.rabbitmq.RetryCommandException;
import cj.netos.rabbitmq.consumer.IConsumerCommand;
import cj.studio.ecm.CJSystem;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.ultimate.gson2.com.google.gson.Gson;
import cj.ultimate.util.StringUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

@CjConsumer(name = "fission-mf-account-on-withdraw")
@CjService(name = "/fission/account.mhub#income")
public class OnIncomeCommand implements IConsumerCommand {
    @CjServiceRef
    IIncomeActivityController incomeActivityController;
    @Override
    public void command(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws RabbitMQException, RetryCommandException, IOException {
//        LongString person = (LongString) properties.getHeaders().get("person");
        String json = new String(body);
        IncomeRecord record = new Gson().fromJson(json, IncomeRecord.class);
        //每个用户一个锁
        try {
            incomeActivityController.income(record);
        } catch (Exception e) {
            String msg = e.getMessage();
            if (!StringUtil.isEmpty(msg)) {
                msg = msg.substring(0, msg.length() > 200 ? 200 : msg.length());
            }
            incomeActivityController.error(record,500,msg);
            CJSystem.logging().error(getClass(), e);
            CircuitException ce = CircuitException.search(e);
            if (ce == null) {
                throw new RabbitMQException(ce.getStatus(), e);
            }
            throw new RabbitMQException("500", e);
        }
    }
}
