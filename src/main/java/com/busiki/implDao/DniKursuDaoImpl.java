package com.busiki.implDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.busiki.model.DniKursu;

@Repository
public class DniKursuDaoImpl extends AbstractDaoImpl<DniKursu> {
	
	protected static Logger logger = Logger.getLogger(DniKursuDaoImpl.class);

	public DniKursu getByName(String string) {

		return (DniKursu) getSession()
				.createQuery("from DniKursu where dzien = :dzien")
				.setParameter("dzien", string).uniqueResult();
	}

}
