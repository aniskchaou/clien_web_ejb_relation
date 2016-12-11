package tn.iit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tn.iit.shop.ProduitDto;
import tn.iit.shop.ShopBeanLocal;

/**
 * Servlet implementation class ProduitServlet
 */
@WebServlet("/ProduitServlet")
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int num = 0;
	ArrayList<ProduitDto> liste_produit = new ArrayList<ProduitDto>();

	@EJB
	ShopBeanLocal shop;

	public ProduitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		shop.achatProduit(request.getParameter("nom_produit"),
				Integer.parseInt(request.getParameter("qantite_produit")),
				Double.parseDouble(request.getParameter("prix_produit")));

		List<ProduitDto> listeDeProduit = shop.listProduitAchete();

		HttpSession session = request.getSession();

		session.setAttribute("produits", listeDeProduit);

		// request.setAttribute("produits", liste_de_produit);
		request.getRequestDispatcher("/produit.jsp").forward(request, response);

	}

}
