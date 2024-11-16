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
    public Usuario crear(Usuario usuario) { return usuarioRepository.crear(usuario); }
    public List<Usuario> getAll() { return usuarioRepository.getAll(); }
    public Usuario getById(Integer id) { return usuarioRepository.getById(id); }
    public String update(Usuario usuario, Integer id) { return usuarioRepository.update(usuario, id); }
    public void delete(Integer id) { usuarioRepository.delete(id); }
    public List<Usuario> searchByCorreo(String correo) { return usuarioRepository.searchByCorreo(correo); }
        //if (correo == null || correo.isEmpty()) {
        //    return usuarioRepository.getAll();
        //} else {
        //    return usuarioRepository.searchByCorreo(correo);
        //}
    //}

}
