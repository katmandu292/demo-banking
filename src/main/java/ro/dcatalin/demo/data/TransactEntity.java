package ro.dcatalin.demo.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_tranzactii")
public class TransactEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TranzactId")
	private Integer transactId;

	@Column(name="DtTranzactie")
	private LocalDateTime datTranzact;

	@Column(name="AcctSrcId")
	private Integer acctSourceId;

	@Column(name="AcctTrgId")
	private Integer acctTargetId;

	@Column(name="SoldTrgSum")
	private double acctBalance;

	@Column(name="TransactSum")
	private double movedAmount;

	public TransactEntity() {
		super();
		movedAmount = 0.0;
	}

	public Integer getTransactId() {
		return transactId;
	}

	public void setTransactId(Integer transactId) {
		this.transactId = transactId;
	}

	public LocalDateTime getDatTranzact() {
		return datTranzact;
	}

	public void setDatTranzact(LocalDateTime datTranzact) {
		this.datTranzact = datTranzact;
	}

	public Integer getAcctSourceId() {
		return acctSourceId;
	}

	public void setAcctSourceId(Integer acctSourceId) {
		this.acctSourceId = acctSourceId;
	}

	public Integer getAcctTargetId() {
		return acctTargetId;
	}

	public void setAcctTargetId(Integer acctTargetId) {
		this.acctTargetId = acctTargetId;
	}

	public double getAcctBalance() {
		return acctBalance;
	}

	public void setAcctBalance(double acctBalance) {
		this.acctBalance = acctBalance;
	}

	public double getMovedAmount() {
		return movedAmount;
	}

	public void setMovedAmount(double movedAmount) {
		this.movedAmount = movedAmount;
	}

	@Override
	public String toString() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String x = datTranzact.format(myFormatObj);
		return "TransactEntity [" + transactId + ", " + datTranzact.format(myFormatObj) + ", Src: "
				+ acctSourceId + ", Trg: " + acctTargetId + ", Balance: " + acctBalance + ", moved: "
				+ movedAmount + "]";
	}

}
