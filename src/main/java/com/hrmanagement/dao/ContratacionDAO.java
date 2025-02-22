package com.hrmanagement.dao;

import com.hrmanagement.model.Contratacion;
import com.hrmanagement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionDAO {

    // ✅ INSERT a new Contratacion
    public void insertContratacion(Contratacion contratacion) {
        String query = "INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, contratacion.getIdDepartamento());
            stmt.setInt(2, contratacion.getIdEmpleado());
            stmt.setInt(3, contratacion.getIdCargo());
            stmt.setInt(4, contratacion.getIdTipoContratacion());
            stmt.setDate(5, contratacion.getFechaContratacion()); // ✅ Fixed date handling
            stmt.setBigDecimal(6, contratacion.getSalario());
            stmt.setBoolean(7, contratacion.isEstado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ GET a single Contratacion by ID
    public Contratacion getContratacionById(int idContratacion) {
        String query = "SELECT * FROM Contrataciones WHERE idContratacion = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idContratacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Contratacion(
                        rs.getInt("idContratacion"),
                        rs.getInt("idDepartamento"),
                        rs.getInt("idEmpleado"),
                        rs.getInt("idCargo"),
                        rs.getInt("idTipoContratacion"),
                        rs.getDate("fechaContratacion"), // ✅ Fixed date handling
                        rs.getBigDecimal("salario"),
                        rs.getBoolean("estado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ GET ALL Contrataciones
    public List<Contratacion> getAllContrataciones() {
        List<Contratacion> contrataciones = new ArrayList<>();
        String query = "SELECT * FROM Contrataciones";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                contrataciones.add(new Contratacion(
                        rs.getInt("idContratacion"),
                        rs.getInt("idDepartamento"),
                        rs.getInt("idEmpleado"),
                        rs.getInt("idCargo"),
                        rs.getInt("idTipoContratacion"),
                        rs.getDate("fechaContratacion"), // ✅ Fixed date handling
                        rs.getBigDecimal("salario"),
                        rs.getBoolean("estado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contrataciones;
    }

    // ✅ UPDATE Contratacion
    public void updateContratacion(Contratacion contratacion) {
        String query = "UPDATE Contrataciones SET idDepartamento = ?, idEmpleado = ?, idCargo = ?, idTipoContratacion = ?, fechaContratacion = ?, salario = ?, estado = ? WHERE idContratacion = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, contratacion.getIdDepartamento());
            stmt.setInt(2, contratacion.getIdEmpleado());
            stmt.setInt(3, contratacion.getIdCargo());
            stmt.setInt(4, contratacion.getIdTipoContratacion());
            stmt.setDate(5, contratacion.getFechaContratacion()); // ✅ Fixed date handling
            stmt.setBigDecimal(6, contratacion.getSalario());
            stmt.setBoolean(7, contratacion.isEstado());
            stmt.setInt(8, contratacion.getIdContratacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ DELETE Contratacion
    public void deleteContratacion(int idContratacion) {
        String query = "DELETE FROM Contrataciones WHERE idContratacion = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idContratacion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
