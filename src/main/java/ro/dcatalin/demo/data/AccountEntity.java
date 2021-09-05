package ro.dcatalin.demo.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_conturi")
public class AccountEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ContId")
	private Integer accountId;

	@Column(name="NrCont")
    private String accountIban;

	@Column(name="Banca")
    private String bankName;

	@Column(name="ClientId")
	private Integer clientId;

	@Column(name="Suma")
	private Double finalSum;

	@Column(name="Stare")
	private Integer statusId;

	public Integer getAccountId() {
		return accountId;
	}

	public AccountEntity() {
		super();
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountIban() {
		return accountIban;
	}

	public void setAccountIban(String accountIban) {
		this.accountIban = accountIban;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Double getFinalSum() {
		return finalSum;
	}

	public void setFinalSum(Double finalSum) {
		this.finalSum = finalSum;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public void payMoney(Double paidAmount) {
		setFinalSum(getFinalSum() - paidAmount);
	}

	public void acceptPayment(Double paidAmount) {
		setFinalSum(getFinalSum() + paidAmount);
	}

	@Override
	public String toString() {
		return "AccountEntity [" + accountId + ", " + accountIban + ", " + bankName
				+ ", " + clientId + ", " + finalSum + ", " + statusId + "]";
	}

}
