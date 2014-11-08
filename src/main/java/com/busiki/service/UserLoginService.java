package com.busiki.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.busiki.model.CustomUser;
import com.busiki.model.User;

@Service("userLoginService")
public class UserLoginService implements UserDetailsService {

	protected static Logger logger = Logger.getLogger(UserLoginService.class);

	@Resource(name = "userService")
	private UserService userService;

	public UserDetails loadUserByUsername(String email) {
		if (email != null && !email.equals("")) {
			User user =userService.getAccountByUsername(email);
			if (user == null) {
				logger.debug("null load user by username");
				return null;
			}
			logger.debug("LoadUserByUsername2" + user.getEmail() +
					  user.getPassword()+ " " + user.isEnabled()+ " " );
			return new CustomUser(user);
		} else {
			return null;
		}
	}

}
