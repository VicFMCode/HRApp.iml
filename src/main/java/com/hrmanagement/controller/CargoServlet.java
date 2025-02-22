package com.hrmanagement.controller;

import com.hrmanagement.dao.CargoDAO;
import com.hrmanagement.model.Cargo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cargo")
public class CargoServlet extends HttpServlet {
    private CargoDAO cargoDAO;

    public void init() {
        cargoDAO = new CargoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                request.getRequestDispatcher("add-cargo.jsp").forward(request, response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Cargo cargo = cargoDAO.getCargoById(id);
                request.setAttribute("cargo", cargo);
                request.getRequestDispatcher("edit-cargo.jsp").forward(request, response);
                break;
            case "delete":
                deleteCargo(request, response);
                break;
            default:
                listCargos(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertCargo(request, response);
        } else if ("update".equals(action)) {
            updateCargo(request, response);
        }
    }

    private void listCargos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cargo> cargos = cargoDAO.getAllCargos();
        request.setAttribute("cargos", cargos);
        request.getRequestDispatcher("cargos.jsp").forward(request, response);
    }

    private void insertCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cargoName = request.getParameter("cargo");
        String desc = request.getParameter("descripcion");
        boolean jefatura = Boolean.parseBoolean(request.getParameter("jefatura"));

        Cargo newCargo = new Cargo(0, cargoName, desc, jefatura);
        cargoDAO.insertCargo(newCargo);
        response.sendRedirect("cargo?action=list");
    }

    private void updateCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String cargoName = request.getParameter("cargo");
        String desc = request.getParameter("descripcion");
        boolean jefatura = Boolean.parseBoolean(request.getParameter("jefatura"));

        Cargo cargo = new Cargo(id, cargoName, desc, jefatura);
        cargoDAO.updateCargo(cargo);
        response.sendRedirect("cargo?action=list");
    }

    private void deleteCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cargoDAO.deleteCargo(id);
        response.sendRedirect("cargo?action=list");
    }
}
