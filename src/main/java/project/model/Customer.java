package project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customerId")
	private int customerId;
	
	@Column(name="customerName", nullable = false)
	private String customerName;
	
	@Column(name="customerEmail", nullable = false)
	private String customerEmail;
	
	@Column(name="customerIncome")
	private double customerIncomce;
	
	@Column(name="customerAge")
	private int customerAge;
	
	@Column(name="customerPass", nullable = false)
	private String customerPass;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String customerName, String customerEmail, double customerIncomce, int customerAge,
			String customerPass) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerIncomce = customerIncomce;
		this.customerAge = customerAge;
		this.customerPass = customerPass;
	}

	public Customer(String customerName, String customerEmail, String customerPass) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPass = customerPass;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public double getCustomerIncomce() {
		return customerIncomce;
	}

	public void setCustomerIncomce(double customerIncomce) {
		this.customerIncomce = customerIncomce;
	}

	public int getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(int customerAge) {
		this.customerAge = customerAge;
	}

	public String getCustomerPass() {
		return customerPass;
	}

	public void setCustomerPass(String customerPass) {
		this.customerPass = customerPass;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerIncomce=" + customerIncomce + ", customerAge=" + customerAge
				+ ", customerPass=" + customerPass + "]";
	}
	
}
