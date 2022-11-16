<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="players">
    
    <!-- <h2>
        <c:if test="${owner['new']}">New </c:if> Owner
    </h2> -->
    <form:form modelAttribute="player" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <!-- <petclinic:inputField label="Hero Type" name="hero"/> -->
            <petclinic:selectField name="hero" label="Hero Type" names="${types}" size="1"/>
            <petclinic:inputField label="Gold" name="gold"/>
            <petclinic:inputField label="Wounds" name="wounds"/>

            <petclinic:inputField label="Glory" name="glory"/>

            
            <input class="form-check-input" type="checkbox" value="" path="${evasion}"  id="flexCheckChecked" checked>
            <label class="form-check-label" for="flexCheckChecked">
              Evasion
            </label>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class = 'btn btn-default' type="submit">Add Player</button>
                <!-- <c:choose>
                    <c:when test="${owner['new']}">
                        <button class="btn btn-default" type="submit">Add Owner</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Owner</button>
                    </c:otherwise>
                </c:choose> -->
            </div>
        </div>
    </form:form>
</petclinic:layout>