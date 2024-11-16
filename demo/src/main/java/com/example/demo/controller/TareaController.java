package com.example.demo.controller;

import com.example.demo.model.Tarea;
import com.example.demo.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TareaController {
    @Autowired
    private TareaService tareaService;

    @PostMapping("/tarea/crear")
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaService.crear(tarea);
    }

    @GetMapping("/tarea/All")
    public List<Tarea> getAllTareas() {
        return tareaService.getAll();
    }

    @PutMapping("/tarea/update/{id}")
    public String updateTarea(@PathVariable int id, @RequestBody Tarea tarea) {
        return tareaService.update(tarea,id);
    }

    @DeleteMapping("/tarea/delete/{id}")
    public void deleteTarea(@PathVariable int id) {
        tareaService.delete(id);
    }
}
