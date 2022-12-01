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
            <h1>ATAQUE</h1><!--Aquí iria el nombre de la fase en la que estamos (Ataque, mercado, reabastecimiento, espera)-->
        </div>
        <div class="enemies">
            <h4 class="enemy">Enemigo1</h4>
            <h4 class="enemy">Enemigo2</h4>
            <h4 class="enemy">Enemigo3</h4>
        </div>
        <div class="marketInGame">
            <h4 class="marketcard">CardMarket1</h4>
            <h4 class="marketcard">CardMarket2</h4>
            <h4 class="marketcard">CardMarket3</h4>
            <h4 class="marketcard">CardMarket4</h4>
            <h4 class="marketcard">CardMarket5</h4>
        </div>
        <div class="nextTurnOrPhaseButton">
            <button>Siguiente Fase/Pasar Turno</button><!--Aquí iria el boton para pasar fase o turno-->
        </div>
        <div class="yourCards"> <!--Panel de informacion personal, aparece abajo de la interfaz y todos los elementos aparecen en la misma linea-->
            <h4 class="cardplayer">CardPlayer1</h4>
            <h4 class="cardplayer">CardPlayer2</h4>
            <h4 class="cardplayer">CardPlayer3</h4>
        </div>
        <div class="pilaDesgaste">
            <h4 class="pilaDesgaste">PilaDesgaste</h4>
        </div>
        <div class="baraja">
            <h4 class="baraja">Baraja</h4>
        </div>
        <div class="enemyPile">
            <h4>EnemyPile</h4>
        </div>
        <div class="yorHeroCard">
            <h4>HeroCard</h4>
        </div>
        <div class="enemyPileDefeated">
            <h4>EnemyPileDefeated</h4>
        </div>
        <div class="marketPile">
            <h4>MarketPile</h4>
        </div>
        <div class="player">
            <h4>Player</h4>
        </div>
    </div>
</petclinic:layoutInGame>