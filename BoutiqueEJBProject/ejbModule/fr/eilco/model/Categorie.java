package fr.eilco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="magasindb",name="categorie")
public class Categorie implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "nom")
	private String nom;
	
	
	public Categorie() {}
	
	public Categorie(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	public Categorie(String nom) {
		super();
		this.nom = nom;
	}

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
	
	
	
	
	
}
