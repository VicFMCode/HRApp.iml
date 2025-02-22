package com.hrmanagement.dao;

import com.hrmanagement.model.TipoContratacion;
import com.hrmanagement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoContratacionDAO {

    // ✅ INSERT TipoContratacion
    public void insertTipoContratacion(TipoContratacion tipo) {
        String query = "INSERT INTO TipoContratacion (tipoContratacion) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipo.getTipoContratacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ GET TipoContratacion BY ID (Fix)
    public TipoContratacion getTipoContratacionById(int idTipoContratacion) {
        String query = "SELECT * FROM TipoContratacion WHERE idTipoContratacion = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idTipoContratacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TipoContratacion(
                        rs.getInt("idTipoContratacion"),
                        rs.getString("tipoContratacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ GET ALL TiposContratacion
    public List<TipoContratacion> getAllTiposContratacion() {
        List<TipoContratacion> tipos = new ArrayList<>();
        String query = "SELECT * FROM TipoContratacion";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                tipos.add(new TipoContratacion(rs.getInt("idTipoContratacion"), rs.getString("tipoContratacion")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
    }

    // ✅ UPDATE TipoContratacion
    public void updateTipoContratacion(TipoContratacion tipo) {
        String query = "UPDATE TipoContratacion SET tipoContratacion = ? WHERE idTipoContratacion = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tipo.getTipoContratacion());
            stmt.setInt(2, tipo.getIdTipoContratacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ DELETE TipoContratacion
    public void deleteTipoContratacion(int idTipoContratacion) {
        String query = "DELETE FROM TipoContratacion WHERE idTipoContratacion = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idTipoContratacion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
