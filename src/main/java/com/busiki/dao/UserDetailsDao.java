package com.busiki.dao;

public interface UserDetailsDao {
	String findPasswordByEmail(String email);
}
