package fr.eilco.controller;

import java.io.IOException;
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
import fr.eilco.model.Client;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String email=request.getParameter("email");
		String motdepass=request.getParameter("motdepass");
		Client client=null;
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
					//Load Login
					client=remote.login(email, motdepass);
					if(client==null) {
						request.getRequestDispatcher("View/login.jsp").forward(request, response);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
		session.setAttribute("client", client);
		//Reponse
		request.getRequestDispatcher("Panier").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
