package com.busiki.implDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.postgresql.core.Query;
import org.springframework.stereotype.Repository;

import com.busiki.model.DniKursu;
import com.busiki.model.Rozklad;

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


	public List<Rozklad> getAllByRozkladInfoID(long riid) {
		return getSession()
				.createQuery("from Rozklad where rozkladinfo_id = :riid")
				.setParameter("riid", riid).list();
	}

	public List<Rozklad> getRozkladByTrasaId(long id) {
		return getSession()
				.createQuery("from Rozklad where trasa_info_id = :id")
				.setParameter("id", id).list();
	}

	@SuppressWarnings("unchecked")
	public int getRozkladNumerByTrasaIdAndDni(long trasa, DniKursu dniKursu,
			String g, long pId, int k) {
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
		if (przed !=null && last != 0){
			
			for (Rozklad rozklad : r) {
				godz = rozklad.getGodzina().split(":");
				if (Integer.parseInt(godz[0] + godz[1]) == przed) {
					result = rozklad.getNumer();
				}
			}
			logger.debug("result: "  + result + " last " + last);
			r.clear();
			int temp;
			while(result<=last){
				temp = last;
				/*int cos = getSession()
				.createQuery(
						"update Rozklad set numer = :n where trasa_info_id = :id AND dnikursu_id = :id2 AND numer := n2 ")
				.setParameter("n", temp + 1)
				.setParameter("id", trasa)
				.setParameter("id2", dniKursu.getId())
				.setParameter("n2", last)
				.executeUpdate();
				getSession().getTransaction().commit();*/
				
				r.addAll(getSession()
						.createQuery(
								"from Rozklad where trasa_info_id = :id AND dnikursu_id = :idDni AND numer = :n")
						.setParameter("id", trasa)
						.setParameter("idDni", dniKursu.getId())
						.setParameter("n", last).list());
				for (Rozklad r : r) {
					r.setNumer(temp+1);
					update(r);
				}
				r.clear();
				last--;
			}
			logger.debug("OK");
			
			
			
			/*temp = "";
			for (Integer h : gNieSort) {
				temp2 = "";
				if (przed <= h) {
					temp2 = "" + h;
					if (temp2.length() == 3) {
						temp = Character.toString(temp2.charAt(0)) + ":" + ""
								+ Character.toString(temp2.charAt(1)) + ""
								+ Character.toString(temp2.charAt(2));
					} else {
						temp = Character.toString(temp2.charAt(0))
								+ Character.toString(temp2.charAt(1)) + ":"
								+ Character.toString(temp2.charAt(2))
								+ Character.toString(temp2.charAt(3));
					}
					r2 = (Rozklad) getSession()
							.createQuery(
									"from Rozklad where trasa_info_id = :id AND dnikursu_id = :idDni AND przystanek_id = :pId AND godzina = :temp")
							.setParameter("id", trasa)
							.setParameter("idDni", dniKursu.getId())
							.setParameter("pId", pId)
							.setParameter("temp", temp).uniqueResult();
					id2 = r2.getId();
					for (int i = 0; i < k; i++) {
						getSession()
								.createQuery(
										"update Rozklad set numer = :numer where id = :id ")
								.setParameter("id", id2 + i)
								.setParameter("numer", numer).executeUpdate();

					}
					numer++;
				}
			}*/
		}
		return result;
	}
}
