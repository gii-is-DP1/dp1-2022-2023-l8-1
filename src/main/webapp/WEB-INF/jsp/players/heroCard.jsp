<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">

    <h2>Hero Card Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Id</th>
            <td><b><c:out value="${player.id}"/></b></td>
        </tr>
        <tr>
            <th>Monedas de gloria</th>
            <td><c:out value="${player.glory}"/></td>
        </tr>
        <tr>
            <th>Monedas de oro</th>
            <td><c:out value="${player.gold}"/></td>
        </tr>
        <tr>
            <th>Wounds</th>
            <td><c:out value="${player.wounds}"/></td>
        </tr>
        <tr>
            <th>Evasion</th>
            <td><c:out value="${player.evasion}"/></td>
        </tr>
        <tr>
            <th>Tipo de Heroe</th>
            <td><c:out value="${player.hero}"/></td>
        </tr>
    </table>
</petclinic:layout>