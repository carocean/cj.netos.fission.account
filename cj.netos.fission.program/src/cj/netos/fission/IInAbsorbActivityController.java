package cj.netos.fission;

import cj.netos.fission.model.AbsorbInRecord;
import cj.netos.fission.model.IncomeRecord;

public interface IInAbsorbActivityController {
    void inAbsorb(AbsorbInRecord record);

    void error(AbsorbInRecord record, int i, String msg);

}
