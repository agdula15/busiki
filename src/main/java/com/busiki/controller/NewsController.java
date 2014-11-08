package com.busiki.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.busiki.implDao.UserDaoImpl;
import com.busiki.model.News;
import com.busiki.service.NewsService;

@Controller
public class NewsController {
	
	protected static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	@Autowired
	private NewsService newsService;

	@RequestMapping(value="/news", method=RequestMethod.POST)
	public String newsCreation(@RequestParam("tytul") String tytul, @RequestParam("tresc") String tresc){ 
		logger.debug(tytul + " " + tresc); 
		News news = new News();
		news.setTresc(tresc);
		news.setTytul(tytul);
		newsService.create(news); 
		
		return "redirect:/news"; 
	}
	
	@RequestMapping("news/delete/{id}")
	public String deleteNews(@PathVariable int id){
		newsService.delete(id);
		
		return "redirect:/news";
	}
}
