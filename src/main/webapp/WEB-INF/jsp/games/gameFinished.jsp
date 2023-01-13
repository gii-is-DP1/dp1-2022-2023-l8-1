<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
<h4>EL GANADOR ES</h4>
<c:choose>
    <c:when test="${winner==null}">
        <c:out value="NADIE, HABEIS PERDIDO LA PARTIDA :C"></c:out>
    </c:when>
    <c:otherwise>
        <c:out value="${winner.user.username} con un total de ${winner.glory} puntos de gloria"></c:out>
    </c:otherwise>
</c:choose>
</petclinic:layout>