package org.mav.sellingpoint.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userlog")
public class UserLog implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1913075621432209038L;

	@Id
	@Column(unique = true, nullable = false, length = 10)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String action;

	private String userName;

	private String email;

	private Date logDateTime;

	private String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(Date logDateTime) {
		this.logDateTime = logDateTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return "[ Action=" + action + ", User Name=" + userName + ", User Email=" + email + ", Date and Time="
				+ logDateTime + ", Message=" + message + "]";
	}

}
