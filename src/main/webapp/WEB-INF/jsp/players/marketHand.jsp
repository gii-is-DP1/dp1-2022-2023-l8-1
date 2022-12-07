<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">

    <h2>Ability Pile Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Player Id</th>
            <td><b><c:out value="${player.id}"/></b></td>
        </tr>
        <c:forEach items="${player.marketHand}" var="hand">
            <tr>
                <th>Card Id</th>
                <td><b><c:out value="${hand.id}"/></b></td>
            </tr>
            <tr>
                <th>Card damage</th>
                <td><b><c:out value="${hand.marketCard.damage}"/></b></td>
            </tr>
            <tr>
                <th>Card profiency1</th>
                <td><b><c:out value="${hand.marketCard.profiency1}"/></b></td>
            </tr>
           
        </c:forEach>
        <tr>
            <th>Cards left</th>
            <td><c:out value="${fn:length(player.marketHand)}"></c:out></td>
        </tr>
    </table>
</petclinic:layout>