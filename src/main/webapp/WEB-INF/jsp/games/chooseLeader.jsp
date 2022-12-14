<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layoutInGame pageName="games">
    <div>
        <div class="phase">
            <h1>FASE ACTUAL</h1>
            <h1>ELEGIR AL L&IacuteDER</h1>
            <h4>Elige una o dos cartas para determinar quien ser&aacute el l&iacuteder de la partida</h4>
        </div>

        <c:if test="${bestBet > 0}">El jugador con mayor puja es: ${bestPlayerBet.user.username} con una apuesta de ${bestBet} puntos</c:if>

        
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
                <div >
                    <spring:url value="/games/{gameId}/chooseLeader/{cardId}" var="selectCard">
                    	<spring:param name="gameId" value="${game.id}"/>
                        <spring:param name="cardId" value="${card.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(selectCard)}" class="btn btn-default">${card.abilityCard.abilityType} -> ${card.abilityCard.damage}</a>
                </div>
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
            	<spring:url value="/games/{gameId}/chooseLeader/compare" var="compare">
                    <spring:param name="gameId" value="${game.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(compare)}" class="btn btn-default">Terminar puja</a>

            </c:otherwise>
        </c:choose>



    </div>
</petclinic:layoutInGame>