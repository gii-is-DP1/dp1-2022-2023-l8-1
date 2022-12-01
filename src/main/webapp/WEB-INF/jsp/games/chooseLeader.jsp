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


        
        <c:forEach items="${players}" var="player">
            <h4 class="cardplayer">Card(s) ${player.user.username}</h4>

        </c:forEach>
        <div class="baraja">
            <h4 class="baraja"><strong>Mi baraja</strong></h4>
            <c:forEach items="${cardInGames}" var="card">
                <div >
                    <spring:url value="chooseLeader/{card}" var="selectCard">
                        <spring:param name="card" value="${card.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(selectCard)}" class="btn btn-default">${card.abilityCard.abilityType} -> ${card.abilityCard.damage}</a>
                </div>
            </c:forEach>
            <c:forEach items="${cartasPuja}" var="cartaPuja">
                ${cartaPuja.abilityCard.abilityType}
                
            </c:forEach>
        </div>
        <br>

    </div>
</petclinic:layoutInGame>