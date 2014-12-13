package com.busiki.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.RozkladDaoImpl;
import com.busiki.model.DniKursu;
import com.busiki.model.Rozklad;

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
		return rozkladDaoImpl.getRozkladByTrasaId(trasaPrzystanekService.getIdByNumerTrasy(tid));
	}
	
	public List<Rozklad> getRozkladByTrasaId(long tid) {
		return rozkladDaoImpl.getRozkladByTrasaId(tid);
	}

	public int updateNumerKursu(String g, long trasa, DniKursu dniKursu, long pId) {
		logger.debug("RozkladService-> updateNumerKursu g:" +  g + " dzien " +  dniKursu.getDzien() +" trasa " +  trasa + " przystanek " + pId);
		return rozkladDaoImpl.getRozkladNumerByTrasaIdAndDni(trasa, dniKursu, g, pId);
	}


}
