package fr.eilco.ejb;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.ejb.Remote;

import fr.eilco.model.Categorie;
import fr.eilco.model.Client;
import fr.eilco.model.Commande;
import fr.eilco.model.Panier;
import fr.eilco.model.Produit;

@Remote
public interface CatalogueEJBRemote {
	public ArrayList<Categorie> getListCategories();
	public ArrayList<Produit> getListProduits();
	public ArrayList<Produit> getListProduitsCategorie(int id);
	public void newCategorie(String c);
	public void newProduit(String nom,double prix,String email, String telephone, String adresse,int id_categorie);
	public void newClient(String nom,String email,String motdepass,String telephone,String adresse);
	public void updateClient(String email, String motdepass);
	public Commande newCommande(int id);
	public void validerCommande(Hashtable<Integer, Integer> panier, int id_commande);
	public ArrayList<Panier> chargerPanier(Hashtable<Integer, Integer> panier);
	public Client login(String email, String password);

}
