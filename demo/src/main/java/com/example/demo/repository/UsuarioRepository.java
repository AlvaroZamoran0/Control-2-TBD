package com.example.demo.repository;

import com.example.demo.model.Usuario;

import java.util.List;

public interface UsuarioRepository {
    public Usuario crear(Usuario usuario);
    public List<Usuario> getAll();
    public Usuario getById(Integer id);
    public String update(Usuario usuario, Integer id);
    public void delete(Integer id);
    public List<Usuario> searchByCorreo(String correo);
}
