<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layoutInGame pageName="games">
    <style>
		.zoom {
		  padding: 30px;
		  transition: transform .3s; /* Animation */
		  width: 250px;
		  height: 330px;
		  margin: 0 auto;
		}
		
		.zoom:hover {
		  transform: scale(1.5); /* (150% zoom - Note: if the zoom is too large, it will go outside of the viewport) */
		}
		</style>

    <div>
        <div class="phase">
            <h1>FASE ACTUAL</h1>
            <h1>ELEGIR AL L&IacuteDER</h1>
            <h4>Elige una o dos cartas para determinar quien ser&aacute el l&iacuteder de la partida</h4>
        </div>

        <c:if test="${bestBet != null}">El jugador con mayor puja es: ${bestPlayerBet.user.username} con una apuesta de ${bestBet} puntos</c:if>

        
        <c:forEach items="${players}" var="player">
            <h4 class="cardplayer">Card(s) ${player.user.username}</h4>

            <c:if test = "${player.user.username == currentPlayer.user.username}">
                
            <c:forEach items="${cartasPuja}" var="cartaPuja">
                ${cartaPuja.abilityCard.abilityType} -> ${cartaPuja.abilityCard.damage}<br>
                
            </c:forEach>
            </c:if>



        </c:forEach>
        <div class="baraja">
            <h4 class="baraja"><strong>Mi baraja</strong></h4>
            <c:forEach items="${cardInGames}" var="card">
                    <spring:url value="/games/{gameId}/chooseLeader/{cardId}" var="selectCard">
                    	<spring:param name="gameId" value="${game.id}"/>
                        <spring:param name="cardId" value="${card.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(selectCard)}"> <img width=300 class="zoom" src="<spring:url value="/resources/images/Cards/Abilities/${card.abilityCard.abilityType}.jpg" htmlEscape="true" />"> </a>
            </c:forEach>

        </div>
        <br>
        <c:choose>
            <c:when test="${bestBet > 0}">
                <spring:url value="/games/board/{gameId}" var="tablero">
                    	<spring:param name="gameId" value="${game.id}"/>
                   </spring:url>
                <a href="${fn:escapeXml(tablero)}" class="btn btn-default">Ir al tablero</a>
            </c:when>
            <c:otherwise>
                <div>
            	<spring:url value="/games/{gameId}/chooseLeader/compare" var="compare">
                    <spring:param name="gameId" value="${game.id}"/>
                </spring:url>

                <c:choose>
                    <c:when test="${todosPujan}">
                        <a href="${fn:escapeXml(compare)}" class="btn btn-default">Terminar puja</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${fn:escapeXml(compare)}" class="btn btn-default" style="pointer-events: none;">Terminar puja</a>
                    </c:otherwise>
                </c:choose>
            </div>
            </c:otherwise>
        </c:choose>



    </div>
</petclinic:layoutInGame>