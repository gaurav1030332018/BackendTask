package com.springrest.springrest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springrest.springrest.entities.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	
	@Query("SELECT p FROM Product p WHERE "+"p.name LIKE CONCAT('%',:query,'%')"+
	         "Or p.type LIKE CONCAT('%',:query,'%')"+
			 "Or p.category LIKE CONCAT('%',:query,'%')")
	List<Product> searchProducts(String query);	
	
	List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);
	List<Product> findByCategoryAndPriceBetween(String category, Integer minPrice, Integer maxPrice);
    List<Product> findByTypeAndPriceBetween(String type,Integer minPrice,Integer maxPrice);
}
