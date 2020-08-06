package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.UserDetailsDAO;
import com.niit.collaboration.model.UserDetails;
import com.niit.collaboration.util.FileUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class UserController 
{
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserDetails user;

	@Autowired
	UserDetailsDAO userDAO;
	
	@Autowired 
	FriendDAO friendDAO;
	
	private Path path;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> getAllUsers() {
		Logger.debug("Start of method to list of getAllUsers");
		List<UserDetails> users = userDAO.list();
			if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("no users are avaliable");
			users.add(user);
		}
		Logger.debug("Ending of method to list of getAllUsers");
		return new ResponseEntity<List<UserDetails>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> getUserByID(@PathVariable("id") String id)
	{
		Logger.debug("Start of method to list of getUserByID");
		user = userDAO.getUser(id);
		if (user == null) {
			user = new UserDetails();
			user.setErrorCode("404");
			user.setErrorMessage("User not found with that id");
			return new ResponseEntity<UserDetails>(user, HttpStatus.NOT_FOUND);
		}
		Logger.debug("End of method to list of getUserByID");
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/authenticate/", method = RequestMethod.POST)
	public ResponseEntity<UserDetails> login(@RequestBody UserDetails user, HttpSession httpSession) {
		Logger.debug("Start of method to validate user");
		user = userDAO.isValidUser(user.getName(), user.getPassword());
			if (user == null) 
			{
			user = new UserDetails();
			user.setErrorCode("404");
			user.setErrorMessage("User not found with that id");
			return new ResponseEntity<UserDetails>(user, HttpStatus.NOT_FOUND);
		} 
			else 
		{
			if (user.getStatus() == 'R') 
			{
				user.setErrorCode("404");
				user.setErrorMessage("Your registration is not approved. Please contact Admin");
			} else 
			{
				httpSession.setAttribute("loggedInUser", user);
				httpSession.setAttribute("loggedInUserID", user.getId());
				user.setIsOnline('Y');
			}
		}
		Logger.debug("End of method to validate user");
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user/",method = RequestMethod.POST)
	public ResponseEntity<UserDetails> createUser(@RequestBody UserDetails user,HttpServletRequest request,HttpSession session)
	{
		//Logger.debug("->->->->calling method createUser"+file.getOriginalFilename());
		Logger.debug("->->->->calling method createUser"+user.getId());
		Logger.debug("->->->->calling method createUser"+user.getImg());
		String name=user.getName();
		session.setAttribute("name",name);
		
		if (userDAO.getUser(user.getId()) != null) {
			{//User Exist with this id.
				   
				   user.setErrorCode("400"); 
				   user.setErrorMessage("Duplicate User with the id :" + user.getId());
			   }
		}
		 else
		   {
			   
			   user.setStatus('N');
			   user.setIsOnline('N');
			   Logger.debug("Going to save in DB");

		   
		 
		   
			   
			   boolean flag = userDAO.saveUser(user); 
			  
			   
		   }
			   return new ResponseEntity<UserDetails>(user , HttpStatus.OK);  
		
		   }
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> logout(HttpSession session) {
		Logger.debug("->->->->calling method logout");
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		friendDAO.setOffLine(loggedInUserID);
		userDAO.setOffLine(loggedInUserID);

		session.invalidate();

		user.setErrorCode("200");
		user.setErrorMessage("You have successfully logged out");
		Logger.debug("->->->->end of method logout");
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	}

	
	

	
	

	@RequestMapping(value = "/userUpdate/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDetails> updateUser(@PathVariable("id") String id,@RequestBody UserDetails user) {
		Logger.debug("->->->->calling method updateUser");
		if (userDAO.getUser(id) == null) {
			Logger.debug("->->->->User does not exist with id " + user.getId());
			user = new UserDetails(); // ?
			user.setIsOnline('N');
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist with id " + user.getId());
			return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
		}

		userDAO.updateUser(user);
		Logger.debug("->->->->User updated successfully");
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	}

	/*@RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> accept(@PathVariable("id") String id) {
		Logger.debug("Starting of the method accept");

		user = updateStatus(id, 'A', "");
		Logger.debug("Ending of the method accept");
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);

	}*/

	/*@RequestMapping(value = "/reject/{id}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<UserDetails> reject(@PathVariable("id") String id, @PathVariable("reason") String reason) {
		Logger.debug("Starting of the method reject");

		user = updateStatus(id, 'R', reason);
		Logger.debug("Ending of the method reject");
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);

	}*/
	
	@RequestMapping(value="/useraccept/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<UserDetails> useraccept(@PathVariable("id") String id, @RequestBody UserDetails user ) 
	   {
		Logger.debug("Starting of the method accept");
		  user = userDAO.getUser(user.getId());
		   if(user==null)
		  {
			  
			Logger.debug("->->->->User does not exist with id "+ user.getId());
			   user = new UserDetails();
			   user.setErrorMessage("User does not exist with id "+ user.getId());
			   return new ResponseEntity<UserDetails>(user, HttpStatus.NOT_FOUND);
		  }
		  else{
		  Logger.debug("Setting status to :");
		   user.setStatus('A');
		   user.setReason("Approved");
		   user.setErrorCode("200");
		   user.setErrorMessage("Successfully Accepted....");
		   userDAO.updateUser(user);
		  }
		   return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	   }
	  

	   
	   @RequestMapping(value="/userreject/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<UserDetails> userreject(@PathVariable("id") String id, @RequestBody UserDetails user ) 
	   {
		   Logger.debug("Starting of the method reject");
		  user = userDAO.getUser(user.getId());
		  if(user==null)
		  {
			  Logger.debug("->->->->User does not exist with id "+ user.getId());
			   user = new UserDetails();
			   user.setErrorMessage("User does not exist with id "+ user.getId());
			   return new ResponseEntity<UserDetails>(user, HttpStatus.NOT_FOUND);
		  }
		  
		   user.setStatus('R');
		   
		   userDAO.updateUser(user);
		   
		   return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
	   }

	/*private UserDetails updateStatus(String id, char status, String reason) {
		Logger.debug("Starting of the method updateStatus");

		Logger.debug("status: " + status);
		user = userDAO.getUser(id);

		if (user == null) {
			user = new UserDetails();
			user.setErrorCode("404");
			user.setErrorMessage("Could not update the status to " + status);
		} else {

			user.setStatus(status);
			user.setReason(reason);

			userDAO.updateUser(user);

			user.setErrorCode("200");
			user.setErrorMessage("Updated the status successfully");
		}
		Logger.debug("Ending of the method updateStatus");
		return user;

	}*/
	   
}
