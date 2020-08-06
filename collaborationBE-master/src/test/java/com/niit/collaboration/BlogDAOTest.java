//package com.niit.collaboration;
//
//import static org.junit.Assert.*;
//
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import java.util.*;
//
//import com.niit.collaboration.dao.BlogDAO;
//import com.niit.collaboration.model.Blog;
//
//
//
//public class BlogDAOTest 
//{
//	@Autowired
//	 static BlogDAO blogDAO;
//	@Autowired
//	 static Blog blog;	
//	@Autowired
//	static AnnotationConfigApplicationContext context;
//
//	@BeforeClass
//	public static void init()
//	{
//		context=new AnnotationConfigApplicationContext();
//		context.scan("com.niit.collaboration");
//		//context.register(com.niit.collaboration.config.AppConfig.class);
//		context.refresh();
//		
//		blog=(Blog)context.getBean("blog");
//		blogDAO=(BlogDAO)context.getBean("blogDAO");		
//	}
//	@Test
//	public void testSave()
//	{
//		blog.setDateTime(new Date());
//		blog.setDescription("c language is a good language");
//		blog.setTitle("c language is good");
//		blog.setStatus("N");
//		blog.setUserID("8189");
//      	blog.setErrorCode("200");
//        blog.setErrorMessage("this page not");
//		Assert.assertEquals("save Test Case",true,blogDAO.save(blog));
//	}
////	@Test
////	public void testUpdate()
////	{
////		blog.setId(342);
////		blog.setDateTime(new Date());
////		blog.setDescription("java is a good language..@@@....####....");
////		blog.setTitle("java language is very good language");
////		blog.setStatus("N");
////		blog.setUserID("8181");
////      	Assert.assertEquals("save Test Case",true,blogDAO.update(blog));
////	}
////	
////	@Test
////	   public void deleteTestCase()
////	 {
////		   
////		 Assert.assertEquals("delete Test Case",true,blogDAO.delete(308));
////     }
////
////	
////	@Test
////	public void  getUserTestCase1()
////	{
////		blog=blogDAO.get(307);
////		int idNo=blog.getId();
////		Assert.assertEquals("blog test case",307,idNo);
////		
////	}
////	@Test
////	public void listOfBlog()
////	{
////	 Assert.assertEquals("listSupplier" , 4,blogDAO.list().size());
////	}
//
//}
//
