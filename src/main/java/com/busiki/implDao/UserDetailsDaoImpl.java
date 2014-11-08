package com.busiki.implDao;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.busiki.dao.UserDetailsDao;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	private static final String FIND_PASSWORD_SQL = "select password from users where email = ?";
	@Override
	public String findPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(FIND_PASSWORD_SQL,
				new Object[] { email }, String.class);
	}


}
