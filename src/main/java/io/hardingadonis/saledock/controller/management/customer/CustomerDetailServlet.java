package io.hardingadonis.saledock.controller.management.customer;

import io.hardingadonis.saledock.model.*;
import io.hardingadonis.saledock.utils.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.util.*;

@WebServlet(name = "CustomerDetailServlet", urlPatterns = {"/customer-detail"})
public class CustomerDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        request.setAttribute("page", "customer");

        String id = request.getParameter("id");
        if (id == null) {
            response.sendError(404);
            return;
        }

        Integer id_customer = Integer.valueOf(id);
        Optional<Customer> customer = Singleton.customerDAO.getByID(id_customer);

        if (customer.isPresent()) {
            var cus = customer.get();

            request.setAttribute("cus", cus);
            request.getRequestDispatcher("/view/jsp/management/customer/customer-detail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        if (id == null || name == null || email == null || address == null) {
            response.sendError(400);
            return;
        }

        Integer id_customer = Integer.valueOf(id);
        Optional<Customer> customer = Singleton.customerDAO.getByID(id_customer);

        if (customer.isPresent()) {
            var cus = customer.get();
            cus.setName(name);
            cus.setEmail(email);
            cus.setAddress(address);
            Singleton.customerDAO.save(cus);
            response.sendRedirect(request.getContextPath() + "/customer-detail?id=" + id);
        } else {
            response.sendError(404);
        }
    }
}
