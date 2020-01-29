package fr.eilco.model;

import java.io.Serializable;

public class Panier implements Serializable{
	private int id;
	private String nom;
	private int quantit;
	public Panier() {
	}
	public Panier(int id, String nom, int quantit) {
		super();
		this.id = id;
		this.nom = nom;
		this.quantit = quantit;
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
	public int getQuantit() {
		return quantit;
	}
	public void setQuantit(int quantit) {
		this.quantit = quantit;
	}
	
	

}
