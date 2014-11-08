package com.busiki.implDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.busiki.dao.NewsDao;
import com.busiki.model.News;

@Repository
public class NewsDaoImpl extends AbstractDaoImpl<News> implements NewsDao {

	protected static Logger logger = Logger.getLogger(UserDaoImpl.class);

}
