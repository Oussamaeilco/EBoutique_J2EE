package fr.eilco.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eilco.ejb.CatalogueEJBRemote;
import fr.eilco.model.Categorie;
import fr.eilco.model.Produit;

/**
 * Servlet implementation class AcceuilServlet
 */
@WebServlet("/AcceuilServlet")
public class AcceuilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceuilServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ArrayList<Categorie> categories=new ArrayList<Categorie>();
		ArrayList<Produit> produits=new ArrayList<Produit>();
		//ConnexionJNDI (annuaire pour localiserl'EJB)
		try{
			final Hashtable jndiProperties= new Hashtable();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context= new InitialContext(jndiProperties);
			final String appName= "BoutiqueEAR";
			final String moduleName= "BoutiqueEJBProject";
			final String beanName= "CatalogueJNDI";
			final String viewClassName= CatalogueEJBRemote.class.getName();
			CatalogueEJBRemote remote= (CatalogueEJBRemote) context.lookup("ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+viewClassName);			
//			 //Catégories
//			 remote.newCategorie("Livres");remote.newCategorie("Consoles");remote.newCategorie("CD"); 
//			 //Produits 
//			  //Livres 
//			  remote.newProduit("Les Miserables",
//			  15, "livrelib@lib.fr", "+3358789699", "ADDRESS1", 1);
//			  remote.newProduit("Harry Potter 2", 10, "livrelib@lib.fr", "+3358789699",
//			  "ADDRESS1", 1); remote.newProduit("D&D 5th edition", 15, "livrelib@lib.fr",
//			  "+3358789699", "ADDRESS2", 1); //Consoles
//			  remote.newProduit("PS4 pro",301,"consol@lib.fr","+3358789699", "ADDRESS1",
//			  2); remote.newProduit("Xbox One",299,"consol@lib.fr","+3358789699",
//			  "ADDRESS1", 2);
//			  remote.newProduit("nintendo switch",300,"consol@lib.fr","+3358789699",
//			  "ADDRESS2", 2); //CD
//			  remote.newProduit("Breaking Bad",25,"serie@lib.fr","+3358789699", "ADDRESS1",
//     		  3); remote.newProduit("supernatural",20,"serie@lib.fr","+3358789699",
//			  "ADDRESS1", 3);
//			  remote.newProduit("Harry Potter pack",15,"film@lib.fr","+3358789699",
//			  "ADDRESS2", 3);
			 			
			//Charger les catécogories
			categories=remote.getListCategories();
			//Charger les produits
			produits=remote.getListProduits();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("produitsList", produits);
		request.setAttribute("categoriesList", categories);
		request.getRequestDispatcher("View/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
