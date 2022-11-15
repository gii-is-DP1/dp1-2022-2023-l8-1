<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    <h2>Games</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Start time</th>
            <th>End Time</th>
            <th>Has scenes</th>
            <th>Max players</th>
            <th>Min Players</th>
            <th>Current Players</th>
            <th>State</th>
            <th>User creation</th>
            <th>Winner</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${games}" var="game">
            <tr>
                
                <td>
                    <c:out value="${game.startTime}"/>
                </td>
                <td>
                    <c:out value="${game.endTime}"/>
                </td>
                <td>
                    <c:out value="${game.hasScenes}"/>
                </td>
                <td>
                    <c:out value="${game.maxPlayers}"/>
                </td>
                <td>
                    <c:out value="${game.minPlayers}"/>
                </td>
                <td>
                    <c:out value="${game.players}"/>
                </td>
                <td>
                    <c:out value="${game.state}"/>
                </td>
                <td>
                    <c:out value="${game.user.username}"/>
                </td>
                <td>
                    <c:out value="player ${game.winner.id}"/>
                </td>
                
                
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a class="btn btn-default" href='<spring:url value="/games/new" htmlEscape="true"/>'>Create game</a>
            </td>
        </tr>
    </table>
</petclinic:layout>
