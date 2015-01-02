package com.busiki.implDao;

import java.text.DateFormat;
import java.text.ParseException;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.busiki.model.RozkladInfo;

@Repository
public class RozkladInfoDaoImpl extends AbstractDaoImpl<RozkladInfo> {
	
	//Daje rozklad dla którego 'pasuje' dana data
	public RozkladInfo getByDate(String dzien) {
		DateFormat df = DateFormat.getDateInstance();
		RozkladInfo r = null;
		try {
			r = (RozkladInfo)getSession()
			.createCriteria(RozkladInfo.class)
			.add(Restrictions.ge("dataDo", df.parse(dzien))).add(Restrictions.le("dataOd",  df.parse(dzien))).uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
}