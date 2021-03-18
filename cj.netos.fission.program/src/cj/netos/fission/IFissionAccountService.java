package cj.netos.fission;

import cj.netos.fission.model.*;

import java.util.List;

public interface IFissionAccountService {
    FissionAccount getAndInitBusiness();

    FissionAccount getAndInitIncome();

    FissionAccount getAndInitAbsorb();

    void income(IncomeRecord record);

    void inAbsorb(AbsorbInRecord record);

    void inBusiness(BusinessInRecord record);

    List<FissionAccount> listAccount();

    List<BusinessBill> pageBusinessBill(int order, int limit, long offset);

}
