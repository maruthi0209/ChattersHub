package com.niit.collaboration.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.collaboration.dao.ProfilePictureDao;
import com.niit.collaboration.model.ProfilePicture;
import com.niit.collaboration.util.FileUtil;



@RestController
public class ProfilePictureController
{
	@Autowired
private ProfilePictureDao profilePictureDao;
	private Path path;
	@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ModelAndView insert(@RequestParam MultipartFile image, HttpSession session,HttpServletRequest request)
	{
		String name = (String)session.getAttribute("name");
		if(name!=null)
		{
		}
		ProfilePicture profilePicture=new ProfilePicture();
		profilePicture.setName(name);
		try
		{
			byte[] bytes=new byte[image.getInputStream().available()];
			image.getInputStream().read(bytes);
			BufferedInputStream buffer=new BufferedInputStream(image.getInputStream());
			String filename=image.getOriginalFilename();
			System.out.println("==>"+System.getProperty("user.dir"));
			System.out.println("==>"+request.getServletContext().getContextPath());
			String path=request.getServletContext().getContextPath()+"/src/main/webapp/resources/c";
			
			String path2 = this.getClass().getClassLoader().getResource("").getPath();
			String fullPath = URLDecoder.decode(path2, "UTF-8");
			
			fullPath=fullPath+"../../images";
			System.out.println("fullpath==>"+fullPath);
			path = fullPath;
			
			System.out.println(path  +"##############################################################"+filename);
			File rootPath=new File(path);
			if(!rootPath.exists())
    			rootPath.mkdirs();
    		File store=new File(rootPath.getAbsolutePath()+"/"+filename);
    		System.out.println("Image path :"+path);
    		OutputStream os=new FileOutputStream(store);
    		os.write(bytes);
			profilePicture.setImage(image.getBytes());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		profilePictureDao.saveOrUpdateProfilePicture(profilePicture);
		ModelAndView m1=new ModelAndView("hello");
	    return m1; 
		
	}
	
	
	
	
	@RequestMapping(value="/updateprofile",method=RequestMethod.POST)
	public ModelAndView updatePropile(@RequestParam MultipartFile image,HttpSession session,HttpServletRequest request)
	{
		String name = (String)session.getAttribute("name");
		if(name!=null)
		{
		}
		ProfilePicture profilePicture=new ProfilePicture();
		System.out.println("*************************************************"+name);
		profilePicture.setName(name);
		try
		{
			byte[] bytes=new byte[image.getInputStream().available()];
			image.getInputStream().read(bytes);
			BufferedInputStream buffer=new BufferedInputStream(image.getInputStream());
			String filename=image.getOriginalFilename();
			System.out.println("==>"+System.getProperty("user.dir"));
			System.out.println("==>"+request.getServletContext().getContextPath());
			String path=request.getServletContext().getContextPath()+"/src/main/webapp/resources/c";
			
			String path2 = this.getClass().getClassLoader().getResource("").getPath();
			String fullPath = URLDecoder.decode(path2, "UTF-8");
			
			fullPath=fullPath+"../../images";
			System.out.println("fullpath==>"+fullPath);
			path = fullPath;
			
			System.out.println(path  +"##############################################################"+filename);
			File rootPath=new File(path);
			if(!rootPath.exists())
    			rootPath.mkdirs();
    		File store=new File(rootPath.getAbsolutePath()+"/"+filename);
    		System.out.println("Image path :"+path);
    		OutputStream os=new FileOutputStream(store);
    		os.write(bytes);
			profilePicture.setImage(image.getBytes());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		profilePictureDao.updateProfilePicture(profilePicture);
		ModelAndView m1=new ModelAndView("hello");
	    return m1; 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping(value="/getimage", method=RequestMethod.GET)
	public byte[] getProfilePicture(@RequestParam("name") String name,HttpSession session,HttpServletResponse response)
	{
		session.setAttribute("name", name);
		System.out.println(name+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
		ProfilePicture profilePicture=profilePictureDao.getProfilePicture(name);
		if(profilePicture == null)
			return null;
		else
			return profilePicture.getImage();
	}
	
	@RequestMapping("/hello")
	public ModelAndView helloWorld()
	{  
		       ModelAndView m1=new ModelAndView("hello");
		       return m1; 
	}
	
}