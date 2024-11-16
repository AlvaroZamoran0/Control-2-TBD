package com.example.demo.repository;

import com.example.demo.model.Tarea;

import java.util.List;

public interface TareaRepository {
    public Tarea crear(Tarea tarea);
    public List<Tarea> getAll();
    public String update(Tarea tarea, Integer Id);
    public void delete(Integer Id);
    public List<Tarea> searchByStatus(Boolean status, Integer idUser);
    public List<Tarea> searchByKeywordAndStatus(Boolean status, String keyword, Integer idUser);

}
