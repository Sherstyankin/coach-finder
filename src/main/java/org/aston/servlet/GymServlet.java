package org.aston.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.deserializer.Deserializer;
import org.aston.dto.request.GymRequestDTO;
import org.aston.dto.response.GymResponseDTO;
import org.aston.dto.update.GymUpdateDTO;
import org.aston.serializer.Serializer;
import org.aston.service.GymService;
import org.aston.service.impl.GymServiceImpl;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/gym")
public class GymServlet extends HttpServlet {

    private final GymService gymService = new GymServiceImpl();
    private final Serializer<GymResponseDTO> serializer = new Serializer<>();
    private final Deserializer<GymRequestDTO> deserializer = new Deserializer<>();
    private final Deserializer<GymUpdateDTO> deserializerForUpdateDTO = new Deserializer<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        GymResponseDTO dto = gymService.find(id);
        String json = serializer.toJson(dto);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        GymRequestDTO dto = deserializer.fromJson(json, GymRequestDTO.class);
        gymService.save(dto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        GymUpdateDTO dto = deserializerForUpdateDTO.fromJson(json, GymUpdateDTO.class);
        gymService.update(dto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        gymService.delete(id);
    }
}
