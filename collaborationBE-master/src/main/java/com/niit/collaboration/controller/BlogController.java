package com.niit.collaboration.controller;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;

@RestController
public class BlogController
{
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(BlogController.class);
	@Autowired
	Blog blog;

	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	HttpSession session;
	

	@RequestMapping(value="/blogs" , method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getBlogs()
	{
		Logger.debug("Start of method to list the blogs");
		List<Blog> blogs = blogDAO.list();
		if(blogs==null){
			blog=new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("No bolgs are available");
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		else
		{
			Logger.debug("End of method to list the blogs");
			return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
		}
	}

@RequestMapping(value="/blog/{id}" , method=RequestMethod.GET)
public ResponseEntity<Blog> getBlog(@PathVariable("id")int id){
	Logger.debug("Start of method to get a certain blog with id"+id);
	Blog blog = blogDAO.get(id);
	if(blog==null)
	{
		blog = new Blog();
		blog.setErrorCode("404");
		blog.setErrorMessage("blog not found with id:"+id);
	}
	Logger.debug("End of method to get a certain blog with id"+id);
		return new ResponseEntity<Blog> (blog,HttpStatus.OK);
}

@RequestMapping(value="/blog/", method=RequestMethod.POST)
public ResponseEntity<Blog> createBlog(@RequestBody Blog blog)
{
	Logger.debug("Start of method to post a new blog");
	String loggedInuserID = (String) session.getAttribute("loggedInUserID");
	//blog.setUserID(loggedInuserID);
    blog.setUserID(blog.getUserID());
	blog.setStatus("N");
	java.util.Date dateTime =new java.util.Date();
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	String strDate= format.format(dateTime); 
	blog.setDateTime(dateTime);
	System.out.println("*******************"+blog.getTitle());
	blogDAO.save(blog);
	Logger.debug("End of method to post a new blog");
	return new ResponseEntity<Blog> (blog,HttpStatus.OK);
}

@RequestMapping(value="/blog/{id}", method=RequestMethod.DELETE)
public ResponseEntity<Blog> deleteBlog(@PathVariable Long id)
{
	Logger.debug("Start of method to delete a blog with id:"+id);
	
	Integer id1=id.intValue();
	if(blogDAO.get(id1)==null){
		return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
	}
	blogDAO.delete(id1);
	Logger.debug("End of method to delete a blog with id:"+id);
	return new ResponseEntity<Blog>(HttpStatus.OK);
	}
}
