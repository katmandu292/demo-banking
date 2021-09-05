package ro.dcatalin.demo.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_client")
public class ClientEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ClientId")
	private Integer clientId;

	@Column(name="Prenume")
    private String clientGivenName;

	@Column(name="Nume")
    private String clientFamilyName;

	@Column(name="Email")
    private String clientEmail;

	@Column(name="Parola")
    private String clientPassWord;

	public ClientEntity() {
		super();
	}

	public ClientEntity(Integer clientId, String clientGivenName, String clientFamilyName, String clientEmail,
			String clientPassWord) {
		super();
		this.clientId = clientId;
		this.clientGivenName = clientGivenName;
		this.clientFamilyName = clientFamilyName;
		this.clientEmail = clientEmail;
		this.clientPassWord = clientPassWord;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientGivenName() {
		return clientGivenName;
	}

	public void setClientGivenName(String clientGivenName) {
		this.clientGivenName = clientGivenName;
	}

	public String getClientFamilyName() {
		return clientFamilyName;
	}

	public void setClientFamilyName(String clientFamilyName) {
		this.clientFamilyName = clientFamilyName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientPassWord() {
		return clientPassWord;
	}

	public void setClientPassWord(String clientPassWord) {
		this.clientPassWord = clientPassWord;
	}

	@Override
	public String toString() {
		return "ClientEntity [" + clientId + ", " + clientGivenName + " "
				+ clientFamilyName  + ", "+ clientEmail  + ", "+ clientPassWord + "]";
	}


}
