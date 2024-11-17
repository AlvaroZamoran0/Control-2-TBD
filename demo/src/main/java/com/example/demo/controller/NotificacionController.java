package com.example.demo.controller;

import com.example.demo.model.Notificacion;
import com.example.demo.model.Tarea;
import com.example.demo.repository.TareaRepository;
import com.example.demo.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping("/crear")
    public Notificacion crearNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.crear(notificacion);
    }

    @GetMapping("/user/{idUser}")
    public List<Notificacion> getNotificacionesByUser(@PathVariable Integer idUser) {
        return notificacionService.getAllByUser(idUser);
    }

    @PutMapping("/marcarTodasLeidas/{idUser}")
    public void marcarTodasLasNotificacionesComoLeidas(@PathVariable Integer idUser) {
        notificacionService.marcarTodasComoLeidas(idUser);
    }
}
