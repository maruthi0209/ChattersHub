package com.niit.collaboration.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.UserDetailsDAO;
import com.niit.collaboration.model.UserDetails;

@EnableTransactionManagement
@Repository("userDetailsDAO")
public class UserDAOImpl implements UserDetailsDAO {
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(UserDAOImpl.class);
	private static final UserDetails User = null;
	@Autowired
	private SessionFactory sessionFactory;

	private UserDetails user;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	@Transactional
	public boolean saveUser(UserDetails user) 
	{
		Logger.debug("Start of method of saveUser");
		try
		{
			/*String hql = "select max(id) from UserDetails";
			Query query =sessionFactory.getCurrentSession().createQuery(hql);
			 String maxID=(String)query.uniqueResult();
			 
			 user.setId(maxID+1);*/
			sessionFactory.getCurrentSession().save(user);
			Logger.debug("end of method of saveUser");
			return true;
		}
		catch(HibernateException e)
		{
			//String maxID="1";
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean updateUser(UserDetails user) 
	{
		Logger.debug("Start of method of updateUser");
		try
		{
			sessionFactory.getCurrentSession().update(user);
			Logger.debug("end of method of updateUser");
			return true;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean removeUser(int id) 
	{

		Logger.debug("Start of method of removeUser");
		try
		{
			UserDetails user=new UserDetails();
			user.getId();
			sessionFactory.getCurrentSession().delete(user);
			Logger.debug("end of method of removeUser");
			return true;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public UserDetails getUser(String id) 
	{
		Logger.debug("Start of method of getUser");
		Session session=sessionFactory.getCurrentSession();
		
		Criteria ct=session.createCriteria(UserDetails.class);
		ct.add(Restrictions.eq("id",id));
		UserDetails u=(UserDetails)ct.uniqueResult();

		Logger.debug("end of method of getUser");
		return u;
	}

	
    @Transactional
	public List<UserDetails> list() {
    	
		Logger.debug("To list all the users present");
		String hql = "from UserDetails where role<>'admin'";
		@SuppressWarnings("rawtypes")
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		List<UserDetails> listUser = query.list();
		Logger.debug("end of method to list the users");
		return query.list();
		
	}

	
	@Transactional
	public UserDetails isValidUser(String name, String password) 
	{
		Logger.debug("Start of method of isValidUser");	
		String hql="from UserDetails where name = " + "'" + name + "'and " + " password='" + password+"'";
		@SuppressWarnings({ "rawtypes" })
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<UserDetails> list = query.list();
		if(list!=null  && !list.isEmpty())
		{
			Logger.debug("end of method of isValidUser");
			return list.get(0);
		}
		Logger.debug("end of method of isValidUser");
		return null;
	}

	@Transactional
	public boolean delete(UserDetails user) 
	{
		Logger.debug("Start of method to delete");	
		try
		{
			sessionFactory.getCurrentSession().delete(user);
			Logger.debug("end of method to delete");	
			return true;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public void setOffLine(String loggedInUserID) 
	{
		Logger.debug("Start of method to setOffLine");
		String hql = "UPDATE UserDetails SET isOnline = 'N' where id ='" + loggedInUserID + "'";
		Logger.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}

	@Transactional
	public void setOnline(String loggedInUserID) {
		Logger.debug("Starting of the method setOffline");
		String hql = "UPDATE User SET isOnline = 'Y' where id = '" + loggedInUserID + "'";
		Logger.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		Logger.debug("Ending of the method setOffline");
		
	}

	@Transactional
	public boolean saveOrupdate(UserDetails user) 
	{
		Logger.debug("Start of method to saveOrupdate");
		try
		{
		sessionFactory.openSession().saveOrUpdate(user);
		Logger.debug("End of method to saveOrupdate");
		return true;
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	
}
