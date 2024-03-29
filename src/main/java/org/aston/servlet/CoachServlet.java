package org.aston.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.deserializer.Deserializer;
import org.aston.dto.request.CoachRequestDTO;
import org.aston.dto.response.CoachResponseDTO;
import org.aston.dto.update.CoachUpdateDTO;
import org.aston.serializer.Serializer;
import org.aston.service.CoachService;
import org.aston.service.impl.CoachServiceImpl;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/coach")
public class CoachServlet extends HttpServlet {

    private final CoachService coachService = new CoachServiceImpl();
    private final Serializer<CoachResponseDTO> serializer = new Serializer<>();
    private final Deserializer<CoachRequestDTO> deserializer = new Deserializer<>();
    private final Deserializer<CoachUpdateDTO> deserializerForUpdateDTO = new Deserializer<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        CoachResponseDTO dto = coachService.find(id);
        String json = serializer.toJson(dto);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        CoachRequestDTO dto = deserializer.fromJson(json, CoachRequestDTO.class);
        coachService.save(dto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        CoachUpdateDTO dto = deserializerForUpdateDTO.fromJson(json, CoachUpdateDTO.class);
        coachService.update(dto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        coachService.delete(id);
    }
}
