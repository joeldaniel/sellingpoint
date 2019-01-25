package org.mav.sellingpoint.dao.admin.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.mav.sellingpoint.dao.admin.IUserDao;
import org.mav.sellingpoint.dao.impl.MySqlDAOImpl;
import org.mav.sellingpoint.domain.User;
import org.mav.sellingpoint.exception.WorkbenchException;

@Repository(value = "userDao")
public class UserDaoImpl extends MySqlDAOImpl<User> implements IUserDao {
	private final static Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	public User checkDuplicateUser(User user) throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>checkDuplicateUser()");
		}
		try {
			
			return getUserByName(user.getUserName());
			
		} catch (WorkbenchException exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--checkDuplicateUser() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}
	}
	
	public User checkDuplicateEmail(User user) throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>checkDuplicateEmail()");
		}
		List<User> users = null;
		try {
			users = findByProperty(User.class, "email", user.getEmail());
			if (!users.isEmpty())
				return users.get(0);
			else
				return null;
		} catch (WorkbenchException exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--checkDuplicateEmail() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}
	}

	public User editUser(User user) throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>editUser()");
		}
		try {
			saveOrUpdate(user);
		} catch (WorkbenchException exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--editUser() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("<<editUser()");
		}
		return user;
	}

	public User deleteUser(User user) throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>deleteUser()");
		}
		try {
			delete(user);
		} catch (WorkbenchException exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--deleteUser() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("<<deleteUser()");
		}
		return user;
	}

	/*public User getUserById(int id) throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>getUserById()");
		}
		User user = null;
		try {
			findUserById(id);
		} catch (WorkbenchException exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--getUserById() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}
		return user;
	}*/

	public List<User> getAllUser() throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>getAllUser()");
		}
		List<User> users = null;
		try {
			users = findAll(User.class);
			return users;
		} catch (WorkbenchException exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--getAllUser() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}
	}

	@SuppressWarnings("unchecked")
	public User userAuthentication(User user) throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>userAuthentication()");
		}
		List<User> users = null;
		User existing = getUserByName(user.getUserName());
		if(existing != null){
			Transaction transaction = null;
			Session session = null;
		try {
			
			session = getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("from User as model where model.userName=:name and model.password=:password and deleted = false and locked = false").setParameter("name", user.getUserName()).setParameter("password", user.getPassword());
			users =  (List<User>) query.list();
			transaction.commit();
			if (!users.isEmpty()){
				existing.setCount(0);
				editUser(existing);
				return users.get(0);}
			else{
				existing.setCount(existing.getCount()+1);
				editUser(existing);
				if(existing.getCount()>3){
					existing.setLocked(true);
					existing.setCount(0);
					editUser(existing);
				}
				return null;
			}
			
		} catch (Exception exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--userAuthentication() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}
		}
		return  null;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByName(String name) throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>getUserByName()");
		}
		User user = null;
		List<User> list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("from User as model where model.userName=:name and deleted = false").setParameter("name", name);
			list =  (List<User>) query.list();
			transaction.commit();

			if(list != null && list.size() >0){
				user = list.get(0);
			}
		} catch (Exception exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--getUserByName() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}finally{
			closeSession(session);
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>getUserByEmail()");
		}
		User user = null;
		List<User> list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("from User as model where model.email=:email and deleted = false").setParameter("email", email);
			list =  (List<User>) query.list();
			transaction.commit();

			if(list != null && list.size() >0){
				user = list.get(0);
			}
		} catch (Exception exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--getUserByName() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}finally{
			closeSession(session);
		}
		return user;
	}
	@SuppressWarnings("unchecked")
	public User findUserById(Integer id) throws WorkbenchException {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>findUserById()");
		}
		User user = null;
		List<User> list = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = getSession();
			transaction = session.getTransaction();
			transaction.begin();
			Query query = session.createQuery("from User as model where model.id=:id and deleted = false").setParameter("id", id);
			list =  (List<User>) query.list();
			transaction.commit();

			if(list != null && list.size() >0){
				user = list.get(0);
			}
		} catch (Exception exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--findUserById() > " + exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}finally{
			closeSession(session);
		}
		return user;
	}
}
