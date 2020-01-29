package fr.eilco.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(schema = "magasindb", name = "ligne_commande")
@IdClass(pk_ligneCommande.class)
public class LigneCommande implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "commande_id")
	private Commande commande;
	@Id
	@ManyToOne
	@JoinColumn(name = "produit_id")	
	private Produit produit;
	
	@Column(name = "quantite")
	@Min(value=0, message="quantite >= {value}")
	private int quantite;
	
	public LigneCommande() {}
	
	
	public LigneCommande(Commande commande, Produit produit, int quantite) {
		super();
		this.commande = commande;
		this.produit = produit;
		this.quantite = quantite;
	}


	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
}
