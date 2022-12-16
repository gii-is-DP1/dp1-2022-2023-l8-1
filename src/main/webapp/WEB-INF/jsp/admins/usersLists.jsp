<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="admins/users">
    <h2>Users</h2>


    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>BirthDate</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>
                        <spring:url value="users/{userId}" var="userUrl">
                            <spring:param name="userId" value="${user.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(userUrl)}"><c:out value="${user.username}"/></a>
                    </td>
                    <td>
                        <c:out value="${user.email}"/>
                    </td>
                    <td>
                        <c:out value="${user.birthDate}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="text-center">
        <spring:url value="/admins/users/next" var="next"/>
        <spring:url value="/admins/users/previous" var="previous"/>
        <a href="${previous}">Anterior</a> &nbsp; P&aacutegina ${currentPage} de ${size} &nbsp; <a href="${next}">Siguiente</a>
    </div>


    <%-- <table class="table-buttons">
        <tr>
            <td>


                <a href="<spring:url value="/admins/users.xml" htmlEscape="true" />">View as XML</a>
            </td>            
        </tr>
    </table> --%>
</petclinic:layout>