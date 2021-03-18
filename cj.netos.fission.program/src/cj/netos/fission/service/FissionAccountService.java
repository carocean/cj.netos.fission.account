package cj.netos.fission.service;

import cj.netos.fission.IFissionAccountService;
import cj.netos.fission.mapper.AbsorbBillMapper;
import cj.netos.fission.mapper.BusinessBillMapper;
import cj.netos.fission.mapper.FissionAccountMapper;
import cj.netos.fission.mapper.IncomeBillMapper;
import cj.netos.fission.model.*;
import cj.netos.fission.util.CashierUtils;
import cj.netos.fission.util.IdWorker;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.Calendar;
import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "fissionAccountService")
public class FissionAccountService implements IFissionAccountService {
    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.FissionAccountMapper")
    FissionAccountMapper fissionAccountMapper;

    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.IncomeBillMapper")
    IncomeBillMapper incomeBillMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.AbsorbBillMapper")
    AbsorbBillMapper absorbBillMapper;
    @CjServiceRef(refByName = "mybatis.cj.netos.fission.mapper.BusinessBillMapper")
    BusinessBillMapper businessBillMapper;

    @CjTransaction
    @Override
    public List<FissionAccount> listAccount() {
        FissionAccountExample example = new FissionAccountExample();
        example.createCriteria();
        return fissionAccountMapper.selectByExample(example);
    }

    @CjTransaction
    @Override
    public FissionAccount getAndInitBusiness() {
        FissionAccountExample example = new FissionAccountExample();
        example.createCriteria().andAccountEqualTo("business");
        List<FissionAccount> accountList = fissionAccountMapper.selectByExample(example);
        if (!accountList.isEmpty()) {
            return accountList.get(0);
        }
        FissionAccount account = new FissionAccount();
        account.setAccount("business");
        account.setBalance(0L);
        fissionAccountMapper.insert(account);
        return account;
    }

    @CjTransaction
    @Override
    public FissionAccount getAndInitIncome() {
        FissionAccountExample example = new FissionAccountExample();
        example.createCriteria().andAccountEqualTo("income");
        List<FissionAccount> accountList = fissionAccountMapper.selectByExample(example);
        if (!accountList.isEmpty()) {
            return accountList.get(0);
        }
        FissionAccount account = new FissionAccount();
        account.setAccount("income");
        account.setBalance(0L);
        fissionAccountMapper.insert(account);
        return account;
    }

    @CjTransaction
    @Override
    public FissionAccount getAndInitAbsorb() {
        FissionAccountExample example = new FissionAccountExample();
        example.createCriteria().andAccountEqualTo("absorb");
        List<FissionAccount> accountList = fissionAccountMapper.selectByExample(example);
        if (!accountList.isEmpty()) {
            return accountList.get(0);
        }
        FissionAccount account = new FissionAccount();
        account.setAccount("absorb");
        account.setBalance(0L);
        fissionAccountMapper.insert(account);
        return account;
    }

    @CjTransaction
    @Override
    public void income(IncomeRecord record) {
        FissionAccount account = getAndInitIncome();

        IncomeBill bill = new IncomeBill();
        bill.setSn(new IdWorker().nextId());
        bill.setAccount("income");
        bill.setTitle("用户提现平台分账");
        bill.setPerson(record.getPerson());
        bill.setNickName(record.getNickName());
        bill.setAmount(record.getAmount());
        long balanceAmount = account.getBalance() + bill.getAmount();
        bill.setBalance(balanceAmount);
        bill.setOrder(0);
        bill.setRefsn(record.getSn());
        bill.setCtime(CashierUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        bill.setWorkday(CashierUtils.dateTimeToDay(System.currentTimeMillis()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        bill.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        bill.setMonth(calendar.get(Calendar.MONTH));
        bill.setSeason(calendar.get(Calendar.MONTH) % 4);
        bill.setYear(calendar.get(Calendar.YEAR));
        bill.setNote(null);
        incomeBillMapper.insert(bill);

        fissionAccountMapper.updateBalance("income", balanceAmount);
    }


    @CjTransaction
    @Override
    public void inAbsorb(AbsorbInRecord record) {
        FissionAccount account = getAndInitAbsorb();

        AbsorbBill bill = new AbsorbBill();
        bill.setSn(new IdWorker().nextId());
        bill.setAccount("absorb");
        bill.setTitle("用户提现洇金分账");
        bill.setPerson(record.getPerson());
        bill.setNickName(record.getNickName());
        bill.setAmount(record.getAmount());
        long balanceAmount = account.getBalance() + bill.getAmount();
        bill.setBalance(balanceAmount);
        bill.setOrder(0);
        bill.setRefsn(record.getSn());
        bill.setCtime(CashierUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        bill.setWorkday(CashierUtils.dateTimeToDay(System.currentTimeMillis()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        bill.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        bill.setMonth(calendar.get(Calendar.MONTH));
        bill.setSeason(calendar.get(Calendar.MONTH) % 4);
        bill.setYear(calendar.get(Calendar.YEAR));
        bill.setNote(null);
        absorbBillMapper.insert(bill);

        fissionAccountMapper.updateBalance("absorb", balanceAmount);
    }

    @CjTransaction
    @Override
    public void inBusiness(BusinessInRecord record) {
        FissionAccount account = getAndInitBusiness();

        BusinessBill bill = new BusinessBill();
        bill.setSn(new IdWorker().nextId());
        bill.setAccount("business");
        bill.setTitle("营业收入");
        bill.setPerson(record.getPerson());
        bill.setNickName(record.getNickName());
        bill.setAmount(record.getAmount());
        long balanceAmount = account.getBalance() + bill.getAmount();
        bill.setBalance(balanceAmount);
        bill.setOrder(0);
        bill.setRefsn(record.getSn());
        bill.setCtime(CashierUtils.dateTimeToMicroSecond(System.currentTimeMillis()));
        bill.setWorkday(CashierUtils.dateTimeToDay(System.currentTimeMillis()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        bill.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        bill.setMonth(calendar.get(Calendar.MONTH));
        bill.setSeason(calendar.get(Calendar.MONTH) % 4);
        bill.setYear(calendar.get(Calendar.YEAR));
        bill.setNote(null);
        businessBillMapper.insert(bill);

        fissionAccountMapper.updateBalance("business", balanceAmount);
    }

    @CjTransaction
    @Override
    public List<BusinessBill> pageBusinessBill(int order, int limit, long offset) {
        return businessBillMapper.pageByOrder(order, limit, offset);
    }
}
