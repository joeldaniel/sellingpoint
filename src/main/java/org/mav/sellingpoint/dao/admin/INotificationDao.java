package org.mav.sellingpoint.dao.admin;

import org.mav.sellingpoint.dao.IGenericDAO;
import org.mav.sellingpoint.domain.Notification;
import org.mav.sellingpoint.exception.WorkbenchException;

public interface INotificationDao extends IGenericDAO<Notification> {
	
	public Notification findByUserName(String userName) throws WorkbenchException;

}
