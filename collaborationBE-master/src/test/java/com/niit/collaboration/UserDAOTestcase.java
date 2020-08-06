package com.niit.collaboration;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.UserDetailsDAO;
import com.niit.collaboration.model.UserDetails;




public class UserDAOTestcase {
	@Autowired
	static UserDetailsDAO userDetailsDAO;
	@Autowired
	static UserDetails userDetails;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init()
	{
		System.out.println("start test method....!");
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration");
		context.refresh();	
		System.out.println("end test method....!");
		userDetails=(UserDetails)context.getBean("userDetails");
		System.out.println("viswam");
		userDetailsDAO=(UserDetailsDAO)context.getBean("userDetailsDAO");
		System.out.println("yogi");
		
		
	}

	
	@Test
	public void saveTestCase()
	{
	   userDetails.setId("9009");
	   userDetails.setName("viswamm");
	   userDetails.setPassword("viswam@123");
	   userDetails.setAddress("hyd");
	   userDetails.setEmail("viswam.b@gmail.com");
	  
	   userDetails.setIsOnline('N');
	   userDetails.setRole("employee");
	   userDetails.setStatus('N');
	   Assert.assertEquals("save Test Case",true,userDetailsDAO.saveUser(userDetails));
	}
}
////	
//////	@Test
//////	public void updateTestCase()
//////	{
//////	   userDetails.setId("9009");
//////	   userDetails.setName("sukesh");
//////	   userDetails.setPassword("sukesh@123");
//////	   userDetails.setAddress("hyd");
//////	   userDetails.setEmail("sukeshk@gmail.com");
//////	   userDetails.setContact(90876);
//////	   userDetails.setIsOnline('N');
//////	   userDetails.setRole("employee");
//////	   userDetails.setStatus('N');
//////	   Assert.assertEquals("save Test Case",true,userDetailsDAO.updateUser(userDetails));
//////		
//////	}
////	
//////	@Test
//////	public void  getUserTest()
//////	{
//////		userDetails=userDetailsDAO.getUser("8888");
//////		String str=userDetails.getId();
//////		Assert.assertEquals("blog test case",str,"8888");
//////		
//////	}
//////	@Test
//////	public void listOfUsers()
//////	{
//////	 Assert.assertEquals("listOfUsres" , 10,userDetailsDAO.list().size());
//////	}
////	
////    //@Test
////    //public void testAuthenticate()
////    //{
////    	// userDetails=userDetailsDAO.isValidUser("venki","venki@123");
////    	 //Assert.assertNotNull(userDetails);
////    //}
//////	@Test
//////	   public void deleteTestCase()
//////	 {
//////		   
//////		userDetails=userDetailsDAO.getUser("8888");
//////		Assert.assertEquals("delete Test Case",true,userDetailsDAO.delete(userDetails));
//////     }
////    
////   // @Test
////    //public void testOffLine()
////    //{
////    	//userDetailsDAO.setOffLine("8181");    	 
////    //}
//////    @Test
//////    public void testOnLine()
//////    {
//////    	userDetailsDAO.setOnline("6363");    	 
//////    }
////   // @Test
////   // public void removeUserTest()
////    //{
////    	//userDetails=userDetailsDAO.getUser("8888");
////    	//Assert.assertEquals("delete Test Case",true,userDetailsDAO.removeUser(9009));
////      //}
////    	
////    }
