package fr.eilco.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="magasindb",name="commande_client")
public class Commande implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "montant")
	private long montant;
	@Column(name = "date_creation")
	private Timestamp date_creation;
	@GeneratedValue
	@Column(name = "no_confirmation")
	private int no_confirmation;
	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;
	
	
	public Commande() {}
	
	public Commande(long montant, Timestamp date_creation, int no_confirmation, Client client) {
		super();
		this.montant = montant;
		this.date_creation = date_creation;
		this.no_confirmation = no_confirmation;
		this.client = client;
	}
	
	public Commande(long montant, Timestamp date_creation, Client client) {
		super();
		this.montant = montant;
		this.date_creation = date_creation;
		this.client = client;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getMontant() {
		return montant;
	}
	public void setMontant(long montant) {
		this.montant = montant;
	}
	public Timestamp getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(Timestamp date_creation) {
		this.date_creation = date_creation;
	}
	public int getNo_confirmation() {
		return no_confirmation;
	}
	public void setNo_confirmation(int no_confirmation) {
		this.no_confirmation = no_confirmation;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
