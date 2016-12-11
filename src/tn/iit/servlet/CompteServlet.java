package tn.iit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.iit.entity.CompteBancaire;
import tn.iit.service.CompteBancaireService;
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
	}

	@EJB
	CompteBancaireServiceLocal service;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// liste de comptes
		List<CompteBancaire> comptes = service.getAll();
		HttpSession ses = request.getSession();
		ses.setAttribute("comptes", comptes);
		request.getRequestDispatcher("/comptebancaire.jsp").forward(request, response);

		if (request.getParameter("rib") != null) {

			// supprimer la compte
			CompteBancaire cb = service.getByRib(Long.parseLong(request.getParameter("rib")));
			service.supprimer(cb);
			request.getRequestDispatcher("/comptebancaire.jsp").forward(request, response);
		} else if (request.getParameter("rib_modif") != null) {
			
			
			// retourner la page de modification
			CompteBancaire compte = service.getByRib(Long.parseLong(request.getParameter("rib_modif")));
			HttpSession session = request.getSession();
			session.setAttribute("nom", compte.getNomclient());
			session.setAttribute("rib", compte.getRib());
			session.setAttribute("solde", compte.getSoldecompte());
			request.getRequestDispatcher("/comptebancaire.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ajouter un produit
		CompteBancaire cb = new CompteBancaire(request.getParameter("nom_client"),
		Double.parseDouble(request.getParameter("solde_compte")));
		service.ajouter(cb);
		List<CompteBancaire> comptes = service.getAll();
		HttpSession session = request.getSession();
		session.setAttribute("comptes", comptes);
		request.getRequestDispatcher("/comptebancaire.jsp").forward(request, response);

		if (request.getParameter("modifier") != null) {
			// valider la modification
			CompteBancaire compte = service.getByRib(Long.parseLong(request.getParameter("rib")));
			compte.setNomclient(request.getParameter("nom_client"));
			compte.setSoldecompte(Double.parseDouble(request.getParameter("solde_compte")));
			service.modifier(compte);
			request.getRequestDispatcher("/comptebancaire.jsp").forward(request, response);

		}

	}

}
