package cj.netos.fission.ports;

import cj.netos.fission.model.BusinessInRecord;
import cj.netos.fission.service.IFissionRecordService;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;
@CjService(name = "/record.ports")
public class FissionRecordPorts implements IFissionRecordPorts {
    @CjServiceRef(refByName = "records.fissionRecordService")
    IFissionRecordService fissionRecordService;
    @Override
    public List<BusinessInRecord> pageBusinessInRecord(ISecuritySession securitySession, int shuntState, int limit, long offset) throws CircuitException {
        return fissionRecordService.pageBusinessInRecord(shuntState,limit,offset);
    }
}
