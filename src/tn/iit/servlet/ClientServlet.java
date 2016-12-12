package tn.iit.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.iit.entity.Client;
import tn.iit.service.ClientDaoLocalService;




/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

   @EJB
   ClientDaoLocalService clientservice;
       
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		List<Client> clientss=clientservice.afficher();
		ses.setAttribute("clientss", clientss);
		request.getRequestDispatcher("/client.jsp").forward(request, response);
		
		 if(request.getParameter("supp")!=null)
		{
			HttpSession sess = request.getSession();
			Client client=clientservice.getByCin(Long.parseLong(request.getParameter("cin")));
			clientservice.supprimer(client);
			request.getRequestDispatcher("/client.jsp").forward(request, response);
		}else if(request.getParameter("modif")!=null)
		{
			HttpSession session = request.getSession();
			Client client=clientservice.getByCin(Long.parseLong(request.getParameter("cin")));
			
			session.setAttribute("clientedit", client);
			request.getRequestDispatcher("/client.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("add")!=null)
		{
			Client c=new Client(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("adresse"));
			clientservice.ajouter(c);
			HttpSession ses = request.getSession();
			List<Client> clientss=clientservice.afficher();
			request.setAttribute("clientss", clientss);
			request.getRequestDispatcher("/client.jsp").forward(request, response);
		}else if(request.getParameter("validermodif")!=null)
		{
			Client  cl=clientservice.getByCin(Long.parseLong(request.getParameter("cin")));
			cl.setAdresse(request.getParameter("adresse"));
			cl.setNom(request.getParameter("nom"));
			cl.setPrenom(request.getParameter("prenom"));
			clientservice.modifier(cl);
			HttpSession ses = request.getSession();
			List<Client> clientss=clientservice.afficher();
			request.setAttribute("clientss", clientss);
			request.getRequestDispatcher("/client.jsp").forward(request, response);
		}
			
	}

}
