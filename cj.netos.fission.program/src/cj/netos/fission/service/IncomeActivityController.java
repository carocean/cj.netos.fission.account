package cj.netos.fission.service;

import cj.netos.fission.IFissionAccountService;
import cj.netos.fission.IIncomeActivityController;
import cj.netos.fission.model.IncomeRecord;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;

@CjService(name = "incomeActivityController")
public class IncomeActivityController implements IIncomeActivityController {
    @CjServiceRef(refByName = "records.fissionRecordService")
    IFissionRecordService fissionRecordService;
    @CjServiceRef
    IFissionAccountService fissionAccountService;


    @Override
    public void income(IncomeRecord record) {
        try {
            fissionAccountService.income(record);
            fissionRecordService.incomeSucceed(record);
        } catch (Exception e) {
            CircuitException ce = CircuitException.search(e);
            if (ce == null) {
                ce = new CircuitException("500", e);
            }
            int status = Integer.valueOf(ce.getStatus());
            String message = ce.getMessage();
            if (message.length() > 40) {
                message = message.substring(0, 40);
            }
            message.replace("\n", "");
            fissionRecordService.incomeError(record, status, message);
            throw e;
        }
    }

    @Override
    public void error(IncomeRecord record, int status, String msg) {
        fissionRecordService.incomeError(record, status, msg);
    }
}
