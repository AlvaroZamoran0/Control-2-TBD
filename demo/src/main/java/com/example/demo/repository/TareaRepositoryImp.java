package com.example.demo.repository;

import com.example.demo.model.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository{

    @Autowired
    private Sql2o sql2o;

    public Tarea crear(Tarea tarea){
       try(Connection con = sql2o.open()){
           String sql = "INSERT INTO tarea (nombre,descripcion,fechaTermino,idUser,status)" +
                   "VALUES (:nombre,:descripcion,:fechaTermino,:idUser,:status)";
           con.createQuery(sql)
                   .addParameter("nombre", tarea.getNombre())
                   .addParameter("descripcion", tarea.getDescripcion())
                   .addParameter("fechaTermino",tarea.getFechaTermino())
                   .addParameter("idUser",tarea.getIdUser())
                   .addParameter("status",tarea.getStatus())
                   .executeUpdate();
           return tarea;

       } catch(Exception e){
           System.out.println(e.getMessage());
           return null;
       }
    }

    public List<Tarea> getAll(){
        try(Connection con = sql2o.open()){
            String sql = "SELECT * FROM tarea";
            return con.createQuery(sql).executeAndFetch(Tarea.class);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Error fetch tareas: " +e.getMessage());
            return null;
        }
    }

    public String update(Tarea tarea, Integer Id){
        try(Connection con = sql2o.open()){
            String sql = "UPDATE tarea SET nombre = :nombre, descripcion = :descripcion, fecha=:fecha WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("fecha", tarea.getFechaTermino())
                    .addParameter("id", Id)
                    .executeUpdate();
            return "Se actualizo la tarea con exito";
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void delete(Integer Id){
        try(Connection con = sql2o.open()){
            String sql = "DELETE FROM tarea WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id", Id)
                    .executeUpdate();
        }
    }
}
