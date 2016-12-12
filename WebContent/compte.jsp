<%@page import="tn.iit.entity.CompteBancaire"%>
<%@page import="tn.iit.entity.Client"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Comptes</h1>
<h3>Ajouter une compte</h3>
<form action="http://localhost:8080/client_web/CompteServlet?add=1" method="post">
<h3>solde</h3>
<input name="solde" type="text"/>
<h3>client</h3>
<select name="client">
<% 


if(session.getAttribute("clientss")!=null)
{
	
	List<Client>  clients=(List<Client>)session.getAttribute("clientss"); 
	for(int i=0;i<clients.size();i++)
	{
		out.print("<option value='"+clients.get(i).getCin()+"'>");
		out.print(clients.get(i).getNom()+" "+clients.get(i).getPrenom());
		out.print("</option>");
		
	}
}



%>
</select>
<input value="ajouter" type="submit">
</form>
<h3>Liste des Comptes</h3>
<table border="5"><tr><td>RIB</td><td>SOLDE</td><td>SUPPRIMER</td><td>DEPOSER</td><td>RETIRER</td><td>TRANSFERER</td></tr>
<% 


if(session.getAttribute("comptes")!=null)
{
	
	List<CompteBancaire>  comptes=(List<CompteBancaire>)session.getAttribute("comptes"); 
	for(int i=0;i<comptes.size();i++)
	{
		out.print("tr><td>"+comptes.get(i).getRib()+"</td>");
	    out.print("<td>"+comptes.get(i).getSoldecompte()+"</td>");
	    out.print("<td><a href='http://localhost:8080/client_web/CompteServlet?supp=1&rib="+comptes.get(i).getRib()+"'>supprimer</a></td>");
	    out.print("<td><a href='http://localhost:8080/client_web/CompteServlet?depo=1&rib="+comptes.get(i).getRib()+"'>deposer</a></td>");
	    out.print("<td><a href='http://localhost:8080/client_web/CompteServlet?ret=1&rib="+comptes.get(i).getRib()+"'>retirer</a></td>");
	    out.print("<td><a href='http://localhost:8080/client_web/CompteServlet?trans=1&rib="+comptes.get(i).getRib()+"'>transferer</a></td></tr>");
	}
}



%>
</table>

<% if(session.getAttribute("compteret")!=null)
{
	CompteBancaire  c=(CompteBancaire)session.getAttribute("compteret");
%>
	<h3>Retirer</h3>
<form action="http://localhost:8080/client_web/CompteServlet?validerret=1" method="post">

<input name="rib" type="hidden" value="<%= c.getRib()   %>"/>
<h3>solde</h3>
<input name="solderet" type="text"/>	
<input name="solde" type="hidden" value="<%= c.getSoldecompte()  %>"/>
<input value="retirer" type="submit">
</form>	
	
<% } %>	


<% if(session.getAttribute("comptedepo")!=null)
{
	CompteBancaire  c=(CompteBancaire)session.getAttribute("comptedepo");
%>
	<h3>Deposer</h3>
<form action="http://localhost:8080/client_web/CompteServlet?validerdepo=1" method="post">

<input name="rib" type="hidden" value="<%= c.getRib()   %>"/>
<h3>solde</h3>
<input name="solde" type="hidden" value="<%= c.getSoldecompte()  %>"/>
<input name="soldedepo" type="text" />	
<input value="deposer" type="submit">
</form>	
	
<% } %>	

<% if(session.getAttribute("comptetrans")!=null)
{
	CompteBancaire  c=(CompteBancaire)session.getAttribute("comptetrans");
%>
	<h3>Transferer</h3>
<form action="http://localhost:8080/client_web/CompteServlet?validertrans=1" method="post">

<input name="rib" type="hidden" value="<%= c.getRib()   %>"/>
<h3>solde</h3>
<input name="soldetrans" type="text" />	

<select name="client">
<% 


if(session.getAttribute("clientes")!=null)
{
	
	List<Client>  clients=(List<Client>)session.getAttribute("clientes"); 
	for(int i=0;i<clients.size();i++)
	{
		out.print("<option value='"+clients.get(i).getCin()+"'>");
		out.print(clients.get(i).getNom()+" "+clients.get(i).getPrenom());
		out.print("</option>");
		
	}
}



%>
</select>
<input value="transferer" type="submit">


</form>	
	
<% } %>

</table>
</body>
</html>