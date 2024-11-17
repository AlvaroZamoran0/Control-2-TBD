package com.example.demo.service;

import com.example.demo.model.Notificacion;
import com.example.demo.model.Tarea;
import com.example.demo.repository.NotificacionRepository;
import com.example.demo.repository.TareaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;
    private TareaRepository tareaRepository;

    @Scheduled(cron = "0 * * * * ?") // Ejecuta cada hora
    public void checkTareasVencidas() {
        LocalDate hoy = LocalDate.now();
        LocalDate proximoDia = hoy.plusDays(1);
        
        List<Tarea> tareas = tareaRepository.getAll();
        for (Tarea tarea : tareas) {
            if (tarea.getFechaTermino().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(proximoDia)) {
                // Aquí puedes agregar la lógica para enviar la notificación
                System.out.println("La tarea '" + tarea.getNombre() + "' está próxima a vencer.");
            }
        }
    }

    public Notificacion crear(Notificacion notificacion) {
        return notificacionRepository.crear(notificacion);
    }

    public List<Notificacion> getAllByUser(Integer idUser) {
        return notificacionRepository.getAllByUser(idUser);
    }

    public void marcarTodasComoLeidas(Integer idUser) {
        notificacionRepository.marcarTodasComoLeidas(idUser);
    }
}
