<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="admins/users">

    <h2>User Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Username</th>
            <td><b><c:out value="${user.username}"/></b></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${user.email}"/></td>
        </tr>
        <tr>
            <th>birthDate</th>
            <td><c:out value="${user.birthDate}"/></td>
        </tr>
    </table>
    <spring:url value="delete/{userId}" var="deleteUrl">
    	<spring:param name="userId" value="${user.id}"></spring:param>
    </spring:url> 
	<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-danger">Delete User</a>
    <%-- <spring:url value="{ownerId}/edit" var="editUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url> 
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Owner</a>--%>

</petclinic:layout>
