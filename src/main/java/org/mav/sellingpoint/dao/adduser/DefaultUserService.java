package org.mav.sellingpoint.dao.adduser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import org.mav.sellingpoint.dao.admin.IUserDao;

import org.mav.sellingpoint.domain.User;
/**
 * @author bhaskeak
 *
 */
 
	public class DefaultUserService {
		@PostConstruct
		public void createDefaultUser() {
	
			String firstName = "admin";
			String lastName = "admin";
			String userName = "admin";
			String password = "ee00db28a32fa42402faf7a9bd378a0a";
			String email = "admin@gmail.com";
			String contact = "9800000000";
			
			boolean deleted = false;
			 
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserName(userName);
			user.setPassword(password);
			user.setEmail(email);
			user.setContact(contact);
			user.setDeleted(deleted);

			

			try {
				User users = getUserDao().checkDuplicateUser(user);
				if (users == null)
					getUserDao().save(user);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Autowired
		IUserDao userDao;

		public IUserDao getUserDao() {
			return userDao;
		}

		public void setUserDao(IUserDao userDao) {
			this.userDao = userDao;
		}
}
