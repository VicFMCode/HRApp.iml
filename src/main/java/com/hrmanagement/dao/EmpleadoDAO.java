package com.hrmanagement.dao;

import com.hrmanagement.model.Empleado;
import com.hrmanagement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    // ✅ INSERT Empleado
    public void insertEmpleado(Empleado empleado) {
        String query = "INSERT INTO empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, empleado.getNumeroDui());
            stmt.setString(2, empleado.getNombrePersona());
            stmt.setString(3, empleado.getUsuario());
            stmt.setString(4, empleado.getNumeroTelefono());
            stmt.setString(5, empleado.getCorreoInstitucional());
            stmt.setString(6, empleado.getFechaNacimiento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ GET Empleado BY ID (Fix)
    public Empleado getEmpleadoById(int idEmpleado) {
        String query = "SELECT * FROM empleados WHERE idEmpleado = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idEmpleado);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Empleado(
                        rs.getInt("idEmpleado"),
                        rs.getString("numeroDui"),
                        rs.getString("nombrePersona"),
                        rs.getString("usuario"),
                        rs.getString("numeroTelefono"),
                        rs.getString("correoInstitucional"),
                        rs.getString("fechaNacimiento")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ GET ALL Empleados
    public List<Empleado> getAllEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT * FROM empleados";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                empleados.add(new Empleado(
                        rs.getInt("idEmpleado"),
                        rs.getString("numeroDui"),
                        rs.getString("nombrePersona"),
                        rs.getString("usuario"),
                        rs.getString("numeroTelefono"),
                        rs.getString("correoInstitucional"),
                        rs.getString("fechaNacimiento")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    // ✅ UPDATE Empleado
    public void updateEmpleado(Empleado empleado) {
        String query = "UPDATE empleados SET numeroDui = ?, nombrePersona = ?, usuario = ?, numeroTelefono = ?, correoInstitucional = ?, fechaNacimiento = ? WHERE idEmpleado = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, empleado.getNumeroDui());
            stmt.setString(2, empleado.getNombrePersona());
            stmt.setString(3, empleado.getUsuario());
            stmt.setString(4, empleado.getNumeroTelefono());
            stmt.setString(5, empleado.getCorreoInstitucional());
            stmt.setString(6, empleado.getFechaNacimiento());
            stmt.setInt(7, empleado.getIdEmpleado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ DELETE Empleado
    public void deleteEmpleado(int idEmpleado) {
        String query = "DELETE FROM empleados WHERE idEmpleado = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idEmpleado);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
