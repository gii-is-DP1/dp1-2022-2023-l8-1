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
        <div class="title">
            <h1>ELIGE UNA CARTA</h1>
            <h4>Elige una carta de la pila de desgaste para recuperarla y ponerla en tu mano</h4>
        </div>

        <div class="pila">
            <h4 class="pila"><strong>Mi pila de desgaste</strong></h4>
            <c:forEach items="${discardPile}" var="card">

                    <spring:url value="/games/{gameId}/selectDiscard/{cardId}" var="selectCard">
                    	<spring:param name="gameId" value="${game.id}"/>
                        <spring:param name="cardId" value="${card.abilityCard.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(selectCard)}"> <img width=300 class="zoom" src="<spring:url value="/resources/images/Cards/Abilities/${card.abilityCard.abilityType}.jpg" htmlEscape="true" />"> </a>
            </c:forEach>
            <c:forEach items="${marketDiscardPile}" var="card">

                    <spring:url value="/games/{gameId}/selectMarketDiscard/{cardId}" var="selectCard">
                    	<spring:param name="gameId" value="${game.id}"/>
                        <spring:param name="cardId" value="${card.marketCard.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(selectCard)}"> <img width=300 class="zoom" src="<spring:url value="/resources/images/Cards/Shop/${card.marketCard.type}.jpg" htmlEscape="true" />"> </a>
            </c:forEach>

        </div>
        <br>

    </div>
</petclinic:layoutInGame>