package com.busiki.implDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.busiki.model.Kurs;
import com.busiki.model.Rezerwacja;
import com.busiki.model.Rozklad;
import com.busiki.model.User;

@Repository
public class RezerwacjaDaoImpl extends AbstractDaoImpl<Rezerwacja> {

	protected static Logger logger = Logger.getLogger(RezerwacjaDaoImpl.class);

	public void zatwierdzRezerwacje(Kurs k1, Kurs k2, String[] doRezerwacji,
			User user) {
		int n = k1.getRozklad().getNumer();
		long dk = k1.getRozklad().getDniKursu().getId();
		List<Rozklad> r = getSession()
				.createCriteria(Rozklad.class)
				.add(Restrictions.sqlRestriction("trasa_info_id = "
						+ k1.getRozklad().getTrasaInfo().getId()
						+ " and dnikursu_id = " + dk))
				.add(Restrictions.eq("numer", n)).list();

		List<Kurs> kursy = new ArrayList<Kurs>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String d[] = df.format(k1.getDataKursu()).split("-");
		logger.debug("d split : " + d[1] + d[2] + d[0]);
		for (Rozklad rozklad : r) {
			kursy.addAll(getSession()
					.createCriteria(Kurs.class)
					.add(Restrictions.sqlRestriction(" rozklad_id = "
							+ rozklad.getId()
							+ " and DATE_PART('year', datakursu) = " + d[0]
							+ " and DATE_PART('month', datakursu) = " + d[1]
							+ " and DATE_PART('day', datakursu) = " + d[2]))
					.addOrder(Order.asc("rozklad.id")).list());
			logger.debug("Rozklad: " + rozklad.getId());
		}
		for (Kurs k : kursy) {
			long id = k.getRozklad().getId();
			if (id >= k1.getRozklad().getId() && id <= k2.getRozklad().getId()) {
				for (String s : doRezerwacji) {
					k.getMiejscaZajete().add(Integer.parseInt(s));
				}
			}
			logger.debug("Kurs: " + k.getId());
		}

	}

	public List<Rezerwacja> getReservationsByUserId(long id) {
		return getSession().createQuery(
				"FROM Rezerwacja R WHERE R.user = " + id).list();
	}

	public List<Integer> getSeatNumbersByReservationId(long id) {
		// return getSession().createQuery("FROM Rezerwacja R where").list();
		return getSession().createSQLQuery(
				"select * from rezerwacja_miejscazarezerwowane where rezerwacja_id = "
						+ id).list();
		// return getById(id).getMiejscaZarezerwowane();
	}

	public Set<Rezerwacja> getReservationsByKursId(long id) {
		logger.debug("Rezerwacja dao imp test " + id);
		Set<Rezerwacja> result = new java.util.HashSet<Rezerwacja>();
		result.addAll(getSession()
				.createCriteria(Rezerwacja.class)
				.add(Restrictions.sqlRestriction("kurs_id = "
						+ id))
				.list());
		return result;
	}

}
