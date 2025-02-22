<%@ page import="java.util.List" %>
<%@ page import="com.hrmanagement.model.Cargo" %>
<%@ include file="WEB-INF/header.jsp" %>

<h2>Lista de Cargos</h2>
<a href="cargo?action=new">Agregar Nuevo Cargo</a>
<table border="1">
    <tr>
        <th>ID</th><th>Cargo</th><th>Departamento</th><th>Jefatura</th><th>Acciones</th>
    </tr>
    <%
        List<Cargo> cargos = (List<Cargo>) request.getAttribute("cargos");
        for (Cargo cargo : cargos) {
    %>
    <tr>
        <td><%= cargo.getIdCargo() %></td>
        <td><%= cargo.getCargo() %></td>
        <td><%= cargo.getDescripcionDepartamento() %></td>
        <td><%= cargo.isJefatura() ? "Sí" : "No" %></td>
        <td>
            <a href="cargo?action=edit&id=<%= cargo.getIdCargo() %>">Editar</a> |
            <a href="cargo?action=delete&id=<%= cargo.getIdCargo() %>" onclick="return confirm('¿Eliminar?')">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>

<%@ include file="WEB-INF/footer.jsp" %>
