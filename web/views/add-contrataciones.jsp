<%@ page import="java.util.List" %>
<%@ page import="com.hrmanagement.model.Empleado" %>
<%@ page import="com.hrmanagement.model.Cargo" %>
<%@ page import="com.hrmanagement.model.TipoContratacion" %>
<%@ page import="com.hrmanagement.model.Contratacion" %>
<%@ include file="WEB-INF/header.jsp" %>

<h2><%= (request.getAttribute("contratacion") == null) ? "Agregar Nueva Contratación" : "Editar Contratación" %></h2>

<%
    Contratacion contratacion = (Contratacion) request.getAttribute("contratacion");
    List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
    List<Cargo> cargos = (List<Cargo>) request.getAttribute("cargos");
    List<TipoContratacion> tiposContratacion = (List<TipoContratacion>) request.getAttribute("tiposContratacion");
%>

<form action="contratacion" method="post">
    <input type="hidden" name="action" value="<%= (contratacion == null) ? "insert" : "update" %>">
    <input type="hidden" name="idContratacion" value="<%= (contratacion != null) ? contratacion.getIdContratacion() : "" %>">

    <label>Empleado:</label>
    <select name="idEmpleado" required>
        <option value="">-- Seleccione un empleado --</option>
        <% for (Empleado emp : empleados) { %>
        <option value="<%= emp.getIdEmpleado() %>" <%= (contratacion != null && contratacion.getEmpleado().getIdEmpleado() == emp.getIdEmpleado()) ? "selected" : "" %>><%= emp.getNombrePersona() %></option>
        <% } %>
    </select><br>

    <label>Cargo:</label>
    <select name="idCargo" required>
        <option value="">-- Seleccione un cargo --</option>
        <% for (Cargo cargo : cargos) { %>
        <option value="<%= cargo.getIdCargo() %>" <%= (contratacion != null && contratacion.getCargo().getIdCargo() == cargo.getIdCargo()) ? "selected" : "" %>><%= cargo.getCargo() %></option>
        <% } %>
    </select><br>

    <label>Tipo de Contratación:</label>
    <select name="idTipoContratacion" required>
        <option value="">-- Seleccione un tipo --</option>
        <% for (TipoContratacion tipo : tiposContratacion) { %>
        <option value="<%= tipo.getIdTipoContratacion() %>" <%= (contratacion != null && contratacion.getTipoContratacion().getIdTipoContratacion() == tipo.getIdTipoContratacion()) ? "selected" : "" %>><%= tipo.getTipoContratacion() %></option>
        <% } %>
    </select><br>

    <label>Fecha de Inicio:</label>
    <input type="date" name="fechaInicio" value="<%= (contratacion != null) ? contratacion.getFechaInicio() : "" %>" required><br>

    <label>Fecha de Fin:</label>
    <input type="date" name="fechaFin" value="<%= (contratacion != null) ? contratacion.getFechaFin() : "" %>"><br>

    <label>Salario:</label>
    <input type="number" name="salario" step="0.01" value="<%= (contratacion != null) ? contratacion.getSalario() : "" %>" required><br>

    <input type="submit" value="Guardar">
</form>

<%@ include file="WEB-INF/footer.jsp" %>
