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
import fr.eilco.model.Client;
import fr.eilco.model.Commande;
import fr.eilco.model.Produit;

/**
 * Servlet implementation class ValiderPanierServlet
 */
@WebServlet({ "/ValiderPanierServlet", "/ValiderPanier" })
public class ValiderPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderPanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Hashtable<Integer, Integer> panier=new Hashtable<Integer, Integer>();
		Client client=null;
		if(session.getAttribute("client")==null) {

			request.getRequestDispatcher("View/login.jsp").forward(request, response);
		}
		else {
			client=(Client) session.getAttribute("client");
		
		if(session.getAttribute("panier")!=null) {
			panier=(Hashtable<Integer, Integer>) session.getAttribute("panier");
		}
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
		//Creation du Panier
			Commande commande= remote.newCommande(client.getId());
			remote.validerCommande(panier, commande.getId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//Reponse
		session.removeAttribute("panier");;
		request.getRequestDispatcher("Panier").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
