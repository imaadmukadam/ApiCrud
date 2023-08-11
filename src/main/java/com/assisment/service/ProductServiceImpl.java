package com.assisment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.assisment.DTO.CategoryDTO;
import com.assisment.DTO.ProductDTO;
import com.assisment.entity.Category;
import com.assisment.entity.Product;
import com.assisment.payloads.ResourceNotFoundException;
import com.assisment.repository.CategoryRepository;
import com.assisment.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Page<ProductDTO> getAllProducts(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size);
		Page<Product> productPage = productRepository.findAll(pageable);

		return productPage.map(product -> {
			ProductDTO dto = new ProductDTO();
			dto.setId(product.getId());
			dto.setName(product.getName());

			Category category = product.getCategory();
			if (category != null) {
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setId(category.getId());
				categoryDTO.setName(category.getName());
				dto.setCategory(categoryDTO);
			}

			return dto;
		});
	}

	@Override
	public ProductDTO getProductById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			ProductDTO dto = new ProductDTO();
			dto.setId(product.getId());
			dto.setName(product.getName());

			Category category = product.getCategory();
			if (category != null) {
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setId(category.getId());
				categoryDTO.setName(category.getName());
				dto.setCategory(categoryDTO);
			}

			return dto;
		} else {
			throw new ResourceNotFoundException("Product not found with id: " + id);
		}
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		 Product product = new Product();
		    product.setName(productDTO.getName());

		    if (productDTO.getCategory() != null && productDTO.getCategory().getId() != null) {
		        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategory().getId());
		       System.out.println(categoryOptional);
		        if (categoryOptional.isPresent()) {
		            Category category = categoryOptional.get();
		            product.setCategory(category);
		            System.out.println(category);
		        }
		    }

		    product = productRepository.save(product);
		    productDTO.setId(product.getId());
		    return productDTO;
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Optional<Product> productOptional = productRepository.findById(id);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			product.setName(productDTO.getName());

			if (productDTO.getCategory() != null && productDTO.getCategory().getId() != null) {
				Category category = new Category();
				category.setId(productDTO.getCategory().getId());
				product.setCategory(category);
			} else {
				product.setCategory(null);
			}

			product = productRepository.save(product);
			productDTO.setId(product.getId());
			return productDTO;
		} else {
			throw new ResourceNotFoundException("Product not found with id: " + id);
		}
	}

	@Override
	public void deleteProduct(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);
		if (productOptional.isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Product not found with id: " + id);
		}
	}
}
