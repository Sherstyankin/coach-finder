package org.aston.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.deserializer.Deserializer;
import org.aston.dto.request.CustomerRequestDTO;
import org.aston.dto.response.CustomerResponseDTO;
import org.aston.dto.update.CustomerUpdateDTO;
import org.aston.serializer.Serializer;
import org.aston.service.CustomerService;
import org.aston.service.impl.CustomerServiceImpl;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();
    private final Serializer<CustomerResponseDTO> serializer = new Serializer<>();
    private final Deserializer<CustomerRequestDTO> deserializer = new Deserializer<>();
    private final Deserializer<CustomerUpdateDTO> deserializerForUpdateDTO = new Deserializer<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        CustomerResponseDTO dto = customerService.find(id);
        String json = serializer.toJson(dto);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        CustomerRequestDTO dto = deserializer.fromJson(json, CustomerRequestDTO.class);
        customerService.save(dto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        CustomerUpdateDTO dto = deserializerForUpdateDTO.fromJson(json, CustomerUpdateDTO.class);
        customerService.update(dto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        customerService.delete(id);
    }
}
