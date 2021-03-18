package cj.netos.fission.ports;

import cj.netos.fission.model.BusinessInRecord;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.List;

@CjOpenports(usage = "裂变游戏账户服务")
public interface IFissionRecordPorts extends IOpenportService {

    @CjOpenport(usage = "获取所有账户")
    List<BusinessInRecord> pageBusinessInRecord(ISecuritySession securitySession,
                                                @CjOpenportParameter(usage = "分账状态,当为-1时表示全部；0为未分账；1为已分账", name = "shuntState") int shuntState,
                                                @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                                @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;
}
