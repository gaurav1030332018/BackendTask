package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Product;
import com.springrest.springrest.services.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
public class MyController {
	 
	@Autowired
	private ProductService productService;
	
	
	//Get the products
	@GetMapping("/products")
	public List<Product> getProducts()
	{
		return this.productService.getProducts();
	}
	
	//Get  product by id
	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable String productId)
	{
		return this.productService.getProduct(Long.parseLong(productId));
	}
	
	//AddProducts
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product)
	{
		return this.productService.addProduct(product);
	}
	
	//update product
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product)
	{
		return this.productService.updateProduct(product);
	}
	
	//Delete Product
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String productId)
	{
		try {
			this.productService.deleteProduct(Long.parseLong(productId));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Search products
	@ApiOperation(value = "Search products by name,type,category,price")
	@GetMapping("products/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query)
	{
		return ResponseEntity.ok(productService.searchProducts(query));
	}
	
	//SearchProductByMinMaxPriceRange
	@ApiOperation(value = "Search product by min-max price range")
	@GetMapping("products/price")
	public ResponseEntity<List<Product>> getProductByPrice(@RequestParam Integer minPrice, @RequestParam Integer maxPrice)
	{
	    return new ResponseEntity<List<Product>>(productService.findByPriceBetween(minPrice, maxPrice),HttpStatus.OK);
	}
	
	//SearchProductByCategoryMinMaxPricerange
	@ApiOperation(value = "Get min-max price range of product category")
	@GetMapping("products/cprice")
	public ResponseEntity<List<Product>> getProductCategoryPrice(@RequestParam String category,@RequestParam Integer minPrice, @RequestParam Integer maxPrice)
	{
	    return new ResponseEntity<List<Product>>(productService.findByCategoryAndPriceBetween(category,minPrice, maxPrice),HttpStatus.OK);
	}
	
	//searchProductByTypeMinMaxPriceRange
	@ApiOperation(value = "Get min-max price range of product type")
	@GetMapping("products/tprice")
	public ResponseEntity<List<Product>> getProductTypePrice(@RequestParam String type,@RequestParam Integer minPrice, @RequestParam Integer maxPrice)
	{
	    return new ResponseEntity<List<Product>>(productService.findByTypeAndPriceBetween(type,minPrice, maxPrice),HttpStatus.OK);
	}

}

