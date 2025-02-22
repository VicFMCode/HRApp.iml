<%@ page import="java.util.List" %>
<%@ page import="com.hrmanagement.model.Contratacion" %>
<%@ page import="com.hrmanagement.model.Empleado" %>
<%@ page import="com.hrmanagement.model.Cargo" %>
<%@ page import="com.hrmanagement.model.TipoContratacion" %>
<%@ include file="WEB-INF/header.jsp" %>

<h2>Lista de Contrataciones</h2>
<a href="contratacion?action=new">Agregar Nueva Contratación</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Empleado</th>
        <th>Cargo</th>
        <th>Tipo de Contratación</th>
        <th>Fecha de Inicio</th>
        <th>Fecha de Fin</th>
        <th>Salario</th>
        <th>Acciones</th>
    </tr>
    <%
        List<Contratacion> contrataciones = (List<Contratacion>) request.getAttribute("contrataciones");
        for (Contratacion contratacion : contrataciones) {
    %>
    <tr>
        <td><%= contratacion.getIdContratacion() %></td>
        <td><%= contratacion.getEmpleado().getNombrePersona() %></td>
        <td><%= contratacion.getCargo().getCargo() %></td>
        <td><%= contratacion.getTipoContratacion().getTipoContratacion() %></td>
        <td><%= contratacion.getFechaInicio() %></td>
        <td><%= contratacion.getFechaFin() %></td>
        <td>$<%= contratacion.getSalario() %></td>
        <td>
            <a href="contratacion?action=edit&id=<%= contratacion.getIdContratacion() %>">Editar</a> |
            <a href="contratacion?action=delete&id=<%= contratacion.getIdContratacion() %>" onclick="return confirm('¿Eliminar esta contratación?')">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>

<%@ include file="WEB-INF/footer.jsp" %>
