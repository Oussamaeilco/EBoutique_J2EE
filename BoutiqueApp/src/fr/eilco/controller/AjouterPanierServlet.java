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
 * Servlet implementation class AjouterPanierServlet
 */
@WebServlet({ "/AjouterPanierServlet", "/AjouterPanier" })
public class AjouterPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterPanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		int id=Integer.parseInt(request.getParameter("id"));
		int produit_id=Integer.parseInt(request.getParameter("produit_id"));
		Hashtable<Integer, Integer> panier=new Hashtable<Integer, Integer>();
		if(session.getAttribute("panier")!=null) {
			panier=(Hashtable<Integer, Integer>) session.getAttribute("panier");
		}
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
			//Charger les catécogories
			categories=remote.getListCategories();
			//Charger les produits
			produits=remote.getListProduitsCategorie(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//Ajout au panier
		if(panier.get(produit_id)==null) {
			panier.put(produit_id, 1);
		}
		else {
			panier.replace(produit_id,panier.get(produit_id)+1);
		}
		//Reponse
		request.setAttribute("id_categorie", id);
		request.setAttribute("produitsList", produits);
		request.setAttribute("categoriesList", categories);
		session.setAttribute("panier", panier);
		request.getRequestDispatcher("View/produit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
