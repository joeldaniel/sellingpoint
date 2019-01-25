package org.mav.sellingpoint.dao;

import java.io.Serializable;
import java.util.List;

import org.mav.sellingpoint.exception.WorkbenchException;

public interface IGenericDAO<T> {

	public T save(T entity) throws WorkbenchException;
	
	public void saveOrUpdate(T pojo) throws WorkbenchException;

	public void delete(T entity) throws WorkbenchException;

	public T find(Serializable entityId, Class<T> clazz) throws WorkbenchException;

	public List<T> findAll(Class<T> entityClass) throws WorkbenchException;
}
