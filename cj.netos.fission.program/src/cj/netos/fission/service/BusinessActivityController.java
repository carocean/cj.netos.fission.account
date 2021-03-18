package cj.netos.fission.service;

import cj.netos.fission.IBusinessActivityController;
import cj.netos.fission.IFissionAccountService;
import cj.netos.fission.model.BusinessInRecord;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;

@CjService(name = "businessActivityController")
public class BusinessActivityController implements IBusinessActivityController {
    @CjServiceRef(refByName = "records.fissionRecordService")
    IFissionRecordService fissionRecordService;
    @CjServiceRef
    IFissionAccountService fissionAccountService;
    @Override
    public void inBusiness(BusinessInRecord record) {
        try {
            fissionAccountService.inBusiness(record);
            fissionRecordService.inBusinessSucceed(record);
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
            fissionRecordService.inBusinessError(record, status, message);
            throw e;
        }
    }

    @Override
    public void error(BusinessInRecord record, int status, String msg) {
        fissionRecordService.inBusinessError(record, status, msg);
    }
}
