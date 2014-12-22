package com.busiki.implDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.busiki.model.DniKursu;
import com.busiki.model.Przystanek;
import com.busiki.model.PrzystankiTrasy;
import com.busiki.model.Rozklad;
import com.busiki.model.RozkladInfo;
import com.busiki.model.TrasaInfo;

@Repository
public class RozkladDaoImpl extends AbstractDaoImpl<Rozklad> {

	protected static Logger logger = Logger.getLogger(RozkladDaoImpl.class);

	private String godz[], godz2[];
	private Set<Integer> gSort;
	private List<Integer> gNieSort;
	private int gDoBazy;
	private Integer przed;
	private int result, last;
	private List<Rozklad> r;

	@SuppressWarnings("unchecked")
	public List<Rozklad> getAllByRozkladInfoID(long riid) {
		return getSession()
				.createQuery("from Rozklad where rozkladinfo_id = :riid")
				.setParameter("riid", riid).list();
	}

	@SuppressWarnings("unchecked")
	public List<Rozklad> getRozkladByTrasaId(long id) {
		return getSession()
				.createQuery("from Rozklad where trasa_info_id = :id")
				.setParameter("id", id).list();
	}

	@SuppressWarnings("unchecked")
	public int getRozkladNumerByTrasaIdAndDni(long trasa, DniKursu dniKursu,
			String g, long pId) {
		gSort = new TreeSet<Integer>();
		gNieSort = new ArrayList<Integer>();
		godz = null;
		godz2 = null;
		godz2 = g.split(":");
		gDoBazy = Integer.parseInt(godz2[0] + godz2[1]);
		result = 1;
		przed = null;
		last = 0;
		r = new ArrayList<Rozklad>();
		r.addAll(getSession()
				.createQuery(
						"from Rozklad where trasa_info_id = :id AND dnikursu_id = :idDni AND przystanek_id = :pId")
				.setParameter("id", trasa)
				.setParameter("idDni", dniKursu.getId())
				.setParameter("pId", pId).list());
		for (Rozklad rozklad : r) {
			godz = rozklad.getGodzina().split(":");
			gNieSort.add(Integer.parseInt(godz[0] + godz[1]));
			gSort.add(Integer.parseInt(godz[0] + godz[1]));
			if (last < rozklad.getNumer()) {
				last = rozklad.getNumer();
			}
		}
		for (Integer h : gSort) {
			if (gDoBazy < h && przed == null) {
				przed = h;
			}
		}
		if (przed == null && last == 0) {
			return 1;
		}
		if (przed == null && last != 0) {
			++last;
			return last;
		}
		if (przed != null && last != 0) {

			for (Rozklad rozklad : r) {
				godz = rozklad.getGodzina().split(":");
				if (Integer.parseInt(godz[0] + godz[1]) == przed) {
					result = rozklad.getNumer();
				}
			}
			logger.debug("result: " + result + " last " + last);
			r.clear();
			int temp;
			while (result <= last) {
				temp = last;
				r.addAll(getSession()
						.createQuery(
								"from Rozklad where trasa_info_id = :id AND dnikursu_id = :idDni AND numer = :n")
						.setParameter("id", trasa)
						.setParameter("idDni", dniKursu.getId())
						.setParameter("n", last).list());
				for (Rozklad r : r) {
					r.setNumer(temp + 1);
					update(r);
				}
				r.clear();
				last--;
			}
			logger.debug("OK");
		}
		return result;
	}

	public List<Rozklad> getRozkladByCriteriaSearch(
			String dzien, RozkladInfo rozkladInfo, TrasaInfo t) {
		DateFormat df = DateFormat.getDateInstance();
		int d=0;
		int d2;
		try {
			d2 = df.parse(dzien).getDay();
			if(d2 == 1 || d2 == 2 || d2 == 3 || d2 == 4 || d2 == 5){
				d= 1; 
			}
			if(d2 == 6){
				d= 2; 
			}
			if(d2 == 0){
				d= 3; 
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getSession()
				.createCriteria(Rozklad.class)
				.add(Restrictions
						.sqlRestriction(
								" trasa_info_id = " 
								+ t.getId()
								+ " and rozkladinfo_id = " 
								+ rozkladInfo.getId()
								+ " and dnikursu_id = " 
								+ d )).list();
	}
}
