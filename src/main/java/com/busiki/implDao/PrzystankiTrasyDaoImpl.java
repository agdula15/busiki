package com.busiki.implDao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.busiki.model.PrzystankiTrasy;

@Repository
public class PrzystankiTrasyDaoImpl extends AbstractDaoImpl<PrzystankiTrasy> {

	protected static Logger logger = Logger
			.getLogger(PrzystankiTrasyDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<PrzystankiTrasy> getAllByTrasaInfoId(long id) {
		return getSession()
				.createQuery("from PrzystankiTrasy where trasa_info_id = :id")
				.setParameter("id", id).list();
	}

}
