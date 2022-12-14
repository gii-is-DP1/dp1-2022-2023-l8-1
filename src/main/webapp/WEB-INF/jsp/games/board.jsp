<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layoutInGame pageName="games">
    <body class="background">
		<div class="board">
			<div class="phase">
				<h1 class="fase">FASE ACTUAL</h1>
				<h1 class="fase_actual">${turn.type}</h1>
				<h1 >Turno de ${turn.player.user.username}</h1>
				<!--Aquí iria el nombre de la fase en la que estamos (Ataque, mercado, reabastecimiento, espera)-->
			</div>

			<div class="enemies">
			<c:forEach items="${game.monsterField}" var="enemy">
					<div class="enemy">
						<img src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
						<h4>Endurance: <c:out value=" ${enemy.enemy.endurance}"/></h4>
					</div>
				</c:forEach>
			</div>

			<div class="market">
				<c:forEach items="${game.sale}" var="marketCard">
					<div class="marketcard">
						<img src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
						<h4 class="marketcard">Price:<c:out value=" ${marketCard.marketCard.price}"/></h4>
					</div>
				</c:forEach>
				<%-- <div class="marketcard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="marketcard">CardMarket1</h4>
				</div> --%>
			</div>

			<div class="yourCards">
				<!--Panel de informacion personal, aparece abajo de la interfaz y todos los elementos aparecen en la misma linea-->
				<c:forEach items="${player.abilityHand}" var="cards">
				<div class="myCard">
					<img src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="cardplayer">Damage:<c:out value=" ${cards.abilityCard.damage}"/></h4>
				</div>
				</c:forEach>
				<%-- <div class="myCard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="cardplayer">CardPlayer1</h4>
				</div> --%>

			</div>
			<div class="nextTurn">
				<spring:url value="/games/board/{gameId}/next" var="nextTurn">
					<spring:param name="gameId" value="${game.id}"></spring:param>
				</spring:url>

				<c:if test="${isMyTurn}">
					<a href="${fn:escapeXml(nextTurn)}" class="nextPhase btn btn-primary">SIGUIENTE
						FASE/TURNO</a>
				</c:if>
				<c:if test="${!isMyTurn}">
					<a href="${fn:escapeXml(nextTurn)}" class="nextPhase btn btn-primary" style="pointer-events: none;">SIGUIENTE
						FASE/TURNO</a>
				</c:if>
				
			</div>
			<div class="yourCards">
				<div class="myCard pilaDesgaste">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="pilaDesgaste">PilaDesgaste</h4>
				</div>
				<div class="myCard baraja">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="baraja">Baraja</h4>
				</div>
				<div class="myCard yourHeroCard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4><c:out value="${player.hero}"/></h4>
				</div>
				<div class="myCard enemyPileDefeated">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4>EnemyPileDefeated</h4>
				</div>
			</div>

			<div class="enemyPile">
				<h4>EnemyPile</h4>
			</div>
			<div class="marketPile">
				<h4>MarketPile</h4>
			</div>
			<div class="player">
				<h4>Other Player</h4>
			</div>
			<div class="player">
				<h4>Other Player</h4>
			</div>
		</div>
	</body>
</petclinic:layoutInGame>