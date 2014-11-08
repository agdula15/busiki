package com.busiki.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUser implements Serializable, UserDetails {

	
	protected static Logger logger = Logger.getLogger(CustomUser.class);

	private static final long serialVersionUID = 8170125730436803939L;
	@SuppressWarnings("unused")
	private long id;
	private User user;
	private String password;
	private String email;

	public CustomUser(User user) {
		this.user=user;
		this.id = user.getId();
		this.password = user.getPassword();
		this.email = user.getEmail();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			logger.debug("Rola getAuthorities " + role.getName());
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	public String getFullName() {
		return this.getFullName();
	}
}