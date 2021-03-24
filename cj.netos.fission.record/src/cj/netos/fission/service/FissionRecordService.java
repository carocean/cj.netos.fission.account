package cj.netos.fission.service;

import cj.netos.fission.mapper.*;
import cj.netos.fission.model.AbsorbInRecord;
import cj.netos.fission.model.AbsorbOutRecord;
import cj.netos.fission.model.BusinessInRecord;
import cj.netos.fission.model.IncomeRecord;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "fissionRecordService")
public class FissionRecordService implements IFissionRecordService {
    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.IncomeRecordMapper")
    IncomeRecordMapper incomeRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.OutcomeRecordMapper")
    OutcomeRecordMapper outcomeRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.AbsorbInRecordMapper")
    AbsorbInRecordMapper absorbInRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.AbsorbOutRecordMapper")
    AbsorbOutRecordMapper absorbOutRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.BusinessInRecordMapper")
    BusinessInRecordMapper businessInRecordMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.BusinessRefundRecordMapper")
    BusinessRefundRecordMapper businessRefundRecordMapper;

    @CjTransaction
    @Override
    public void incomeSucceed(IncomeRecord record) {
        incomeRecordMapper.done(record.getSn(), 200, "ok");
    }

    @CjTransaction
    @Override
    public void incomeError(IncomeRecord record, int status, String message) {
        incomeRecordMapper.done(record.getSn(), status, message);
    }

    @CjTransaction
    @Override
    public void inAbsorbSucceed(AbsorbInRecord record) {
        absorbInRecordMapper.done(record.getSn(), 200, "ok");
    }

    @CjTransaction
    @Override
    public void inAbsorbError(AbsorbInRecord record, int status, String message) {
        absorbInRecordMapper.done(record.getSn(), status, message);
    }

    @CjTransaction
    @Override
    public void outAbsorb(AbsorbOutRecord record) {
        absorbOutRecordMapper.insert(record);
    }

    @CjTransaction
    @Override
    public void outAbsorbSucceed(AbsorbOutRecord record) {
        absorbOutRecordMapper.done(record.getSn(),200,"ok");
    }

    @CjTransaction
    @Override
    public void outAbsorbError(AbsorbOutRecord record, int status, String message) {
        absorbOutRecordMapper.done(record.getSn(),status,message);
    }

    @CjTransaction
    @Override
    public void inBusinessSucceed(BusinessInRecord record) {
        businessInRecordMapper.done(record.getSn(), 200, "ok");
    }

    @CjTransaction
    @Override
    public void inBusinessError(BusinessInRecord record, int status, String message) {
        businessInRecordMapper.done(record.getSn(), status, message);
    }

    @CjTransaction
    @Override
    public List<BusinessInRecord> pageBusinessInRecord(int shuntState, int limit, long offset) {
        if (shuntState < 0) {
            return businessInRecordMapper.page(limit, offset);
        }
        return businessInRecordMapper.pageByShuntState(shuntState, limit, offset);
    }
}
