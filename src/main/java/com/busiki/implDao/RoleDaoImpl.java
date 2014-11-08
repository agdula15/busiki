package com.busiki.implDao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.busiki.dao.RoleDao;
import com.busiki.model.Role;

@Repository
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {
	
	public Role findByName(String name) {
		Query q = getSession().getNamedQuery("findRoleByName");
		q.setParameter("name", name);
		return (Role) q.uniqueResult();
	}

}