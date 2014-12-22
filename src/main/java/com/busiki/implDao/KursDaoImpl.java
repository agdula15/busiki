package com.busiki.implDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.busiki.model.Kurs;
import com.busiki.model.Rozklad;

@Repository
public class KursDaoImpl extends AbstractDaoImpl<Kurs> {

	protected static Logger logger = Logger.getLogger(KursDaoImpl.class);

	public List<Kurs> getKursByCriteriaSearch(String dzien, long r_id) {
		String d[] = dzien.split("-");
		logger.debug("d split : " + d[1] + d[2] + d[0]);
		return getSession()
				.createCriteria(Kurs.class)
				.add(Restrictions.sqlRestriction(" rozklad_id = " + r_id
						+ " and DATE_PART('year', datakursu) = " + d[0]
						+ " and DATE_PART('month', datakursu) = " + d[1]
						+ " and DATE_PART('day', datakursu) = " + d[2]))
				.addOrder(Order.asc("rozklad.id")).list();
	}

	public Set<Integer> getZajeteMiejsca(Kurs k, Kurs k2) {
		int n = k.getRozklad().getNumer();
		long dk = k.getRozklad().getDniKursu().getId();
		Set<Integer> miejscaZajete = new TreeSet<Integer>();
		List<Kurs> kursy = new ArrayList<Kurs>();
		List<Rozklad> r = getSession()
				.createCriteria(Rozklad.class)
				.add(Restrictions.sqlRestriction("trasa_info_id = "
						+ k.getRozklad().getTrasaInfo().getId()
						+ " and dnikursu_id = " + dk))
				.add(Restrictions.eq("numer", n)).list();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (Rozklad rozklad : r) {
			kursy.addAll(getKursByCriteriaSearch(df.format(k.getDataKursu()),
					rozklad.getId()));
		}
		logger.debug(" size : " + kursy.size());
		for (int i = 0; i < kursy.size(); i++) {
			if (k.getId() < kursy.get(i).getId()
					&& k2.getId() > kursy.get(i).getId()) {
				miejscaZajete.addAll(kursy.get(i).getMiejscaZajete());
				logger.debug("if 2 ");
			}
			if (i < (kursy.size() - 1)) {
				if (kursy.get(i).getId() == k.getId()
						&& kursy.get(i + 1).getId() == k2.getId()) {
					for (Integer m1 : kursy.get(i).getMiejscaZajete()) {
						for (Integer m2 : kursy.get(i + 1).getMiejscaZajete()) {
							if (m1 == m2) {
								miejscaZajete.add(m2);
							}
						}
					}
				}
			}
		}
		return miejscaZajete;
	}
}