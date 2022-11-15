<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="admins">
<<<<<<< HEAD

    <h2>Elige opci&oacuten para administrar</h2>
    <table>
        <th>
            <ul><h3>Administrar Usuarios</h3></ul>

        </th>

        <th>
            <ul><h3>Administrar Partidas</h3></ul>

        </th>

        <tr>
            <td>
                <ul><a  href="${fn:escapeXml('/admins/users')}" class="btn btn-default">Administrar Usuarios</a></ul>
    
            </td>
            <td>
                <ul><a  href="${fn:escapeXml('/admins/players')}" class="btn btn-default">Administrar Partidas</a></ul>
    
            </td>
        </tr>
        

    </table>
    <!-- Aquï¿½ se aï¿½adirï¿½a cuando se pueda administrar otras cosas como partidas o jugadores -->

=======
    <h2>Elige opción para administrar</h2>
    <ul><h3>Administrar Usuarios</h3></ul>
    <ul><a  href="${fn:escapeXml('/admins/users')}" class="btn btn-default">Administrar Usuarios</a></ul>
    <ul><h3>Administrar Jugadores</h3></ul>
    <ul><a  href="${fn:escapeXml('/admins/players')}" class="btn btn-default">Administrar Jugadores</a></ul>
    <!-- Aquí se añadiría cuando se pueda administrar otras cosas como partidas o jugadores -->
>>>>>>> d27ac91af18a4f2f759e274793c6f72e78655cb4
    
</petclinic:layout>