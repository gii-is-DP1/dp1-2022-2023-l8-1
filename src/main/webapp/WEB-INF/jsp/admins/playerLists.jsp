<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="admins/players">
    <h2>Players</h2>

    <table id="playersTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Game id</th>
            <th>Hero Type</th>
            <th>Glory</th>
            <th>Gold</th>
            <th>Wounds</th>
            <th>Evasion</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${players}" var="player">
            <tr>
            	<td>
                    <c:out value="${player.game.id}"/>
                </td>
                <td>
                    <c:out value="${player.hero}"/>
                </td>
                <td>
                    <c:out value="${player.glory}"/>
                </td>
                <td>
                    <c:out value="${player.gold}"/>
                </td>
                <td>
                    <c:out value="${player.wounds}"/>
                </td>
                <td>
                    <c:out value="${player.evasion}"/>
                </td>
                <td>
                	<spring:url value="/admins/players/delete/{playerId}" var="deleteUrl">
							<spring:param name="playerId" value="${player.id}"></spring:param>
						</spring:url> <a href="${fn:escapeXml(deleteUrl)}"
						class="btn btn-danger">Delete Player</a>
					</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/users.xml" htmlEscape="true" />">View as XML</a>
            </td>   
    
        </tr>
        <tr>
            <td>
                <a class="btn btn-default" href='<spring:url value="/admins/createPlayerForm" htmlEscape="true"/>'>Add Player</a>
            </td>     
        </tr>
    </table>
</petclinic:layout>