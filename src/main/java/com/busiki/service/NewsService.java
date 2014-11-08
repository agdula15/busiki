package com.busiki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.NewsDaoImpl;
import com.busiki.model.News;

@Service
@Transactional
public class NewsService {
	
	@Autowired
	private NewsDaoImpl newsDaoImpl;

	public void create(News n){
		newsDaoImpl.create(n);
	}
	
	public void delete(int id){
		newsDaoImpl.delete(newsDaoImpl.getById(id));
	}
	
	public List<News> getAll(){
		return newsDaoImpl.getAll();
	}
	
}
