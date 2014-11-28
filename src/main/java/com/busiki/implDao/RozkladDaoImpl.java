package com.busiki.implDao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.busiki.model.Rozklad;

@Repository
public class RozkladDaoImpl extends AbstractDaoImpl<Rozklad> {
	public List<Rozklad> getAllByRozkladInfoID(long riid) {
		return getSession().createQuery("from Rozklad where rozkladinfo_id = :riid").setParameter("riid", riid).list();
	}
}
