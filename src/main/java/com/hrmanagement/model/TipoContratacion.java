package com.hrmanagement.model;

public class TipoContratacion {
    private int idTipoContratacion;
    private String tipoContratacion;

    // Constructors
    public TipoContratacion() {
    }

    public TipoContratacion(int idTipoContratacion, String tipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
        this.tipoContratacion = tipoContratacion;
    }

    // Getters and Setters
    public int getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(int idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }

    @Override
    public String toString() {
        return "TipoContratacion{" +
                "idTipoContratacion=" + idTipoContratacion +
                ", tipoContratacion='" + tipoContratacion + '\'' +
                '}';
    }
}
