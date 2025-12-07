package project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transactionId")
    private int transactionId;
    
    //@ManyToOne
    //@JoinColumn(name = "accountId", nullable = false)
    @Column(name="accountId")
    private int accountId;
    
    //@ManyToOne
    //@JoinColumn(name = "customerId", nullable = false)
    @Column(name="customerId")
    private int customerId;
    
    @Column(name="transactionType")
    private String transactionType;
    
    @Column(name="transactionAmount")
    private double transactionAmount;
    
    @Column(name="transactionDesc")
    private String transactionDesc;
    
    public Transactions() {
        super();
    }
    

	public Transactions(int accountId, int customerId, String transactionType, double transactionAmount, String transactionDesc) {
        super();
        this.accountId = accountId;
        this.customerId = customerId;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDesc = transactionDesc;
    }
    
    public int getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public String getTransactionType() {
        return transactionType;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public double getTransactionAmount() {
        return transactionAmount;
    }
    
    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    
    public String getTransactionDesc() {
        return transactionDesc;
    }
    
    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }
    
    @Override
    public String toString() {
        return "Transaction [transactionId=" + transactionId + ", accountId=" + accountId + ", customerId=" + customerId
                + ", transactionType=" + transactionType + ", transactionAmount=" + transactionAmount
                + ", transactionDesc=" + transactionDesc + "]";
    }
}
