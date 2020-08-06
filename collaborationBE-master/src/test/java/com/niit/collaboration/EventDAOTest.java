//package com.niit.collaboration;
//
//import static org.junit.Assert.*;
//
//import java.util.Date;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.Assert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.niit.collaboration.dao.BlogDAO;
//import com.niit.collaboration.dao.EventDAO;
//import com.niit.collaboration.model.Blog;
//import com.niit.collaboration.model.Event;
//
//
//public class EventDAOTest
//{
//	@Autowired
//	 static EventDAO eventDAO;
//	@Autowired
//	 static Event event;	
//	@Autowired
//	static	AnnotationConfigApplicationContext context;
//
//	@BeforeClass
//	public static void init()
//	{
//		context=new AnnotationConfigApplicationContext();
//		context.scan("com.niit.collaboration");
//		context.refresh();
//		
//		event=(Event)context.getBean("event");
//		eventDAO=(EventDAO)context.getBean("eventDAO");		
//	}
//
//	@Test
//	public void testSave() 
//	{
//		event.setDateTime(new Date());
//		event.setDescription("good");
//		event.setName("action");
//		event.setVenue("good");
//		Assert.assertEquals("save Test Case",true,eventDAO.save(event));
//	}
//	
//	@Test
//	public void testUpdate() 
//	{
//		event.setEvent_id(344);
//		event.setDateTime(new Date());
//		event.setDescription("very good");
//		event.setName("sports.");
//		event.setVenue("good");
//		Assert.assertEquals("update Test Case",true,eventDAO.update(event));
//	}
//	@Test
//	public void  getEvent()
//	{
//		event=eventDAO.get(348);
//		int eNo=event.getEvent_id();
//		Assert.assertEquals("blog test case",348,eNo);
//		
//	}
//	@Test
//	public void listOfEvents()
//	{
//	 Assert.assertEquals("listOfEvents" , 3,eventDAO.list().size());
//	}
////	 @Test
////	   public void deleteTestCase()
////	 {
////		  Assert.assertEquals("delete Test Case",true,eventDAO.delete(347));	  
////	 }
//
//}
