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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;
    @Autowired
    private TareaRepository tareaRepository;


    public void checkTareasVencidas(Integer id_user) {
        Date hoy = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoy);
        calendar.add(Calendar.DATE, 1);
        Date diaSiguiente = calendar.getTime();
        List<Notificacion> notificaciones = notificacionRepository.getAllByUser(id_user);
        List<Tarea> tareas = tareaRepository.getAllUser(id_user);

        for (Tarea tarea : tareas) {
            boolean existe = false;
            for (Notificacion notificacion : notificaciones) {
                if (Objects.equals(notificacion.getIdTarea(), tarea.getId())){
                    existe = true;
                    break;
                }
            }
            Date fecha = tarea.getFechaTermino();
            if (!existe && fecha.before(diaSiguiente)) {
                Notificacion notificacion = new Notificacion(null, id_user, tarea.getId(), "Tarea proxima a vencer", false );
                Notificacion notificacionCreada = crear(notificacion);
            }
        }
    }

    public Notificacion crear(Notificacion notificacion) {
        return notificacionRepository.crear(notificacion);
    }

    public List<Notificacion> getAllByUser(Integer idUser) {
        return notificacionRepository.getAllByUser(idUser);
    }

    public List<Notificacion> getAllByUserAnd(Integer idUser) {
        return notificacionRepository.getAllByUserAnd(idUser);
    }

    public void marcarTodasComoLeidas(Integer idUser) {
        notificacionRepository.marcarTodasComoLeidas(idUser);
    }

    public void marcarLeida(Integer id) {notificacionRepository.marcarLeida(id);}
}
