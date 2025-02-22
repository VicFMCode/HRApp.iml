<%@ include file="header.jsp" %>

<h2>Agregar Nuevo Empleado</h2>
<form action="empleado" method="post">
    <input type="hidden" name="action" value="insert">
    Nombre: <input type="text" name="nombrePersona" required><br>
    Usuario: <input type="text" name="usuario" required><br>
    Teléfono: <input type="text" name="numeroTelefono" required><br>
    Correo: <input type="email" name="correoInstitucional" required><br>
    <input type="submit" value="Guardar">
</form>

<%@ include file="footer.jsp" %>
