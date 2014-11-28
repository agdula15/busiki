package com.busiki.implDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.busiki.model.TrasaInfo;

@Repository
public class TrasaDaoImpl extends AbstractDaoImpl<TrasaInfo> {

	protected static Logger logger = Logger.getLogger(TrasaDaoImpl.class);

	public TrasaInfo getByNumer(long tid) {
		TrasaInfo t = (TrasaInfo) getSession()
				.createQuery("from TrasaInfo where numer = :numer")
				.setParameter("numer",Long.toString(tid) ).uniqueResult();
		return t;
	}
}
