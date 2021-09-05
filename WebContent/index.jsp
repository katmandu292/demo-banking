<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  http-equiv="refresh" content="0; URL="giveMeASession/" -->
<title>E-Banking Index Page</title>
	<!-- reference our style sheet -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" /></head>
</head>
<body>

<table width="750" border="0">
   <tr>
      <td width="14">&nbsp;</td>
	  <td width="180">&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>
		<c:if test="${userName == anonymousUser}">
		    Welcome. Please Login
		</c:if>
		<c:if test="${userName != anonymousUser}">
      	    Hello,&nbsp;
			<security:authentication property="principal.username" />
<!--  	    Hello,&nbsp;${userName} -->
		</c:if>
	  </td>
	  <td>Date/Time:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${userName == anonymousUser}">
			<a href="${pageContext.request.contextPath}/giveMeASession">Login</a>
		</c:if>
		<c:if test="${userName != anonymousUser}">
			<form:form action="${pageContext.request.contextPath}/logout"  method="POST">
			<input type="submit" value="Logout" class="add-button" />
			</form:form>
		</c:if>
      </td>
	  <td>&nbsp;</td>
   </tr>
   <tr>
      <td colspan="2"><h1>&nbsp;&nbsp;eBanking</h1></td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td><h3>Conturi&nbsp;</h3></td>
	  <td><h3>Opera&#355;ii&nbsp;</h3></td>
	  <td><h3>Rapoarte&nbsp;</h3></td>
	  <td><h3>Aprobare&nbsp;</h3></td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td>
		<c:if test="${userName == anonymousUser}">
		    Administrare Cont
		</c:if>
		<c:if test="${userName != anonymousUser}">
	     <a href="${pageContext.request.contextPath}/bank/manageAccts">
	     Administrare Cont
	     </a>
		</c:if>
	     &nbsp;</td>
	  <td>Transfer&nbsp;</td>
	  <td>Rapoarte&nbsp;</td>
	  <td>Aprobare&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td>
		<c:if test="${userName != anonymousUser}">
<a href="${pageContext.request.contextPath}/bank/showAccounts">Vizualizare Conturi</a></td>
		</c:if>
		<c:if test="${userName == anonymousUser}">
Vizualizare Conturi&nbsp;
		</c:if>
	  </td>
	  <td>Opera&#355;ii &#238;n curs&nbsp;</td>
	  <td>Extras Cont&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>
		<c:if test="${userName != anonymousUser}">
<a href="${pageContext.request.contextPath}/bank/payMoney">Alimentare Conturi</a>&nbsp;
		</c:if>
		<c:if test="${userName == anonymousUser}">
Alimentare Conturi
		</c:if>
	  </td>
	  <td>
Lista de Tranzactii&nbsp;
	  </td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td colspan="6">${message}&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td colspan="3">Autor: C&#259;t&#259;lin (Catman) Dumitra&#351;cu (2021)&nbsp;</td>
   </tr>

</table>
</body>
</html>