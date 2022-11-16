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
    <form:form modelAttribute="game" class="form-horizontal" id="add-game-form" accept-charset="utf-8">
        <div class="form-group has-feedback">
            <!--
            <petclinic:inputField label="Min players" name="minPlayers"/>
            <petclinic:inputField label="Max players" name="maxPlayers"/>
            type="number"
            -->


            <table>
                <colgroup>
                    <col span="3">
                    <col>
                </colgroup>
                <tr>
                    <th>Min players</th>
                    <th>&nbsp;&nbsp;</th>
                    <th><input type="number" label="Min players" name="minPlayers" value="2" min="2" max="4"/></th>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                </tr>
                <tr>
                    <th>Max players</th>
                    <th>&nbsp;&nbsp;</th>
                    <th><input type="number" label="Max players" name="maxPlayers" value="4" min="2" max="4"/></th>
                </tr>
            </table>
            <br/>
            <input class="form-check-input" type="checkbox" path="${hasScenes}" name="hasScenes" id="flexCheckChecked">
            
            
              Scenes
             
            </label>
            <hr>  
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class = 'btn btn-default' type="submit">Create Game</button>
            </div>
        </div>
    </form:form>


</petclinic:layout>