package org.mav.sellingpoint.dao.admin.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.mav.sellingpoint.dao.admin.INotificationDao;
import org.mav.sellingpoint.dao.impl.MySqlDAOImpl;
import org.mav.sellingpoint.domain.Notification;
import org.mav.sellingpoint.exception.WorkbenchException;

@Repository(value = "notificationDao")
public class NotificationDaoImpl extends MySqlDAOImpl<Notification> implements INotificationDao {

	private final static Logger LOG = LoggerFactory.getLogger(NotificationDaoImpl.class);
	
	public Notification findByUserName(String userName) throws WorkbenchException {
		if(LOG.isDebugEnabled()){
			LOG.debug(">>findByUserName()");
		}
		try{
			List<Notification> notifications = findByProperty(Notification.class, "userName", userName);
			if(notifications != null && notifications.size() >0)
				return notifications.get(0);
			else
				return null;
		}catch (WorkbenchException exception) {
			if(LOG.isErrorEnabled()){
				LOG.error("--findByUserName() > "+exception.getMessage(), exception);
			}
			throw new WorkbenchException(exception.getMessage(), exception);
		}
	}

}
