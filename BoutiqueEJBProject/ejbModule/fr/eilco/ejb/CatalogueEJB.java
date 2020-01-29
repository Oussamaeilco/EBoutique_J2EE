package fr.eilco.ejb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sun.org.apache.bcel.internal.generic.NEW;

import fr.eilco.model.Categorie;
import fr.eilco.model.Client;
import fr.eilco.model.Commande;
import fr.eilco.model.LigneCommande;
import fr.eilco.model.Panier;
import fr.eilco.model.Produit;

/**
 * Session Bean implementation class CatalogueEJB
 */
@Stateless(name = "CatalogueJNDI")
public class CatalogueEJB implements CatalogueEJBRemote, CatalogueEJBLocal {

	@PersistenceContext(unitName="managerBoutique")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public CatalogueEJB() {}

    //Produits et Catégories
	@Override
	public ArrayList<Categorie> getListCategories() {
		Query query = em.createQuery("Select h from Categorie h");
		ArrayList<Categorie> categories=(ArrayList<Categorie>)(query.getResultList());
		return categories;
	}

	@Override
	public ArrayList<Produit> getListProduits() {
		Query query = em.createQuery("Select h from Produit h");
		ArrayList<Produit> produits=(ArrayList<Produit>)(query.getResultList());
		return produits;
	}
	@Override
	public ArrayList<Produit> getListProduitsCategorie(int id){
		Categorie cat=em.find(Categorie.class,id);
		Query query = em.createQuery("Select h from Produit h where h.categorie=?1");
		query.setParameter(1, cat);
		ArrayList<Produit> produits=(ArrayList<Produit>)(query.getResultList());
		return produits;
	}
	@Override
	public void newCategorie(String c) {
		try {
			em.persist(new Categorie(c));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void newProduit(String nom,double prix,String email, String telephone, String adresse,int id_categorie) {
		Categorie categorie=em.find(Categorie.class,id_categorie);
		Produit p=new Produit(nom, prix, email, telephone, adresse, categorie);
		try {
			em.persist(p);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//Compte Client
	@Override
	public void newClient(String nom,String email,String motdepass,String telephone,String adresse) {
		Client client=new Client(nom, email, motdepass, telephone, adresse);
		try {
			em.persist(client);
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	@Override
	public Client login(String email, String password) {
		Query query=em.createQuery("Select h from Client h where h.email=?1 and h.motdepass=?2");
		query.setParameter(1, email);query.setParameter(2, password);
		Client client=(Client) query.getSingleResult();
		return client;
	}
	
	@Override
	public void updateClient(String email,String motdepass) {
		Query query=em.createQuery("Select h from Client h where h.email = ?1").setParameter(1, email);
		Client client=(Client) query.getSingleResult();
		client.setMotdepass(motdepass);
	}
	
	//Commande
	
	@Override
	public Commande newCommande(int id) {
		Client client=em.find(Client.class, id);
		Commande commande=new Commande(0, new Timestamp(System.currentTimeMillis()), client);
		try {
			em.persist(commande);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return commande;
	}
	
	@Override
	public void validerCommande(Hashtable<Integer, Integer> panier, int id_commande) {
		Commande commande=em.find(Commande.class, id_commande);
		double prix=0;
		Set<Integer> keys=panier.keySet();
		Iterator<Integer> itr=keys.iterator();
		while(itr.hasNext()) {
			try {
				int key=itr.next();
				Produit produit = em.find(Produit.class, key);
				prix=prix+produit.getPrix()*panier.get(key);
				em.persist(new LigneCommande(commande, produit, panier.get(key)));
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		commande.setMontant((long)prix);
	}
	
	@Override
	
	public ArrayList<Panier> chargerPanier(Hashtable<Integer, Integer> panier){
		ArrayList<Panier> list=new ArrayList<Panier>();
		Set<Integer> keys=panier.keySet();
		Iterator<Integer> itr=keys.iterator();
		while(itr.hasNext()) {
				int key=itr.next();
				Produit produit = em.find(Produit.class, key);
				list.add(new Panier(produit.getId(), produit.getNom(), panier.get(key)));
		}
		return list;
	}
	
}
