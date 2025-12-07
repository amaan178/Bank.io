package project.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Loan")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="loanId")
	private int loanId;
	
	@Column(name="loanAmount")
	private double loanAmount;
	
	@Column(name="loanEmiAmount")
	private double loanEmiAmount;
	
	@Column(name="loanType")
	private String loanType;
	
	@Column(name="loanStatus")
	private String loanStatus;
	
	@Column(name="loanDuration")
	private int loanDuration;
	
	@Column(name="loanStartDate")
	private Date loanStartDate;
	
	@Column(name="loanEndDate")
	private Date loanEndDate;
	
	@Column(name="loanTotalEmi")
	private int loanTotalEmi;
	
	@Column(name="loanPaidEmi")
	private int loanPaidEmi;
	
	@Column(name="loanRemainEmi")
	private int loanRemainEmi;
	
	@Column(name="loanLastEmiDate")
	private Date loanLastEmiDate;
	
	@Column(name="loanNextEmiDate")
	private Date loanNextEmiDate;
	
	//@ManyToOne
    //@JoinColumn(name = "customerId", nullable = false)
	@Column(name="customerId")
	private int customerId;
	
	//@ManyToOne
    //@JoinColumn(name = "accountId", nullable = false)
	@Column(name="accountId")
	private int accountId;

	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// constructor for AddLoan Servlet
	public Loan(double loanAmount, double loanEmiAmount, String loanType, String loanStatus, int loanDuration,
			Date loanStartDate, Date loanEndDate, int loanTotalEmi, int loanPaidEmi, int loanRemainEmi,
			Date loanNextEmiDate, int customerId, int accountId) {
		super();
		this.loanAmount = loanAmount;
		this.loanEmiAmount = loanEmiAmount;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
		this.loanDuration = loanDuration;
		this.loanStartDate = loanStartDate;
		this.loanEndDate = loanEndDate;
		this.loanTotalEmi = loanTotalEmi;
		this.loanPaidEmi = loanPaidEmi;
		this.loanRemainEmi = loanRemainEmi;
		this.loanNextEmiDate = loanNextEmiDate;
		this.customerId = customerId;
		this.accountId = accountId;
	}

	public Loan(int loanId, double loanAmount, double loanEmiAmount, String loanType, String loanStatus,
			int loanDuration, Date loanStartDate, Date loanEndDate, int loanTotalEmi, int loanPaidEmi,
			int loanRemainEmi, Date loanLastEmiDate, Date loanNextEmiDate, int customerId, int accountId) {
		super();
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.loanEmiAmount = loanEmiAmount;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
		this.loanDuration = loanDuration;
		this.loanStartDate = loanStartDate;
		this.loanEndDate = loanEndDate;
		this.loanTotalEmi = loanTotalEmi;
		this.loanPaidEmi = loanPaidEmi;
		this.loanRemainEmi = loanRemainEmi;
		this.loanLastEmiDate = loanLastEmiDate;
		this.loanNextEmiDate = loanNextEmiDate;
		this.customerId = customerId;
		this.accountId = accountId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getLoanEmiAmount() {
		return loanEmiAmount;
	}

	public void setLoanEmiAmount(double loanEmiAmount) {
		this.loanEmiAmount = loanEmiAmount;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public int getLoanDuration() {
		return loanDuration;
	}

	public void setLoanDuration(int loanDuration) {
		this.loanDuration = loanDuration;
	}

	public Date getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public Date getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public int getLoanTotalEmi() {
		return loanTotalEmi;
	}

	public void setLoanTotalEmi(int loanTotalEmi) {
		this.loanTotalEmi = loanTotalEmi;
	}

	public int getLoanPaidEmi() {
		return loanPaidEmi;
	}

	public void setLoanPaidEmi(int loanPaidEmi) {
		this.loanPaidEmi = loanPaidEmi;
	}

	public int getLoanRemainEmi() {
		return loanRemainEmi;
	}

	public void setLoanRemainEmi(int loanRemainEmi) {
		this.loanRemainEmi = loanRemainEmi;
	}

	public Date getLoanLastEmiDate() {
		return loanLastEmiDate;
	}

	public void setLoanLastEmiDate(Date loanLastEmiDate) {
		this.loanLastEmiDate = loanLastEmiDate;
	}

	public Date getLoanNextEmiDate() {
		return loanNextEmiDate;
	}

	public void setLoanNextEmiDate(Date loanNextEmiDate) {
		this.loanNextEmiDate = loanNextEmiDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanAmount=" + loanAmount + ", loanEmiAmount=" + loanEmiAmount
				+ ", loanType=" + loanType + ", loanStatus=" + loanStatus + ", loanDuration=" + loanDuration
				+ ", loanStartDate=" + loanStartDate + ", loanEndDate=" + loanEndDate + ", loanTotalEmi=" + loanTotalEmi
				+ ", loanPaidEmi=" + loanPaidEmi + ", loanRemainEmi=" + loanRemainEmi + ", loanLastEmiDate="
				+ loanLastEmiDate + ", loanNextEmiDate=" + loanNextEmiDate + ", customerId=" + customerId + "]";
	}
}