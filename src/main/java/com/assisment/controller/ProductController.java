package com.assisment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assisment.DTO.ProductDTO;
import com.assisment.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	 @Autowired
	    private ProductService productService;

	    @GetMapping
	    public ResponseEntity<Page<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "0") int page,
	                                                           @RequestParam(defaultValue = "10") int size) {
	        Page<ProductDTO> products = productService.getAllProducts(page, size);
	        return ResponseEntity.ok(products);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
	        ProductDTO productDTO = productService.getProductById(id);
	        return ResponseEntity.ok(productDTO);
	    }

	    @PostMapping
	    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
	        ProductDTO createdProduct = productService.createProduct(productDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
	        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
	        return ResponseEntity.ok(updatedProduct);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return ResponseEntity.noContent().build();
	    }
}
