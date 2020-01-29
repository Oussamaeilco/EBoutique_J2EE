package fr.eilco.model;

import java.io.Serializable;

public class pk_ligneCommande implements Serializable{
	private int commande;
	private int produit;
	 
	public pk_ligneCommande() {}
	 
	public pk_ligneCommande(int commande_client_id, int produit_id) {
		super();
		this.commande = commande_client_id;
		this.produit = produit_id;
	}
	public int getCommande_id() {
		return commande;
	}
	public void setCommande_id(int commande_client_id) {
		this.commande = commande_client_id;
	}
	public int getProduit_id() {
		return produit;
	}
	public void setProduit_id(int produit_id) {
		this.produit = produit_id;
	} 
}
