<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="lobby">




    
    <h1 style="text-align:center">LOBBY ${gameId}</h1>

	<h1>ELIGE TU H&EacuteROE</h1>

	<c:forEach items="${heroTypes}" var="heroType">
		<spring:url value="lobby/selectHero/{heroType}" var="selectHero">
		<spring:param name="gameId" value="${gameId}"/>
		<spring:param name="heroType" value="${heroType}"/>
	</spring:url>
	<a href="${fn:escapeXml(selectHero)}" class="btn btn-default">${heroType}</a>
	</c:forEach>

    <h3>Lista de jugadores</h3>

    <c:forEach items="${players}" var="player">
        <li>${player.user.username} - ${player.hero.toString().split("_")[0]} &nbsp ${player.hero.toString().split("_")[1]}</li>
    </c:forEach>
    <br>
    <h2>Invitaciones a jugar</h2>
		<div class="col-md-4">
			<table id="friendsTable" class="table table-striped">
		        <thead>
		        <tr>
		            <th style="width: 150px;">Amigo</th>
		            <th style="width: 150px;">Acciones</th>
		        </tr>
		        </thead>
		        <tbody>
		        	 <c:forEach items="${friends}" var="friend">
				        <c:if test = "${null != friend.receiver}">
					        <c:if test = "${friend.sender.username!=userMain}">
					        	<tr>
					        		<td><c:out value="${friend.sender.username}"/></td>
					        		<td>
							        	<!-- <spring:url value="play/join/{boardId}" var="joinUrl">
					                    	<spring:param name="boardId" value="${friend.sender.board.id}"/>
					                    	<spring:param name="friendId" value="${friend.id}"/></spring:url>
					                    <a href="${fn:escapeXml(joinUrl)}" class="btn btn-default">Unirte</a> -->
							        	
							        	<spring:url value="friendList/deleteInvitation/{friendId}" var="deleteUrl">
					                    <spring:param name="friendId" value="${friend.id}"/></spring:url>
					                    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Eliminar</a>
					                </td>
					            </tr>
					        </c:if>
				        </c:if>
				        
				     </c:forEach>
		        </tbody>
		    </table>
		</div>
    <spring:url value="start" var="startUrl">
        <spring:param name="gameId" value="${game.id}"/>
    </spring:url>
    <!-- ${message} -->
    <ul style="text-align:center"><a href="${fn:escapeXml(startUrl)}" class="btn btn-default">Empezar partida</a></ul>

</petclinic:layout>