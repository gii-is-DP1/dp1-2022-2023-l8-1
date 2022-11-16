<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="lobby">
    
    <h1 style="text-align:center">LOBBY</h1>

    <h3>Lista de jugadores</h3>

    <c:forEach items="${players}" var="player">
        <li>${player.user.username}</li>
    </c:forEach>
    <br>
    <a style="margin:50;" class="btn btn-default">Invitar jugadores</a>
    <ul style="text-align:center"><a class="btn btn-default">Empezar partida</a></ul>

</petclinic:layout>