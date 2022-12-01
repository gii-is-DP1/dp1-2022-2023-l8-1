<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">

    <h2>Enemies Actives Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Game Id</th>
            <td><b><c:out value="${game.id}"/></b></td>
        </tr>
        <c:forEach items="${game.marketPile}" var="market">
            <tr>
                <th>market card Id</th>
                <td><b><c:out value="${market.id}"/></b></td>
            </tr>
           
        </c:forEach>
    </table>
</petclinic:layout>