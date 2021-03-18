package cj.netos.fission;

import cj.netos.fission.model.IncomeRecord;

public interface IIncomeActivityController {
    void income(IncomeRecord record);

    void error(IncomeRecord record, int i, String msg);

}
