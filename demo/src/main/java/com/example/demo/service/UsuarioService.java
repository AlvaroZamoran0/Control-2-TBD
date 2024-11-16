package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<Usuario> getAll() { return usuarioRepository.getAll(); }
    public Usuario getById(Integer id) { return usuarioRepository.getById(id); }
    public String update(Usuario usuario, Integer id) { return usuarioRepository.update(usuario, id); }
    public void delete(Integer id) { usuarioRepository.delete(id); }
    public Usuario iniciarSesion(String username, String contrasena) {
        Usuario usuario = usuarioRepository.searchByNombre(username);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }
    public String registro(Usuario usuario) {
        if (usuarioRepository.searchByCorreo(usuario.getCorreo()) != null) {
            return "El correo ya esta registrado";
        }
        if (usuarioRepository.searchByNombre(usuario.getUsername()) != null) {
            return "El nombre de usuario ya existe";
        }
        usuarioRepository.crear(usuario);
        return "Usuario creado con exito";
    }

}
