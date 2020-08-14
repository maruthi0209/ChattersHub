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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;

@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(BlogDAOImpl.class);
	private static final Blog Blog = null;
	@Autowired
	private SessionFactory sessionFactory;
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean save(Blog blog)
	{
		Logger.debug("Calling method of save");
		try{
			  sessionFactory.getCurrentSession().save(blog);
			  Logger.debug("ending method of save");
		return true;
			}catch (Exception e ){
				
				e.printStackTrace();
				return false;
			}
	}

	
	@Transactional
	public boolean delete(int id) 
	{
		Logger.debug("Method to delete blog");
		try
		{
		Blog BlogToDelete = new Blog();
		BlogToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(BlogToDelete);
		Logger.debug("blog deleted successfully");
		return true;
	    }
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean update(Blog blog) {
		Logger.debug("Calling method of update");
		try{
			sessionFactory.getCurrentSession().update(blog);
			Logger.debug("ending method of update");
	return true;
		} catch (Exception e){
			
	       e.printStackTrace();
	       return false;
		}
	}

	@Transactional
	public List<Blog> list() {
		Logger.debug("To list all the blogs present");
		String hql = "from Blog";
		@SuppressWarnings("rawtypes")
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog> listBlog = query.list();
		Logger.debug("end of method to list the blogs");
		return listBlog;
	}

	@Transactional
	public Blog get(int id) 
	{
		Logger.debug("Calling method of get");

		Session session=sessionFactory.getCurrentSession();
		
		Criteria ct=session.createCriteria(Blog.class);
		ct.add(Restrictions.eq("id",id));
		Blog u=(Blog)ct.uniqueResult();
		Logger.debug("Ending method of get");
		return u;
	}

}
