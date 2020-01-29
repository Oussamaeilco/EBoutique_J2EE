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
 * Servlet implementation class CategorieServlet
 */
@WebServlet({ "/CategorieServlet", "/Categorie" })
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorieServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ArrayList<Categorie> categories=new ArrayList<Categorie>();
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
			//produits=remote.getListProduits();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//request.setAttribute("produitsList", produits);
		request.setAttribute("categoriesList", categories);
		request.getRequestDispatcher("View/categorie.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
