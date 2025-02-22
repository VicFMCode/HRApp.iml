package com.hrmanagement.controller;

import com.hrmanagement.dao.EmpleadoDAO;
import com.hrmanagement.model.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/empleado")
public class EmpleadoServlet extends HttpServlet {
    private EmpleadoDAO empleadoDAO;

    public void init() {
        empleadoDAO = new EmpleadoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                request.getRequestDispatcher("add-empleado.jsp").forward(request, response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Empleado empleado = empleadoDAO.getEmpleadoById(id);
                request.setAttribute("empleado", empleado);
                request.getRequestDispatcher("edit-empleado.jsp").forward(request, response);
                break;
            case "delete":
                deleteEmpleado(request, response);
                break;
            default:
                listEmpleados(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                insertEmpleado(request, response);
                break;
            case "update":
                updateEmpleado(request, response);
                break;
        }
    }

    private void listEmpleados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empleado> empleados = empleadoDAO.getAllEmpleados();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("empleados.jsp").forward(request, response);
    }

    private void insertEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String numeroDui = request.getParameter("dui");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        Empleado nuevoEmpleado = new Empleado(0, numeroDui, nombre, usuario, telefono, correo, fechaNacimiento);
        empleadoDAO.insertEmpleado(nuevoEmpleado);
        response.sendRedirect("empleado?action=list");
    }

    private void updateEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String numeroDui = request.getParameter("dui");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String fechaNacimiento = request.getParameter("fechaNacimiento");

        Empleado empleado = new Empleado(id, numeroDui, nombre, usuario, telefono, correo, fechaNacimiento);
        empleadoDAO.updateEmpleado(empleado);
        response.sendRedirect("empleado?action=list");
    }

    private void deleteEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        empleadoDAO.deleteEmpleado(id);
        response.sendRedirect("empleado?action=list");
    }
}
