package org.mav.sellingpoint.dao.admin;

import java.util.List;

import org.mav.sellingpoint.dao.IGenericDAO;
import org.mav.sellingpoint.domain.UserLog;
import org.mav.sellingpoint.exception.WorkbenchException;

public interface IUserLogDao extends IGenericDAO<UserLog> {
	public List<UserLog> findUserLogByUserName(String userName) throws WorkbenchException;
 
}
