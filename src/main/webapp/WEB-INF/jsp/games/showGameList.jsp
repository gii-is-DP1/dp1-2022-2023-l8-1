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
            <th>Number of Players</th>
            <th>State</th>
            <th>User creation</th>
            <th>Join</th>
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
                    <c:choose>
                        <c:when test="${game.hasScenes=='true'}">
                            <c:out value="${'yes'}"></c:out>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${'no'}"/>
                        </c:otherwise>
                    </c:choose>
                    <!--<c:out value="${game.hasScenes}"/>-->
                </td>
                <td>
                    <c:out value="${game.maxPlayers}"/>
                </td>
                <td>
                    <c:out value="${game.minPlayers}"/>
                </td>
                <td>
                    <c:out value="${fn:length(game.player)}"></c:out>
                </td>
                <td>
                    <c:out value="${game.state}"/>
                </td>
                <td>
                    <c:out value="${game.username}"/>
                </td>
                <td>
                <c:if test="${game.state=='LOBBY'}">
                    <spring:url value="/games/{gameId}/join" var="joinUrl">
                        <spring:param name="gameId" value="${game.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(joinUrl)}">Join game</a>
                </c:if>
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
