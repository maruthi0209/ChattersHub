//package com.niit.collaboration;
//
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.niit.collaboration.dao.FriendDAO;
//import com.niit.collaboration.model.Friend;
//
//public class FriendDAOTestCase 
//{
//	@Autowired
//	 static FriendDAO friendDAO;
//	@Autowired
//	 static Friend friend;	
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
//		friend=(Friend)context.getBean("friend");
//		friendDAO=(FriendDAO)context.getBean("friendDAO");		
//	}
////	@Test
////	public void testSave()
////	{
////		//friend.setId(243);
////		friend.setFriendID("8899");
////		friend.setIsOnline('N');
////		friend.setStatus("A");
////		friend.setUserID("8282");
////		Assert.assertEquals("save Test Case",true,friendDAO.save(friend));
////	}
////	@Test
////   public void testUpdate()
////	{
////		{
////			friend.setId(362);
////			friend.setFriendID("6365");
////			friend.setIsOnline('N');
////			friend.setStatus("A");
////			friend.setUserID("8989");
////			Assert.assertEquals("update Test Case",true,friendDAO.update(friend));
////		}
////		
////	}
//
//	@Test
//   public void testDelete()
// 	{
//		friend.setFriendID("6364");
//		friend.setUserID("6363");
//		Assert.assertEquals("delete Test Case",true,friendDAO.delete("6363","6364"));
//		
// 	}
//	@Test
//	public void getA()
//	{
//		Friend fri=friendDAO.get(362);
//	}
//	@Test
//	public void getB()
//	{
//		Friend fri1=friendDAO.get("8000","6365");
//	}
//	@Test
//	public void getShow()
//	{
//		List list=friendDAO.getMyFriendRequests("362");
//	}
//	@Test
//	public void setOff()
//	{
//		friendDAO.setOffLine("362");
//	}
//	@Test
//	public void setOn()
//	{
//		friendDAO.setOnline("362");
//	}
//	@Test
//	public void getFriend()
//	{
//		List li=friendDAO.getMyFriend("8888");
//	}
//	@Test
//	public void getSend()
//	{
//		List li1=friendDAO.getRequestsSentByMe("8282");
//	}
//	
// 	}
//	
//	
//
//
