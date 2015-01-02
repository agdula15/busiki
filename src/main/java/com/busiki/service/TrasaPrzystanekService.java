package com.busiki.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.PrzystanekDaoImpl;
import com.busiki.implDao.PrzystankiTrasyDaoImpl;
import com.busiki.implDao.TrasaDaoImpl;
import com.busiki.model.Przystanek;
import com.busiki.model.PrzystankiTrasy;
import com.busiki.model.TrasaInfo;

@Service
@Transactional
public class TrasaPrzystanekService {

	protected static Logger logger = Logger
			.getLogger(TrasaPrzystanekService.class);

	@Autowired
	private PrzystanekDaoImpl przystanekDaoImpl;

	@Autowired
	private TrasaDaoImpl trasaDaoImpl;

	@Autowired
	private PrzystankiTrasyDaoImpl przystankiTrasyDaoImpl;

	public List<Przystanek> getAllPrzystanek() {
		return przystanekDaoImpl.getAll();
	}

	public Przystanek getByIdPrzystanek(long id) {
		return przystanekDaoImpl.getById(id);
	}

	public void deletePrzystanek(long id) {
		przystanekDaoImpl.delete(przystanekDaoImpl.getById(id));
	}

	public void updatePrzystanek(Przystanek t) {
		przystanekDaoImpl.update(t);
	}

	public void createPrzystanek(Przystanek t) {
		przystanekDaoImpl.create(t);
	}

	public Przystanek getPrzystanekByName(String nazwa) {
		return przystanekDaoImpl.getPrzystanekByName(nazwa);
	}

	public void dodajTrase(String numer, String string) {
		TrasaInfo newTrasa = new TrasaInfo();
		String[] nazwy = string.split("-");
		newTrasa.setNumer(Integer.parseInt(numer));
		newTrasa.setPoczatek(nazwy[0]);
		newTrasa.setKoniec(nazwy[nazwy.length - 1]);
		trasaDaoImpl.create(newTrasa);
		PrzystankiTrasy pt;
		int i = 1;
		for (String s : nazwy) {
			pt = new PrzystankiTrasy();
			pt.setNumerPrzystanku(i);
			pt.setTrasaInfo(newTrasa);
			pt.setPrzystanek(przystanekDaoImpl.getPrzystanekByName(s));
			logger.debug("Znaleziono i dodano przystanek do trasy"
					+ przystanekDaoImpl.getPrzystanekByName(s).getNazwa());
			przystankiTrasyDaoImpl.create(pt);
			i++;
		}
		newTrasa = new TrasaInfo();
		newTrasa.setNumer(Integer.parseInt(numer)+1);
		newTrasa.setKoniec(nazwy[0]);
		newTrasa.setPoczatek(nazwy[nazwy.length - 1]);
		trasaDaoImpl.create(newTrasa);
		i = 1;
		for (int j = nazwy.length - 1 ; j >= 0 ; j--) {
			pt = new PrzystankiTrasy();
			pt.setNumerPrzystanku(i);
			pt.setTrasaInfo(newTrasa);
			pt.setPrzystanek(przystanekDaoImpl.getPrzystanekByName(nazwy[j]));
			logger.debug("Znaleziono i dodano przystanek do trasy"
					+ przystanekDaoImpl.getPrzystanekByName(nazwy[j]).getNazwa());
			przystankiTrasyDaoImpl.create(pt);
			i++;
		}
	}

	public List<TrasaInfo> getAllTrasy() {
		return trasaDaoImpl.getAll();
	}

	public List<Przystanek> getAllPrzystankiTrasyByTrasaId(long tid) {
		List<Przystanek> p = new ArrayList<Przystanek>();
		for (PrzystankiTrasy pt : przystankiTrasyDaoImpl
				.getAllByTrasaInfoId(tid)) {
			p.add(pt.getPrzystanek());
		}
		return p;
	}

	public Map<Integer, Przystanek> getAllPrzystankiTrasyByTrasaIdMap(long tid) {
		Map<Integer, Przystanek> m = new HashMap<Integer, Przystanek>();
		for (PrzystankiTrasy pt : przystankiTrasyDaoImpl
				.getAllByTrasaInfoId(tid)) {
			m.put(pt.getNumerPrzystanku(), pt.getPrzystanek());
		}
		return m;
	}

	public TrasaInfo getByIdTrasaInfo(long tid) {
		return trasaDaoImpl.getById(tid);
	}

	public TrasaInfo getByNumerTrasaInfo(long tid) {
		return trasaDaoImpl.getByNumer(tid);
	}

	public Set<String> dajPrzystankiDo(String p1, String p2) {
		Set<String> result = new HashSet<String>();
		Integer i = null;
		Set<TrasaInfo> set = new HashSet<TrasaInfo>();
		Map<Integer, Przystanek> m = null;
		// Do optymalizacji:
		for (PrzystankiTrasy pt : getAllPrzystankiTrasy()) {
			if (pt.getPrzystanek().getNazwa().equals(p1)) {
				set.add(pt.getTrasaInfo());
			}
		}
		for (TrasaInfo t : set) {
			if (m == null) {
				m = new HashMap<Integer, Przystanek>();
			}
			m = getAllPrzystankiTrasyByTrasaIdMap(t.getId());
			for (Map.Entry<Integer, Przystanek> entry : m.entrySet()) {
				if (entry.getValue().getNazwa().equals(p1)) {
					i = entry.getKey().intValue();
				}
				if (i != null
						&& entry.getKey() > i
						&& entry.getValue().getNazwa().toLowerCase()
								.contains(p2.toLowerCase())) {
					result.add(entry.getValue().getNazwa());
				}
			}
			m.clear();
		}
		return result;
	}

	public long getIdByNumerTrasy(long tid) {
		return trasaDaoImpl.getByNumer(tid).getId();
	}

	public List<PrzystankiTrasy> getAllPrzystankiTrasy() {
		return przystankiTrasyDaoImpl.getAll();
	}

	public void removeTrasa(long id) {
		for (PrzystankiTrasy pt : przystankiTrasyDaoImpl
				.getAllByTrasaInfoId(id)) {
			przystankiTrasyDaoImpl.delete(pt);
		}
	}

	public List<PrzystankiTrasy> getPrzystankiTrasyByTrasaInfoId(long tid) {
		return przystankiTrasyDaoImpl.getAllByTrasaInfoId(tid);
	}
	
	//sprawdza czy start i end nale¿y do trasy i zwraca ilste tych tras
	public Set<TrasaInfo> getTrasyByNazwyPrzystankow(String start, String end) {
		return przystankiTrasyDaoImpl.getTrasyByNazwyPrzystankow(getPrzystanekByName(start).getId(),getPrzystanekByName(end).getId());
	}

}
