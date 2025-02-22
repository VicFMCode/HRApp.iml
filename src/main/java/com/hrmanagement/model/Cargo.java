package com.hrmanagement.model;

public class Cargo {
    private int idCargo;
    private String cargo;
    private String descripcionDepartamento;
    private boolean jefatura;

    // Constructors
    public Cargo() {
    }

    public Cargo(int idCargo, String cargo, String descripcionDepartamento, boolean jefatura) {
        this.idCargo = idCargo;
        this.cargo = cargo;
        this.descripcionDepartamento = descripcionDepartamento;
        this.jefatura = jefatura;
    }

    // Getters and Setters
    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }

    public boolean isJefatura() {
        return jefatura;
    }

    public void setJefatura(boolean jefatura) {
        this.jefatura = jefatura;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "idCargo=" + idCargo +
                ", cargo='" + cargo + '\'' +
                ", descripcionDepartamento='" + descripcionDepartamento + '\'' +
                ", jefatura=" + jefatura +
                '}';
    }
}
