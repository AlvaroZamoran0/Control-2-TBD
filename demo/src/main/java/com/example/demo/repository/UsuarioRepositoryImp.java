package com.example.demo.repository;


import com.example.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository{

    @Autowired
    private Sql2o sql2o;

    public Usuario crear(Usuario usuario) {
        try(Connection con = sql2o.open()){
            String sql = "INSERT INTO usuario (nombre,correo,contraseña)" +
                    "VALUES (:nombre,:correo,:contraseña)";
            con.createQuery(sql)
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("correo", usuario.getCorreo())
                    .addParameter("contraseña",usuario.getContraseña())
                    .executeUpdate();
            return usuario;

        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Usuario> getAll() {
        try(Connection con = sql2o.open()){
            String sql = "SELECT * FROM usuario";
            return con.createQuery(sql).executeAndFetch(Usuario.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Usuario getById(Integer id) {
        try(Connection con = sql2o.open()) {
            String sql = "SELECT * FROM usuario WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Usuario.class);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String update(Usuario usuario, Integer id) {
        try (Connection con = sql2o.open()) {
            String sql = "UPDATE usuario SET nombre = :nombre, correo = :correo WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("correo", usuario.getCorreo())
                    .addParameter("id", id)
                    .executeUpdate();
            return "Usuario actualizado con éxito";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void delete(Integer id) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM usuario WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public List<Usuario> searchByCorreo(String correo) {
        try (Connection con = sql2o.open()) {
            String searchCorreo = (correo == null || correo.isEmpty()) ? "%" : "%" + correo + "%";
            String sql = "SELECT * FROM usuario WHERE correo LIKE :correo";
            return con.createQuery(sql)
                    .addParameter("correo", searchCorreo)
                    .executeAndFetch(Usuario.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
