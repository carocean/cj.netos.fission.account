package cj.netos.fission.model;

/**
 * Table: fission_account
 */
public class FissionAccount {
    /**
     * Column: account
     * Remark: 三个账户，如： absorbs 用户提现平台的洇金分账账户 income 用户提现平台的分账账户 business B端冲值平台的分账账户
     */
    private String account;

    /**
     * Column: balance
     * Remark: 余额
     */
    private Long balance;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}