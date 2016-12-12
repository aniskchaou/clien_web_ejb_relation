package tn.iit.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.iit.entity.Client;
import tn.iit.entity.CompteBancaire;
import tn.iit.service.ClientDaoLocalService;
import tn.iit.service.CompteBancaireServiceLocal;

/**
 * Servlet implementation class CompteServlet
 */
@WebServlet("/CompteServlet")
public class CompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
  */  
    @EJB
	CompteBancaireServiceLocal servicebancaire;
    @EJB
    ClientDaoLocalService clientservice;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession ses = request.getSession();
		List<CompteBancaire> comptes=servicebancaire.getAll();
		List<Client> clientss=clientservice.afficher();
		ses.setAttribute("clientss", clientss);
		ses.setAttribute("comptes", comptes);
		request.getRequestDispatcher("/compte.jsp").forward(request, response);
		 if(request.getParameter("supp")!=null)
		{
			CompteBancaire cb=servicebancaire.getByRib(Long.parseLong(request.getParameter("rib")));
			servicebancaire.supprimer(cb);
			ses.setAttribute("comptes", comptes);
			request.getRequestDispatcher("/compte.jsp").forward(request, response);
		}else if(request.getParameter("ret")!=null)
		{
			CompteBancaire cb=servicebancaire.getByRib(Long.parseLong(request.getParameter("rib")));
			ses.setAttribute("compteret", cb);
			request.getRequestDispatcher("/compte.jsp").forward(request, response);
		}else if(request.getParameter("depo")!=null)
		{
			CompteBancaire cb=servicebancaire.getByRib(Long.parseLong(request.getParameter("rib")));
			ses.setAttribute("comptedepo", cb);
			request.getRequestDispatcher("/compte.jsp").forward(request, response);
		}else if(request.getParameter("trans")!=null)
		{
			CompteBancaire cb=servicebancaire.getByRib(Long.parseLong(request.getParameter("rib")));
			ses.setAttribute("comptetrans", cb);
			List<Client> clientes=clientservice.afficher();
			ses.setAttribute("clientes", clientes);
			request.getRequestDispatcher("/compte.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("add")!=null)
		{
			Client c=clientservice.getByCin(Long.parseLong(request.getParameter("client")));
			CompteBancaire cb=new CompteBancaire(Double.parseDouble(request.getParameter("solde")),c);
			servicebancaire.ajouter(cb);
			HttpSession ses = request.getSession();
			List<CompteBancaire> comptes=servicebancaire.getAll();
			ses.setAttribute("comptes", comptes);
			request.getRequestDispatcher("/compte.jsp").forward(request, response);
		}else if(request.getParameter("validerret")!=null)
		{
			double solderetirer=Double.parseDouble(request.getParameter("solde"))-Double.parseDouble(request.getParameter("solderet"));
			CompteBancaire cb=servicebancaire.getByRib(Long.parseLong(request.getParameter("rib")));
			cb.setSoldecompte(solderetirer);
			servicebancaire.modifier(cb);
			HttpSession ses = request.getSession();
			List<CompteBancaire> comptes=servicebancaire.getAll();
			ses.setAttribute("comptes", comptes);
			request.getRequestDispatcher("/compte.jsp").forward(request, response);
		}else if(request.getParameter("validerdepo")!=null)
		{
			double soldedeposer=Double.parseDouble(request.getParameter("solde"))+Double.parseDouble(request.getParameter("soldedepo"));
			CompteBancaire cb=servicebancaire.getByRib(Long.parseLong(request.getParameter("rib")));
			cb.setSoldecompte(soldedeposer);
			servicebancaire.modifier(cb);
			HttpSession ses = request.getSession();
			List<CompteBancaire> comptes=servicebancaire.getAll();
			ses.setAttribute("comptes", comptes);
			request.getRequestDispatcher("/compte.jsp").forward(request, response);
		}else if(request.getParameter("validertrans")!=null)
		{
			//client
			Client c=clientservice.getByCin(Long.parseLong(request.getParameter("client")));
			//compte client
			Collection<CompteBancaire> clientes=c.getComptes();
			for (Iterator iterator = clientes.iterator(); iterator.hasNext();) {
				CompteBancaire compteBancaire = (CompteBancaire) iterator.next();
				compteBancaire.setSoldecompte(Double.parseDouble(request.getParameter("soldetrans"))+compteBancaire.getSoldecompte());
				servicebancaire.modifier(compteBancaire);
				
			}
			
		}
	}

}
