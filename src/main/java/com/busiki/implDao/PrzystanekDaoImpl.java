package com.busiki.implDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.busiki.model.Przystanek;

@Repository
public class PrzystanekDaoImpl extends AbstractDaoImpl<Przystanek> {

	protected static Logger logger = Logger.getLogger(PrzystanekDaoImpl.class);

	public Przystanek getPrzystanekByName(String n) {
		Przystanek p = (Przystanek) getSession()
				.createQuery("from Przystanek where nazwa = :nazwa")
				.setParameter("nazwa", n).uniqueResult();
		logger.debug("Wyszukiwanie przystanku po nazwie: " + p.getNazwa()
				+ ", id:" + p.getId());
		return p;
	}

	/*public boolean isPrzystanekUsed(long id) {
		if (getSession().createQuery("from przystanki_trasy where przystanek_id = :przystanek_id").setParameter("przystanek_id", id).uniqueResult() != null) 
			return true;
		else
			return false;
	}*/
}
