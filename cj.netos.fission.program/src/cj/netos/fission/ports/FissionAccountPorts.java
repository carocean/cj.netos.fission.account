package cj.netos.fission.ports;

import cj.netos.fission.IFissionAccountService;
import cj.netos.fission.model.BusinessBill;
import cj.netos.fission.model.FissionAccount;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;

import java.util.List;

@CjService(name = "/account.ports")
public class FissionAccountPorts implements IFissionAccountPorts {
    @CjServiceRef
    IFissionAccountService fissionAccountService;
    void checkRights(ISecuritySession securitySession) throws CircuitException {
        if (!securitySession.roleIn("platform:administrators")) {
            throw new CircuitException("800", "拒绝访问");
        }
    }
    @Override
    public FissionAccount getBusinessAccount(ISecuritySession securitySession) throws CircuitException {
        checkRights(securitySession);
        return fissionAccountService.getAndInitBusiness();
    }

    @Override
    public FissionAccount getIncomeAccount(ISecuritySession securitySession) throws CircuitException {
        checkRights(securitySession);
        return fissionAccountService.getAndInitIncome();
    }

    @Override
    public FissionAccount getAbsorbAccount(ISecuritySession securitySession) throws CircuitException {
        checkRights(securitySession);
        return fissionAccountService.getAndInitAbsorb();
    }

    @Override
    public List<FissionAccount> listAccount(ISecuritySession securitySession) throws CircuitException {
        checkRights(securitySession);
        return fissionAccountService.listAccount();
    }

    @Override
    public List<BusinessBill> pageBusinessBill(ISecuritySession securitySession, int order, int limit, long offset) throws CircuitException {
        checkRights(securitySession);
        return fissionAccountService.pageBusinessBill(order,limit,offset);
    }
}
