<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/css/html; charset=ISO-8859-1">
<title>E-Banking Self Page</title>
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" /></head>

<body>
<table width="750" border="0">
   <tr>
      <td width="14">&nbsp;</td>
	  <td width="180">&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>Hello,&nbsp;${userName}</td>
	  <td>Date/Time:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/login?logout">Logout</a>
<!--  		 form:form action="${pageContext.request.contextPath}/logout"  method="GET">
			<input type="submit" value="Logout" class="add-button" />
			  form:form  -->
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
	  <td><a href="${pageContext.request.contextPath}/bank/manageAccts">Administrare Cont</a>&nbsp;</td>
	  <td>Transfer&nbsp;</td>
	  <td>Rapoarte&nbsp;</td>
	  <td>Aprobare&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td><a href="${pageContext.request.contextPath}/bank/showAccounts">Vizualizare Conturi</a>&nbsp;</td>
	  <td>Opera&#355;ii &#238;n curs&nbsp;</td>
	  <td>Extras Cont&nbsp;</td>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
   </tr>

   <tr>
      <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  <td><a href="${pageContext.request.contextPath}/bank/payMoney">Alimentare Conturi</a>&nbsp;</td>
	  <td><a href="${pageContext.request.contextPath}/bank/showTransacts">Lista de Tranzactii</a>&nbsp;</td>
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
<form:form action="${pageContext.request.contextPath}/bank/sendNewAcctCreation" 
		modelAttribute="new_account"   method="POST">
      <td><label>New Account&#39;s Number:&nbsp;</label></td>
      <td><form:input id="acctId" path="accountIban" /></td>
      <td colspan="2"><input type="submit" value="New Account Request" class="save" /></td>
	  <td>&nbsp;</td>
</form:form>
   </tr>

   <tr>
      <td>&nbsp;</td>
      <td colspan="5">
        <table>
          <tr>
            <td>Account NR&nbsp;</td>
            <td>Owner&nbsp;</td>
            <td>IBAN&nbsp;</td>
            <td>Sold&nbsp;</td>
            <td>Status&nbsp;</td>
          </tr>
<c:forEach var="crtAccount" items="${bankAccountsList}">
          <tr>
            <td>${crtAccount.accountId}&nbsp;</td>
            <td>${crtAccount.ownerName}&nbsp;</td>
            <td>${crtAccount.accountIban}&nbsp;</td>
            <td>${crtAccount.finalSum}&nbsp;</td>
            <td>${crtAccount.acctStatus}&nbsp;</td>
          </tr>
</c:forEach>
        </table>
      </td>
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
