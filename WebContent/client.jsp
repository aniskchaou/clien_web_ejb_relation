
<%@page import="tn.iit.entity.Client"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Client</h1>

<h3>Ajouter un CLient</h3>
<form method="post" action="http://localhost:8080/client_web/ClientServlet?add=1">

<h3>nom</h3>
<input name="nom" type="text"/>

<h3>prenom</h3>

<input name="prenom" type="text"/>
<h3>adesse</h3>
<input name="adresse" type="text"/>

<input value="ajouter" type="submit"/>
</form>
<h3>Liste de Clients</h3>
<table border="5">
<tr><td>CIN</td><td>NOM</td><td>PRENOM</td><td>ADRESSE</td><td>SUPPRIMER</td><td>MODIFIER</td><td>GERER COMPTE</td></tr>

<% 


if(session.getAttribute("clientss")!=null)
{
	
	List<Client>  clientss=(List<Client>)session.getAttribute("clientss"); 
	for(int i=0;i<clientss.size();i++)
	{
		out.print("<tr><td>"+clientss.get(i).getCin()+"</td>");
	    out.print("<td>"+clientss.get(i).getNom()+"</td>");
	    out.print("<td>"+clientss.get(i).getPrenom()+"</td>");
	    out.print("<td>"+clientss.get(i).getAdresse()+"</td>");
	    out.print("<td><a href='http://localhost:8080/client_web/ClientServlet?supp=1&cin="+clientss.get(i).getCin()+"'>supprimer</a></td>");
	    out.print("<td><a href='http://localhost:8080/client_web/ClientServlet?modif=1&cin="+clientss.get(i).getCin()+"'>modifier</a></td>");
	    out.print("<td><a href='http://localhost:8080/client_web/CompteServlet'>gerer compte</a></td></tr>");
	}
}



%>
</table>
<%
if(session.getAttribute("clientedit")!=null)
{
Client c=(Client)session.getAttribute("clientedit");	
%>
<h1>modifier client</h1>
<form method="post" action="http://localhost:8080/client_web/ClientServlet?validermodif=1">
<input name="cin" type="hidden" value="<%= c.getCin() %>" />
<h3>nom</h3>
<input name="nom" type="text" value="<%= c.getNom() %>"/>

<h3>prenom</h3>

<input name="prenom" type="text" value="<%= c.getPrenom() %>" />
<h3>adesse</h3>
<input name="adresse" type="text" value="<%= c.getAdresse() %>" />

<input value="ajouter" type="submit"/>
</form>
<%
}
%>
</table>
</body>
</html>