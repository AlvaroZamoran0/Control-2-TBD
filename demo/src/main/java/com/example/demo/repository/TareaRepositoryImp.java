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

    public Tarea getById(Integer id){
        try(Connection con = sql2o.open()){
            String sql = "SELECT * FROM tarea WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Tarea.class);
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
            System.out.println("Error fetch tareas: " +e.getMessage());
            return null;
        }
    }

    public String update(Tarea tarea, Integer Id){
        try(Connection con = sql2o.open()){
            String sql = "UPDATE tarea SET nombre = :nombre, descripcion = :descripcion, fechaTermino= :fechaTermino, status= :status WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("fechaTermino", tarea.getFechaTermino())
                    .addParameter("status", tarea.getStatus())
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

    public List<Tarea> searchByStatus(Boolean status,Integer idUser){
        try(Connection con = sql2o.open()){
            String sql = "SELECT * FROM tarea WHERE idUser=:idUser AND status=:status";
            return con.createQuery(sql)
                    .addParameter("idUser", idUser)
                    .addParameter("status", status)
                    .executeAndFetch(Tarea.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Tarea> searchByKeywordAndStatus(Boolean status, String keyword, Integer idUser){
        try(Connection con = sql2o.open()){
            String searchKeyword = (keyword == null || keyword.isEmpty()) ? "%" : "%" + keyword + "%";
            String sql = "SELECT * FROM tarea WHERE idUser=:idUser AND status=:status AND (nombre LIKE :keyword OR descripcion LIKE :keyword)";
            return con.createQuery(sql)
                    .addParameter("idUser",idUser)
                    .addParameter("status",status)
                    .addParameter("keyword",searchKeyword)
                    .executeAndFetch(Tarea.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Tarea> getAllUser(Integer idUser){
        try(Connection con = sql2o.open()){
            String sql = "SELECT * FROM tarea WHERE idUser=:idUser";
            return con.createQuery(sql)
                    .addParameter("idUser", idUser)
                    .executeAndFetch(Tarea.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Tarea> searchByKeyword(String keyword, Integer idUser){
        try(Connection con = sql2o.open()){
            String sql = "SELECT * FROM tarea WHERE idUser=:idUser AND (nombre LIKE :keyword OR descripcion LIKE :keyword)";
            String searchKeyword = (keyword == null || keyword.isEmpty()) ? "%" : "%" + keyword + "%";
            List<Tarea> tareas = con.createQuery(sql)
                    .addParameter("keyword", searchKeyword)
                    .addParameter("idUser", idUser)
                    .executeAndFetch(Tarea.class);
            System.out.println(tareas);
            return tareas;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
