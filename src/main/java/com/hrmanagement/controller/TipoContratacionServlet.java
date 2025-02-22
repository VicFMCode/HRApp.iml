package com.hrmanagement.controller;

import com.hrmanagement.dao.TipoContratacionDAO;
import com.hrmanagement.model.TipoContratacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tipoContratacion")
public class TipoContratacionServlet extends HttpServlet {
    private TipoContratacionDAO tipoContratacionDAO;

    public void init() {
        tipoContratacionDAO = new TipoContratacionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                request.getRequestDispatcher("add-tipoContratacion.jsp").forward(request, response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                TipoContratacion tipo = tipoContratacionDAO.getTipoContratacionById(id);
                request.setAttribute("tipoContratacion", tipo);
                request.getRequestDispatcher("edit-tipoContratacion.jsp").forward(request, response);
                break;
            case "delete":
                deleteTipoContratacion(request, response);
                break;
            default:
                listTiposContratacion(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertTipoContratacion(request, response);
        } else if ("update".equals(action)) {
            updateTipoContratacion(request, response);
        }
    }

    private void listTiposContratacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TipoContratacion> tipos = tipoContratacionDAO.getAllTiposContratacion();
        request.setAttribute("tiposContratacion", tipos);
        request.getRequestDispatcher("tipoContratacion.jsp").forward(request, response);
    }

    private void insertTipoContratacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipoContratacion");
        TipoContratacion newTipo = new TipoContratacion(0, tipo);
        tipoContratacionDAO.insertTipoContratacion(newTipo);
        response.sendRedirect("tipoContratacion?action=list");
    }

    private void updateTipoContratacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("tipoContratacion");

        TipoContratacion updatedTipo = new TipoContratacion(id, tipo);
        tipoContratacionDAO.updateTipoContratacion(updatedTipo);
        response.sendRedirect("tipoContratacion?action=list");
    }

    private void deleteTipoContratacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tipoContratacionDAO.deleteTipoContratacion(id);
        response.sendRedirect("tipoContratacion?action=list");
    }
}
