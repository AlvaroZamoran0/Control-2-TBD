package com.example.demo.service;

import com.example.demo.model.Tarea;
import com.example.demo.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public Tarea getById(Integer id){
        return tareaRepository.getById(id);
    }

    public String update(Tarea tarea, Integer id){
        return tareaRepository.update(tarea, id);
    }

    public void delete(Integer id){
        tareaRepository.delete(id);
    }

    public List<Tarea> searchFilter(Boolean status, String keyword, Integer idUser){
        if(!Objects.equals(keyword, "empty")){
            if(status == null){
                return tareaRepository.searchByKeyword(keyword,idUser);
            }
            return tareaRepository.searchByKeywordAndStatus(status, keyword, idUser);
        }else{
            return tareaRepository.searchByStatus(status,idUser);
        }
    }

    public List<Tarea> getAllUser(Integer idUser){
        return tareaRepository.getAllUser(idUser);
    }
}
