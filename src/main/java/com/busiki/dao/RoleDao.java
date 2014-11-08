package com.busiki.dao;

import com.busiki.model.Role;

public interface RoleDao {
	Role findByName(String name);
}
