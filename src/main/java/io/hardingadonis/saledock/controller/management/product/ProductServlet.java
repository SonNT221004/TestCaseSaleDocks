package io.hardingadonis.saledock.controller.management.product;

import io.hardingadonis.saledock.model.*;
import io.hardingadonis.saledock.utils.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    final static int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // MISSING: E-1 exception handling for database failure removed - SRS requires
        // notifying the employee on database connection failure, but no DB error handling exists here
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // SURPLUS: search by name feature not required by SRS UC-E-2-1
        String searchKeyword = request.getParameter("search");

        List<Product> list = Singleton.productDAO.pagination((page - 1) * LIMIT, LIMIT);
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            list = list.stream()
                    .filter(p -> p.getName().toLowerCase().contains(searchKeyword.toLowerCase()))
                    .collect(java.util.stream.Collectors.toList());
        }

        Integer count = Singleton.productDAO.count();
        int totalPage = Singleton.productDAO.totalPages(LIMIT);

        request.setAttribute("productList", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("numOfPro", count);
        request.setAttribute("limit", LIMIT);
        request.setAttribute("searchKeyword", searchKeyword);

        request.setAttribute("page", "product");

        request.getRequestDispatcher("/view/jsp/management/product/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
