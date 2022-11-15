<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="social">
    <h2>Friends</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Friend Username</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${friendsL.friendList}" var="friends">
            <tr>
                <td>
                    <c:out value="${friends.friendUsername}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>