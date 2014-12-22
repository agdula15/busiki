package com.busiki.implDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.busiki.model.PrzystankiTrasy;
import com.busiki.model.TrasaInfo;

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

	public Set<TrasaInfo> getTrasyByNazwyPrzystankow(long s, long e) {
		logger.debug("test7 " + s + " " + e);
		Set<TrasaInfo> result = new HashSet<TrasaInfo>();
		@SuppressWarnings("unchecked")
		List<TrasaInfo> trasyId = getSession()
				.createCriteria(PrzystankiTrasy.class)
				.add(Restrictions
						.sqlRestriction(" przystanek_id = "
								+ s
								+ " or"
								+ " przystanek_id="
								+ e
								+ " group by trasa_info_id having count(trasa_info_id)=2"))
				.setProjection(Projections.property("trasaInfo")).list();
		int numerStart;
		int numerEnd;
		for (TrasaInfo t : trasyId) {
			numerStart = (Integer) getSession()
					.createCriteria(PrzystankiTrasy.class)
					.add(Restrictions.sqlRestriction(" przystanek_id = " + s
							+ "and trasa_info_id=" + t.getId()))
					.setProjection(Projections.property("numerPrzystanku"))
					.uniqueResult();
			numerEnd = (Integer) getSession()
					.createCriteria(PrzystankiTrasy.class)
					.add(Restrictions.sqlRestriction(" przystanek_id = " + e
							+ "and trasa_info_id=" + t.getId()))
					.setProjection(Projections.property("numerPrzystanku"))
					.uniqueResult();
			if (numerEnd > numerStart) {
				result.add(t);
			}

		}
		for (TrasaInfo trasaInfo : result) {
			logger.debug("Trasa result id" + trasaInfo.getId());
		}
		return result;
	}

}
