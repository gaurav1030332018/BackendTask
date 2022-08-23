package com.springrest.springrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.ProductDao;
import com.springrest.springrest.entities.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
    //GetAllProducts
	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}
	
    //GetProductById
	@Override
	public Product getProduct(long productId) {
		// TODO Auto-generated method stub
		return productDao.getOne(productId);
	}
    
	//AddProduct
	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		
		productDao.save(product);
		return product;
	}
    
	//updateProduct
	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.save(product);
	}

	//DeleteProduct
	@Override
	public void deleteProduct(long productId) {
		// TODO Auto-generated method stub
		 
		Product entity=productDao.getOne(productId);
	    productDao.delete(entity);
	}
    
	//SearchProduct
	@Override
	public List<Product> searchProducts(String query) {
		// TODO Auto-generated method stub
		List<Product> products=productDao.searchProducts(query);
		return products;
	}
	
    //SearchProductByMinMaxRange
	@Override
	public List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice) {
		// TODO Auto-generated method stub
		List<Product> products=productDao.findByPriceBetween(minPrice, maxPrice);
		return products;
	}
    
	//FindMinMaxPriceRangeByCategory
	@Override
	public List<Product> findByCategoryAndPriceBetween(String category, Integer minPrice, Integer maxPrice) {
		// TODO Auto-generated method stub
		List<Product> products=productDao.findByCategoryAndPriceBetween(category,minPrice, maxPrice);

		return products;
	}
    
	//FindMinMaxRangeByType
	@Override
	public List<Product> findByTypeAndPriceBetween(String type, Integer minPrice, Integer maxPrice) {
		// TODO Auto-generated method stub
	    List<Product> products= productDao.findByTypeAndPriceBetween(type, minPrice,maxPrice);

		return products;
	}
	
	
	

}
