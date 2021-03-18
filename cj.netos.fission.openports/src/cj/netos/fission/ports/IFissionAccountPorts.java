package cj.netos.fission.ports;

import cj.netos.fission.model.BusinessBill;
import cj.netos.fission.model.BusinessInRecord;
import cj.netos.fission.model.FissionAccount;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.IOpenportService;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.annotations.CjOpenport;
import cj.studio.openport.annotations.CjOpenportParameter;
import cj.studio.openport.annotations.CjOpenports;

import java.util.List;

@CjOpenports(usage = "裂变游戏账户服务")
public interface IFissionAccountPorts extends IOpenportService {
    @CjOpenport(usage = "获取营业账户")
    FissionAccount getBusinessAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取收入账户")
    FissionAccount getIncomeAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取洇金账户")
    FissionAccount getAbsorbAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "获取所有账户")
    List<FissionAccount> listAccount(ISecuritySession securitySession
    ) throws CircuitException;

    @CjOpenport(usage = "分页账单")
    List<BusinessBill> pageBusinessBill(ISecuritySession securitySession,
                                            @CjOpenportParameter(usage = "- 0 bussiness账户的进账\n" +
                                                    "- 1 bussiness账户的转出账\n" +
                                                    "- 2 bussiness账户的商户退款", name = "order") int order,
                                            @CjOpenportParameter(usage = "页大小", name = "limit") int limit,
                                            @CjOpenportParameter(usage = "偏移", name = "offset") long offset
    ) throws CircuitException;
}
