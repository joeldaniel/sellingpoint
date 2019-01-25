package org.mav.sellingpoint.dao.admin;

import java.util.List;

import org.mav.sellingpoint.dao.IGenericDAO;
import org.mav.sellingpoint.domain.Product;

public interface IProductDao extends IGenericDAO<Product>{
	public Product editProduct(Product product) throws Exception;
	public List<Product> getSellProducts();
	public List<Product> getBuyProducts();
	
}