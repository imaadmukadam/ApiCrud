package com.assisment.service;

import org.springframework.data.domain.Page;

import com.assisment.DTO.ProductDTO;

public interface ProductService {
	Page<ProductDTO> getAllProducts(int page, int size);
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
