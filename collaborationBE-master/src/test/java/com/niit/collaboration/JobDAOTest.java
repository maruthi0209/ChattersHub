//package com.niit.collaboration;
//
//import java.util.Date;
//
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.niit.collaboration.dao.BlogDAO;
//import com.niit.collaboration.dao.JobDAO;
//import com.niit.collaboration.dao.UserDetailsDAO;
//import com.niit.collaboration.model.Blog;
//import com.niit.collaboration.model.Job;
//import com.niit.collaboration.model.JobApplication;
//import com.niit.collaboration.model.UserDetails;
//
//public class JobDAOTest 
//{
//	@Autowired
//	 static JobDAO jobDAO;
//	@Autowired
//	 static Job job;	
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
//		job=(Job)context.getBean("job");
//		jobDAO=(JobDAO)context.getBean("jobDAO");		
//	}
//	@Test
//	public void testSave()
//	{
//		Date d1=new Date();
//		job.setDateTime(d1);
//		job.setQualification("MCA");
//		job.setDescription("good");
//		job.setStatus('V');
//		job.setTitle("teacher");
//		Assert.assertEquals("save Test Case",true,jobDAO.save(job));
//	}
//	@Test
//	public void testUpdate()
//	{
//		job.setId(241);
//		Date d1=new Date();
//		job.setDateTime(d1);
//		job.setQualification("M.Tech");
//		job.setDescription("good");
//		job.setStatus('V');
//		job.setTitle("faculty");
//		Assert.assertEquals("update Test Case",true,jobDAO.updateJob(job));
//	}
//	@Test
//	public void listOfJobs()
//	{
//	 Assert.assertEquals("listJobs" ,3,jobDAO.getAllOpenedJobs().size());
//	}
//	@Test
//	public void getJob()
//	{
//	 job=jobDAO.getJobDetails(241);
//	}
//	@Test
//	public void listsOfJob()
//	{		
//		Assert.assertEquals("listJobs" ,2,jobDAO.listJobApplication().size());		
//	}
//	@Test
//	public void getApp()
//	{
//		
//		Assert.assertEquals("listJob" ,1,jobDAO.getMyAppliedJobs("8989").size());	
//	}
//	@Test 
//	public void getJobApplication()
//	{
//		Assert.assertEquals("listJob",null,jobDAO.getJobApplication("8888",241));
//	}
//	@Test
//	public void jobApplication()
//	{
//		JobApplication jobapp=jobDAO.getJobApplication(243);
//		Assert.assertEquals("listJob" ,243,jobapp.getId());		
//	}
//	
//	}
//
