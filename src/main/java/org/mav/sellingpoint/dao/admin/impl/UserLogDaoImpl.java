package org.mav.sellingpoint.dao.admin.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.mav.sellingpoint.dao.admin.IUserLogDao;
import org.mav.sellingpoint.dao.impl.MySqlDAOImpl;
import org.mav.sellingpoint.domain.UserLog;
import org.mav.sellingpoint.exception.WorkbenchException;

@Repository(value = "userlogDao")
public class UserLogDaoImpl extends MySqlDAOImpl<UserLog> implements IUserLogDao {
	private final static Logger LOG = LoggerFactory.getLogger(UserLogDaoImpl.class);	
	
	public List<UserLog> findUserLogByUserName(String userName) throws WorkbenchException {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>findUserLogByUserName()");
		}
		try{
			return findByProperty(UserLog.class, "userName", userName);
		}catch (Exception exception) {
			if(LOG.isErrorEnabled()){
				LOG.error("--findUserLogByUserName() > "+exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}
	}
}
