<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">

    <h2>Player Cards List</h2>


    <table class="table table-striped">
        <tr>
            <th>Player Id</th>
            <td><b><c:out value="${player.id}"/></b></td>
        </tr>
        <c:forEach var="abilityCard" items="{$player.abilityHand}">
            <tr>
                <th>CardsInHand List</th>
                <td><c:out value="${abilityCard.damage}"/></td>
            </tr>
        </c:forEach>
        <c:forEach var="marketCard" items="{player.marketHand}">
            <tr>
                <th>CardsInMarket List</th>
                <%--<td><c:out value="${marketCard.id}"/></td>--%>
            </tr>
        </c:forEach>
    </table>

</petclinic:layout>