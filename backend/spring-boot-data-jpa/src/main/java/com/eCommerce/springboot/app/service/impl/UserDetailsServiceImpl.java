package com.eCommerce.springboot.app.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eCommerce.springboot.app.dao.IUsuarioDao;
import com.eCommerce.springboot.app.model.dto.UsuarioModelo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        UsuarioModelo user = usuarioDao.findByUsuario(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with username: " + username);
	        }
	        return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getPassword(),
	                new ArrayList<GrantedAuthority>());
	    }

}
