package cj.netos.fission.service;

import cj.netos.fission.model.AbsorbInRecord;
import cj.netos.fission.model.AbsorbOutRecord;
import cj.netos.fission.model.BusinessInRecord;
import cj.netos.fission.model.IncomeRecord;

import java.util.List;

public interface IFissionRecordService {
    void incomeSucceed(IncomeRecord record);

    void incomeError(IncomeRecord record, int status, String message);

    void inAbsorbSucceed(AbsorbInRecord record);

    void inAbsorbError(AbsorbInRecord record, int status, String message);

    void inBusinessSucceed(BusinessInRecord record);

    void inBusinessError(BusinessInRecord record, int status, String message);

    List<BusinessInRecord> pageBusinessInRecord(int shuntState, int limit, long offset);

    void outAbsorb(AbsorbOutRecord record);

    void outAbsorbError(AbsorbOutRecord outRecord, int status, String message);

    void outAbsorbSucceed(AbsorbOutRecord outRecord);

}
