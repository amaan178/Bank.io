package project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="accountId")
    private int accountId;
    
    @Column(name="accountNumber", length = 10)
    private String accountNumber;
    
    //@ManyToOne
    //@JoinColumn(name = "customerId", nullable = false)
    @Column(name="customerId")
    private int customerId;
    
    @Column(name="accountType")
    private String accountType;
    
    @Column(name="accountBalance")
    private double accountBalance;
    
    @Column(name="bankName")
    private String bankName;
    
    public Account() {
        super();
    }
    
    public Account(String accountNumber, int customerId, String accountType, double accountBalance, String bankName) {
        super();
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.bankName = bankName;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public double getAccountBalance() {
        return accountBalance;
    }
    
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    
    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", customerId=" + customerId
                + ", accountType=" + accountType + ", accountBalance=" + accountBalance + ", bankName=" + bankName + "]";
    }
}
