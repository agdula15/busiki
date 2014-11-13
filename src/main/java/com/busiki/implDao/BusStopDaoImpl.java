package com.busiki.implDao;

import org.springframework.stereotype.Repository;

import com.busiki.dao.BusStopDao;
import com.busiki.model.Przystanek;

@Repository(value = "BusStopDao")
public class BusStopDaoImpl extends AbstractDaoImpl<Przystanek> implements BusStopDao{

}
