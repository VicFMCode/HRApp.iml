package com.hrmanagement.controller;

import com.hrmanagement.dao.ContratacionDAO;
import com.hrmanagement.model.Contratacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebServlet("/contratacion")
public class ContratacionServlet extends HttpServlet {
    private ContratacionDAO contratacionDAO;

    public void init() {
        contratacionDAO = new ContratacionDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                contratacionDAO.deleteContratacion(id);
                response.sendRedirect("contratacion?action=list");
                break;
            default:
                listContrataciones(request, response);
                break;
        }
    }

    private void listContrataciones(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Contratacion> contrataciones = contratacionDAO.getAllContrataciones();
        request.setAttribute("contrataciones", contrataciones);
        request.getRequestDispatcher("contrataciones.jsp").forward(request, response);
    }
}
