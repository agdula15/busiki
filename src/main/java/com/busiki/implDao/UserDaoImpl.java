package com.busiki.implDao;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.busiki.dao.RoleDao;
import com.busiki.dao.UserDao;
import com.busiki.model.Role;
import com.busiki.model.User;

@Repository(value = "UserDao")
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	protected static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	private static final String UPDATE_PASSWORD_SQL = "update users set password = ? where email = ?"; 
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Inject
	private RoleDao roleDao;

	public void create(User user, String password) {

		Set<Role> roles = new HashSet<Role>();
		roles.add(roleDao.findByName("ROLE_USER"));
		user.setRoles(roles);
		create(user);
		jdbcTemplate.update(UPDATE_PASSWORD_SQL, password, user.getEmail());
	}
	
	public void deleteCustomer(long id) {
		  getSession().createQuery("delete User where id = " + id).executeUpdate();
	}
	
	@Override
	public User findByEmail(String email) {
		return (User) getSession().getNamedQuery("findUserByEmail")
				.setParameter("email", email).uniqueResult();
	}

	public void myUserDelete(User byId) {
		// TODO Auto-generated method stub
		
	}



}
