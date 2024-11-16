package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario/all")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAll();
    }

    @GetMapping("/usuario/get/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioService.getById(id);
    }

    @PutMapping("/usuario/update/{id}")
    public String updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.update(usuario, id);
    }

    @DeleteMapping("/usuario/delete/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestBody Usuario usuario) {
        return usuarioService.iniciarSesion(usuario.getUsername(), usuario.getContrasena());
    }

    @PostMapping("/register")
    public String registro(@RequestBody Usuario usuario) {
        return usuarioService.registro(usuario);
    }


}
