package org.mav.sellingpoint.dao.admin.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.mav.sellingpoint.dao.admin.IProductDao;
import org.mav.sellingpoint.dao.impl.MySqlDAOImpl;
import org.mav.sellingpoint.domain.Product;

@Repository(value = "productDao")
public class ProductDaoImpl extends MySqlDAOImpl<Product> implements IProductDao {
	private final static Logger LOG = LoggerFactory.getLogger(ProductDaoImpl.class);

	public List<Product> getSellProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> getBuyProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	public Product editProduct(Product product) throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug(">>editProduct()");
		}
		try {
			saveOrUpdate(product);
		} catch (Exception exception) {
			if (LOG.isErrorEnabled()) {
				LOG.error("--editProduct() > " + exception.getMessage(), exception);
			}
			throw new Exception(exception.getMessage(), exception);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("<<editProduct()");
		}

		return product;
	}

}
