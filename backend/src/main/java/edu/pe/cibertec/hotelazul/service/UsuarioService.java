package edu.pe.cibertec.hotelazul.service;

import edu.pe.cibertec.hotelazul.entity.Usuario;

public interface UsuarioService {

    Usuario buscarPorUsername(String username);

    Usuario autenticar(String username, String password);

}
