package fr.eilco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(schema = "magasin" ,name = "produit")
public class Produit implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prix")
	private double prix;
	@Column(name = "email")
	private String email;
	@Column(name = "telephone")
	private String telephone;
	@Column(name = "adresse")
	private String adresse;
	@ManyToOne
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;
	
	
	
	
	public Produit(String nom, double prix, String email, String telephone, String adresse, Categorie categorie) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.categorie = categorie;
	}
	public Produit() {}
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
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
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
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
}
