package org.mav.sellingpoint.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.mav.sellingpoint.dao.IGenericDAO;
import org.mav.sellingpoint.exception.WorkbenchException;


public class MySqlDAOImpl<T> implements IGenericDAO<T> {

	private final static Logger LOG = LoggerFactory.getLogger(MySqlDAOImpl.class);

	private SessionFactory sessionFactory;
		
	public T save(T pojo)  throws WorkbenchException{
			//LoggerUtil.debugLogger(logger, "save() Method Starts in MySqlDAOImpl");
			Transaction transaction = null;
			Session session = null;
			try {
				session = getSession();
				transaction = session.beginTransaction();
				session.save(pojo);
				LOG.debug("Save Successful in save() Method in MySqlDAOImpl Class");
				transaction.commit();
				} catch (HibernateException e) {
					LOG.error("save failed", e);
				throw e;
			}catch(Exception e){}
			finally{
				closeSession(session);
			}
			LOG.debug("save() Method Ends in MySqlDAOImpl");
			return pojo;
	}
	
	public void saveOrUpdate(T pojo) throws WorkbenchException{
		LOG.debug( "saveOrUpdate() Method Starts in MySqlDAOImpl");
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(pojo);
			transaction.commit();
			LOG.debug("saveOrUpdate successful in saveOrUpdate() Method in MySqlDAOImpl");
		} catch (HibernateException e) {
			LOG.error("Exception Caught While Updating Persistance Object in MySqlDAOImpl", e);
			throw e;
		}finally{
			closeSession(session);
		}	
		LOG.debug("saveOrUpdate() Method Ends in MySqlDAOImpl");
	}

	public void delete(T pojo) throws WorkbenchException{
		LOG.debug("delete() Method Starts in MySqlDAOImpl");
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			session.delete(pojo);
			transaction.commit();
			LOG.debug("delete successful in delete() Method in MySqlDAOImpl");
		} catch (HibernateException e) {
			LOG.error("Exception Caught While Deleting Persistance Object in MySqlDAOImpl", e);
			throw e;
		}finally{
			closeSession(session);
		}	
		LOG.debug("delete() Method Ends in MySqlDAOImpl");
	}

	@SuppressWarnings("unchecked")
	public T find(Serializable entityId, Class<T> clazz) throws WorkbenchException {
		LOG.debug("findById() Method Starts in MySqlDAOImpl");
		T pojo = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			pojo = (T)session.get(clazz, entityId);
			transaction.commit();
			LOG.debug( "findById() Method successful in MySqlDAOImpl");
		} catch (HibernateException e) {
			LOG.debug("Exception Caught While Processing findById() in MySqlDAOImpl", e);
			throw e;
		}finally{
			closeSession(session);
		}	
		LOG.debug("findById() Method Ends in MySqlDAOImpl");
		return pojo;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> classObject) throws WorkbenchException{
		LOG.debug("findAll() Method Starts in MySqlDAOImpl");
		List<T> list;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.beginTransaction();
			
			list = (List<T>)session.createCriteria(classObject).list();
			transaction.commit();
			LOG.debug("findAll() Method Successful in MySqlDAOImpl");
		} catch (HibernateException e) {
			LOG.error("Exception Caught While Processing findAll() Method in MySqlDAOImpl", e);
			throw e;
		}finally{
			closeSession(session);
		}	
		LOG.debug("findAll() Method Ends in MySqlDAOImpl");
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(Class<T> classObject, String propertyName,
			final Object value)throws WorkbenchException {
		LOG.debug("findByProperty() Method Starts in MySqlDAOImpl");
		List<T> list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("from " + classObject.getName()+ " as model where model." + propertyName + "= :value").setParameter("value", value);
			list =  (List<T>) query.list();
			transaction.commit();
		} catch (RuntimeException re) {
			transaction.rollback();
			LOG.error("Exception Caught while processing findByproperty() method in MySqlDAOImpl",re);
			throw re;
		}finally{
			closeSession(session);
		}
		
		LOG.debug("findByProperty() Method Ends in MySqlDAOImpl");
		return list;
	}
	
	public Session getSession() {
		LOG.debug("getSession() Method Starts in MySqlDAOImpl");
		Session session = null;
		if(null != sessionFactory){
			session = sessionFactory.openSession();
		}
		LOG.debug( "getSession() Method Ends in MySqlDAOImpl");
		return session;
	}
	public void closeSession(Session session) {
		LOG.debug( "getSession() Method Starts in MySqlDAOImpl");
		
		if(null != session){
			session.close();
		}
		LOG.debug("getSession() Method Ends in MySqlDAOImpl");
		
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		LOG.debug( "setSessionFactory() Method Starts in MySqlDAOImpl");
		this.sessionFactory = sessionFactory;
//		if (sessionFactory != this.session.getSessionFactory()) {
//			//this.hibernateTemplate = new HibernateTemplate(sessionFactory);
//			this.session = sessionFactory.openSession();
//		}
		LOG.debug( "setSessionFactory() Method Ends in MySqlDAOImpl");
	}
	
}
