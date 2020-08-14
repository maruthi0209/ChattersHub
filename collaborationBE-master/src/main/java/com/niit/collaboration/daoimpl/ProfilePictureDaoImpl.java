package com.niit.collaboration.daoimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.ProfilePictureDao;
import com.niit.collaboration.model.ProfilePicture;

@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao
{
@Autowired
private SessionFactory sessionFactory;
public SessionFactory getSessionFactory() 
{
	return sessionFactory;
}
public void setSessionFactory(SessionFactory sessionFactory) 
{
	this.sessionFactory = sessionFactory;
}
@Transactional
	public void saveOrUpdateProfilePicture(ProfilePicture profilePicture) {
		Session s=sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().saveOrUpdate(profilePicture);
		
	}

	public ProfilePicture getProfilePicture(String name)
	{
		Session session=sessionFactory.openSession();
		ProfilePicture profilePicture=(ProfilePicture)session.load(ProfilePicture.class,name);
		return profilePicture;
	}
	
	@Transactional
	public void updateProfilePicture(ProfilePicture profilePicture) 
	 {
		Session s=sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().update(profilePicture);
	 } 
	    
}