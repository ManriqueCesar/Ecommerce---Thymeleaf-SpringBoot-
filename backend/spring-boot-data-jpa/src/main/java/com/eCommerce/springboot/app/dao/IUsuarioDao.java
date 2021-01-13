package com.eCommerce.springboot.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eCommerce.springboot.app.model.dto.LoginResponseModel;
import com.eCommerce.springboot.app.model.dto.UsuarioClienteModelo;
import com.eCommerce.springboot.app.model.dto.UsuarioModelo;
import com.eCommerce.springboot.app.model.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	@Query("select new com.eCommerce.springboot.app.model.dto.UsuarioModelo(u) from Usuario u")
	public List<UsuarioModelo> findAllUsuarios();
	
	@Query("select new com.eCommerce.springboot.app.model.dto.UsuarioModelo(u) from Usuario u where u.usuario.usuario=:usuario")
	public UsuarioModelo findByUsuario(String usuario);
	
	public Usuario findByUsuario(UsuarioClienteModelo usuarioModelo);
	
	public LoginResponseModel save(UsuarioClienteModelo usuario);
	
	
	
}
