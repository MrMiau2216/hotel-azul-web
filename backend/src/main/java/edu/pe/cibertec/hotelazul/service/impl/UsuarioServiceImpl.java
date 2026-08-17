package edu.pe.cibertec.hotelazul.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pe.cibertec.hotelazul.entity.Usuario;
import edu.pe.cibertec.hotelazul.repository.UsuarioRepository;
import edu.pe.cibertec.hotelazul.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario buscarPorUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Usuario autenticar(String username, String password) {
        Usuario usuario = repository.findByUsername(username);
        if (usuario == null)
            return null;
        if (!Boolean.TRUE.equals(usuario.getEstado()))
            return null;
        if (!passwordEncoder.matches(password, usuario.getPassword()))
            return null;
        return usuario;
    }

}
