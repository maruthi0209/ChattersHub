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

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;

@Repository("eventDAO")
public class EventDAOImpl implements EventDAO {

	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(EventDAOImpl.class);

	private static final Event Event = null;

	@Autowired
	private SessionFactory sessionFactory;

	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean save(Event event) 
	{
		Logger.debug("Calling method of save");
		try {
			sessionFactory.getCurrentSession().save(event);
			Logger.debug("Ending method of save");
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(int id)
	{
		Logger.debug("Method to delete event");
		try
		{
		Event EventToDelete = new Event();
		EventToDelete.setEvent_id(id);
		sessionFactory.getCurrentSession().delete(EventToDelete);
		Logger.debug("event deleted successfully");
		return true;
	    }
		catch (HibernateException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
		@Transactional
	public boolean update(Event event) 
	{
		Logger.debug("Calling method of update");
		try {
			sessionFactory.getCurrentSession().update(event);
			Logger.debug("ending method of update");
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public List<Event> list() {
		Logger.debug("To list all the events present");
		String hql = "from Event";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Event> listEvent = query.list();
		Logger.debug("end of method to list the events");
		return listEvent;
	}

	@Transactional
	public Event get(int id) 
	{
		Logger.debug("Calling method of get");
		Session session = sessionFactory.getCurrentSession();

		Criteria ct = session.createCriteria(Event.class);
		ct.add(Restrictions.eq("event_id", id));
		Event u = (Event) ct.uniqueResult();
		Logger.debug("Ending method of get");
		return u;
	}

}
