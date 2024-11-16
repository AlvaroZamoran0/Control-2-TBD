package com.example.demo.service;

import com.example.demo.model.Tarea;
import com.example.demo.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    public Tarea crear(Tarea tarea){
        return tareaRepository.crear(tarea);
    }

    public List<Tarea> getAll(){
        return tareaRepository.getAll();
    }

    public String update(Tarea tarea, Integer id){
        return tareaRepository.update(tarea, id);
    }

    public void delete(Integer id){
        tareaRepository.delete(id);
    }
}
