<%@ page import="java.util.List" %>
<%@ page import="com.hrmanagement.model.TipoContratacion" %>
<%@ include file="WEB-INF/header.jsp" %>

<h2>Tipos de Contratación</h2>
<a href="tipoContratacion?action=new">Agregar Nuevo Tipo de Contratación</a>
<table border="1">
    <tr>
        <th>ID</th><th>Tipo de Contratación</th><th>Acciones</th>
    </tr>
    <%
        List<TipoContratacion> tipos = (List<TipoContratacion>) request.getAttribute("tiposContratacion");
        for (TipoContratacion tipo : tipos) {
    %>
    <tr>
        <td><%= tipo.getIdTipoContratacion() %></td>
        <td><%= tipo.getTipoContratacion() %></td>
        <td>
            <a href="tipoContratacion?action=edit&id=<%= tipo.getIdTipoContratacion() %>">Editar</a> |
            <a href="tipoContratacion?action=delete&id=<%= tipo.getIdTipoContratacion() %>" onclick="return confirm('¿Eliminar?')">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>

<%@ include file="WEB-INF/footer.jsp" %>
