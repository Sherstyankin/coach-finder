package org.aston.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.service.CustomerService;
import org.aston.service.impl.CustomerServiceImpl;

import java.io.IOException;

@WebServlet("/assign_coach")
public class AssignCoachServlet extends HttpServlet {

    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long customer_id = Long.parseLong(req.getParameter("customer_id"));
        long coach_id = Long.parseLong(req.getParameter("coach_id"));
        customerService.assignCoach(customer_id, coach_id);
    }
}
