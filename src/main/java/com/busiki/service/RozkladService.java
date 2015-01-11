package com.busiki.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.RozkladDaoImpl;
import com.busiki.model.DniKursu;
import com.busiki.model.Rozklad;
import com.busiki.model.RozkladInfo;
import com.busiki.model.TrasaInfo;

@Service
@Transactional
public class RozkladService {

	protected static Logger logger = Logger.getLogger(RozkladService.class);

	@Autowired
	private RozkladDaoImpl rozkladDaoImpl;

	@Autowired
	private TrasaPrzystanekService trasaPrzystanekService;

	public void create(Rozklad r) {
		rozkladDaoImpl.create(r);
	}

	public List<Rozklad> getAllByRozkladInfoID(long riid) {
		return rozkladDaoImpl.getAllByRozkladInfoID(riid);
	}

	public List<Rozklad> getRozkladByTrasaNumer(long tid) {
		return rozkladDaoImpl.getRozkladByTrasaId(trasaPrzystanekService
				.getIdByNumerTrasy(tid));
	}

	public List<Rozklad> getRozkladByTrasaId(long tid) {
		return rozkladDaoImpl.getRozkladByTrasaId(tid);
	}

	public int updateNumerKursu(String g, long trasa, DniKursu dniKursu,
			long pId) {
		logger.debug("RozkladService-> updateNumerKursu g:" + g + " dzien "
				+ dniKursu.getDzien() + " trasa " + trasa + " przystanek "
				+ pId);
		return rozkladDaoImpl.getRozkladNumerByTrasaIdAndDni(trasa, dniKursu,
				g, pId);
	}

	public List<Rozklad> getRozkladByCriteriaSearch(String dzien,
			RozkladInfo rozkladInfo, TrasaInfo t) {
		return rozkladDaoImpl.getRozkladByCriteriaSearch(dzien, rozkladInfo, t);
	}

	public List<Rozklad> getAllByRozkladInfoIdAndTrasyId(String data, RozkladInfo r,
			Set<TrasaInfo> trasy) {
		List<Rozklad> result = new ArrayList<Rozklad>();
		for (TrasaInfo t : trasy) {
			result.addAll(getRozkladByCriteriaSearch(data, r, t));
		}
		return result;
	}

	public List<Rozklad> getAllByRozkladInfoIdAndTrasyIdAndNumerKursu(String dzien,
			RozkladInfo rInfo, Set<TrasaInfo> trasy,
			String numerKursu) {
		return rozkladDaoImpl.getAllByRozkladInfoIdAndTrasyIdAndNumerKursu(dzien,
				rInfo,  trasy,
				 numerKursu);
	}
}
