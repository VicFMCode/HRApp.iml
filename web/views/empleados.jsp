<%@ page import="java.util.List" %>
<%@ page import="com.hrmanagement.model.Empleado" %>
<%@ include file="WEB-INF/header.jsp" %>

<h2>Lista de Empleados</h2>
<a href="empleado?action=new">Agregar Nuevo Empleado</a>
<table border="1">
    <tr>
        <th>ID</th><th>Nombre</th><th>Usuario</th><th>Teléfono</th><th>Correo</th><th>Acciones</th>
    </tr>
    <%
        List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
        for (Empleado emp : empleados) {
    %>
    <tr>
        <td><%= emp.getIdEmpleado() %></td>
        <td><%= emp.getNombrePersona() %></td>
        <td><%= emp.getUsuario() %></td>
        <td><%= emp.getNumeroTelefono() %></td>
        <td><%= emp.getCorreoInstitucional() %></td>
        <td>
            <a href="empleado?action=edit&id=<%= emp.getIdEmpleado() %>">Editar</a> |
            <a href="empleado?action=delete&id=<%= emp.getIdEmpleado() %>" onclick="return confirm('¿Eliminar?')">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>

<%@ include file="WEB-INF/footer.jsp" %>
