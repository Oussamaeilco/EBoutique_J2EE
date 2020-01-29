package fr.eilco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="magasindb",name="client")
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "email")
	private String email;
	@Column(name = "motdepass")
	private String motdepass;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "adresse")
	private String adresse;
	/*-------Construct--------*/
	public Client(String nom, String email, String motdepass, String telephone, String adresse) {
		super();
		this.nom = nom;
		this.email = email;
		this.motdepass =motdepass;
		this.telephone = telephone;
		this.adresse = adresse;
	}
	public Client() {}
	/*-----Get&Set------*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getMotdepass() {
		return motdepass;
	}
	public void setMotdepass(String motdepass) {
		this.motdepass = motdepass;
	}
	
	
}
