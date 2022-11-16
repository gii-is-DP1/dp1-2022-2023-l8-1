<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<petclinic:layout pageName="users">
    <h2>Users</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">User name</th>         
            <th style="width: 150px;">Actions</th>
        </tr>
        </thead>
        <tbody>
        <sec:authentication var="name" property="name" />
        <c:forEach items="${users}" var="user">
        <c:if test = "${name != user.username}">
            <tr>
                <td>
                    <c:out value="${user.username}"/>
                </td>

                <th>
                 <spring:url value="friendList/new/{userId}" var="editUrl">
                    <spring:param name="userId" value="${user.id}"/></spring:url>
                    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Add Friend</a>
                </th>
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>