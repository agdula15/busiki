package com.busiki.implDao;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.busiki.dao.UserDetailsDao;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	private static final String FIND_PASSWORD_SQL = "select password from users where email = ?";
	
	@Override
	public String findPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(FIND_PASSWORD_SQL,
				new Object[] { email }, String.class);
	}

	public void changeUserDetails(String name, String surname, String phone, String email, String idnumber, String location) {
		sessionFactory.getCurrentSession().createSQLQuery("UPDATE users SET first_name = '" + name + "', last_name = '" + surname + "', phone_number = '" + phone + "', id_card_number = '" + idnumber + "', address = '" + location + "' WHERE email = '" + email + "'").executeUpdate();
	}
}
