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
				<h1 class="fase_actual">ATAQUE</h1>
				<!--Aquí iria el nombre de la fase en la que estamos (Ataque, mercado, reabastecimiento, espera)-->
			</div>

			<div class="enemies">
				<div class="enemy">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4>Enemy1</h4>
				</div>
				<div class="enemy">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4>Enemy2</h4>
				</div>
				<div class="enemy">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4>Enemy3</h4>
				</div>
			</div>

			<div class="market">
				<div class="marketcard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="marketcard">CardMarket1</h4>
				</div>
				<div class="marketcard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="marketcard">CardMarket2</h4>
				</div>
				<div class="marketcard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="marketcard">CardMarket3</h4>
				</div>
				<div class="marketcard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="marketcard">CardMarket4</h4>
				</div>
				<div class="marketcard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="marketcard">CardMarket5</h4>
				</div>
			</div>

			<div class="nextTurn">
				<button class="nextPhase btn btn-primary">SIGUIENTE
					FASE/TURNO</button>
				<!--Aquí iria el boton para pasar fase o turno-->
			</div>

			<div class="yourCards">
				<!--Panel de informacion personal, aparece abajo de la interfaz y todos los elementos aparecen en la misma linea-->
				<div class="myCard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="cardplayer">CardPlayer1</h4>
				</div>
				<div class="myCard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="cardplayer">CardPlayer2</h4>
				</div>
				<div class="myCard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="cardplayer">CardPlayer3</h4>
				</div>
				<div class="myCard">
					<img
						src="<spring:url value="/resources/images/no-time-for-heroes.png" htmlEscape="true" />">
					<h4 class="cardplayer">CardPlayer4</h4>
				</div>

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
					<h4>HeroCard</h4>
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
				<h4>Player</h4>
			</div>
		</div>
	</body>
</petclinic:layoutInGame>