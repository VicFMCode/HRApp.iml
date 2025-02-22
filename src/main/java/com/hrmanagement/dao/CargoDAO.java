package com.hrmanagement.dao;

import com.hrmanagement.model.*;
import com.hrmanagement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {
    // CRUD operations for Cargo
    public void insertCargo(Cargo cargo) {
        String query = "INSERT INTO cargos (cargo, descripcionDepartamento, jefatura) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cargo.getCargo());
            stmt.setString(2, cargo.getDescripcionDepartamento());
            stmt.setBoolean(3, cargo.isJefatura());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cargo> getAllCargos() {
        List<Cargo> cargos = new ArrayList<>();
        String query = "SELECT * FROM cargos";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                cargos.add(new Cargo(rs.getInt("idCargo"), rs.getString("cargo"), rs.getString("descripcionDepartamento"), rs.getBoolean("jefatura")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cargos;
    }

    public void updateCargo(Cargo cargo) {
        String query = "UPDATE cargos SET cargo = ?, descripcionDepartamento = ?, jefatura = ? WHERE idCargo = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cargo.getCargo());
            stmt.setString(2, cargo.getDescripcionDepartamento());
            stmt.setBoolean(3, cargo.isJefatura());
            stmt.setInt(4, cargo.getIdCargo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCargo(int idCargo) {
        String query = "DELETE FROM cargos WHERE idCargo = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idCargo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cargo getCargoById(int idCargo) {
        String query = "SELECT * FROM cargos WHERE idCargo = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idCargo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cargo(
                        rs.getInt("idCargo"),
                        rs.getString("cargo"),
                        rs.getString("descripcionDepartamento"),
                        rs.getBoolean("jefatura")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no record found
    }

}