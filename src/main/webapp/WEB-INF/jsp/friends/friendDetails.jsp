<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="friends">

    <h2>Friend Information</h2>


    <table class="table table-striped">
        <tr>
            <th>User1</th>
            <td><b><c:out value="${friend.user1}"/></b></td>
        </tr>
        <tr>
            <th>User2</th>
            <td><c:out value="${friend.user2}"/></td>
        </tr>
        <tr>
            <th>Friendship</th>
            <td><c:out value="${friend.friendState}"/></td>
        </tr>
        
    </table>
       
	     <spring:url value="/delete/{friendId}" var="deleteUrl">
        <spring:param name="friendId" value="${friend.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete Friend</a>
    <br/>
    <br/>
    <br/>


</petclinic:layout>