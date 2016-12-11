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
<body style="height: 152px; ">
<h1>Liste de Produit</h1>
<table>
<thead><td>nom produit </td><td>quantite</td><td>prix</td></thead>
<%  

 // HttpSession sess=request.getSession(false); 
  if(session.getAttribute("produits")!=null)
  {
  ArrayList<ProduitDto> liste=(ArrayList<ProduitDto>)session.getAttribute("produits");
   for(int i=0; i<liste.size();i++)
   {
   out.print("<tr><td>"+liste.get(i).getNom()+"</td><td>"+liste.get(i).getQte()+"</td><td>"+liste.get(i).getPrix()+"</td></tr>");
   
   
   }
  
  }
   
  %>


</table>


<h1>Ajouter un Produit</h1>
<form action="http://localhost:8080/client_web/ProduitServlet" method="post">
		<br>Ajouter un produit<br><br><br>nom produit <br><input name="nom_produit"><br>quantite produit<br>

		<input name="qantite_produit"><br>prix produit<br>

		<input name="prix_produit">

		<br><br><input type="submit" value="ajouter">
	</form><br></body>
</html>