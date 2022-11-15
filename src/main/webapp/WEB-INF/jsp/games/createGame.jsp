<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
    
    <!-- <h2>
        <c:if test="${owner['new']}">New </c:if> Owner
    </h2> -->
    <form:form modelAttribute="game" class="form-horizontal" id="add-game-form">
        <div class="form-group has-feedback">

            <petclinic:inputField label="Min players" name="minPlayers"/>
            <petclinic:inputField label="Max players" name="maxPlayers"/>
         
            <input class="form-check-input" type="checkbox" value="" path="${hasScenes}"  id="flexCheckChecked" checked>
            <label class="form-check-label" for="flexCheckChecked">
              Scenes
            </label>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class = 'btn btn-default' type="submit">Create Game</button>
            </div>
        </div>
    </form:form>


</petclinic:layout>