package com.busiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.RoleDaoImpl;
import com.busiki.model.Role;

@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleDaoImpl roleDaoImpl;

	public Role getRoleByName(String name) {
		return  roleDaoImpl.findByName(name);
	}
}
