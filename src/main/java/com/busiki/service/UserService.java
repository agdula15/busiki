package com.busiki.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.dao.RoleDao;
import com.busiki.implDao.UserDaoImpl;
import com.busiki.model.Role;
import com.busiki.model.User;

@Service
@Transactional
public class UserService {

	protected static Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired 
	private RoleDao roleDao;
	 

	public void createNewCustomer(User user, String password) {
		logger.debug("Save user " + user.getFirstName() + " "
				+ user.getLastName());
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleDao.findByName("ROLE_USER"));
		user.setRoles(roles);
		user.setEnabled(true);
		 
		userDaoImpl.create(user, password);
	}

	//getByUsername i podaje siê email?
	public User getAccountByUsername(String email) {
		User user = userDaoImpl.findByEmail(email);
		if (user != null) {
			Hibernate.initialize(user.getRoles());
		}
		return user;
	}
	public boolean findRoleByName(User user, String name ){
		logger.debug("find role userservice: " + roleDao.findByName(name).getName() + user.getRoles().contains(roleDao.findByName(name)));
		for (Role role : user.getRoles()) {
			logger.debug(role.getName() + "!");
			if (role.getName().equals(name))
					return true;
		}
		return false;
	}

	public List<User> getAll(){
		return userDaoImpl.getAll();
	}
	
	public User getById(long id ){
		return userDaoImpl.getById(id);
	}
	
	public void delete(long id){
		userDaoImpl.deleteCustomer(id);
	}

	public void updateUser(User t) {
		userDaoImpl.update(t);
	}
}