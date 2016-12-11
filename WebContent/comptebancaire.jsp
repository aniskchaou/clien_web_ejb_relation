<%@page import="tn.iit.entity.CompteBancaire"%>
<%@page import="java.util.ArrayList"%>

<%@page import="tn.iit.shop.ProduitDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="height: 152px;">
	
	
	
	

<% //afficher la liste de produits %>	
	<h1>Liste de Produit</h1>	
	<table>
		<thead>
			<td>rib</td>
			<td>Nom de client</td>
			<td>Solde de Compte</td>
			<td>Actions</td>
		</thead>
		<%
			
			if (session.getAttribute("comptes") != null) {
				ArrayList<CompteBancaire> liste = (ArrayList<CompteBancaire>) session.getAttribute("comptes");
				for (int i = 0; i < liste.size(); i++) {
					out.print("<tr><td>" + liste.get(i).getRib() + "</td><td>" + liste.get(i).getNomclient()
							+ "</td><td>" + liste.get(i).getSoldecompte()
							+ "</td><td><a href='http://localhost:8080/client_web/CompteServlet?rib="
							+ liste.get(i).getRib()
							+ "'>supprimer</a></td><td><a href='http://localhost:8080/client_web/CompteServlet?rib_modif="
							+ liste.get(i).getRib() + "'>Modifier</a></td></tr>");

				}

			}
		%>


	</table>





   <%/* afficher la formulaire */ %>
   <h1>Ajouter une Compte Bancaire</h1>
	<form action="CompteServlet" method="post">
		<br> <br>Nom de client<br> <input name="rib"
			type="hidden"> <input name="nom_client" type="text">
		<br>Solde de Compte<br> <input name="solde_compte"
			type="text"> <br> <br> <input type="submit"
			value="ajouter">
	</form>







	<%
		// afficher la formulaire de modification 
		Long rib = (Long) session.getAttribute("rib");
		String nom = (String) session.getAttribute("nom");
		Double slode = (Double) session.getAttribute("solde");
		if (nom != null) {
	%>
	<h1>Modifier une Compte Bancaire</h1>
	<form action="CompteServlet?modifier=1" method="post">
		<br> <br>Nom de client<br> <input name="rib"
			type="hidden" value="<%=rib.longValue()%>"> <input
			name="nom_client" type="text" value="<%=nom%>"> <br>Solde
		de Compte<br> <input name="solde_compte" type="text"
			value="<%=slode.doubleValue()%>"> <br> <br> <input
			type="submit" value="modifier">
	</form>

	<%
		}
	%>
	<br>
</body>
</html>