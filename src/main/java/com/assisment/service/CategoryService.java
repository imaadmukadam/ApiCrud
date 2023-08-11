package com.assisment.service;

import org.springframework.data.domain.Page;

import com.assisment.DTO.CategoryDTO;

public interface CategoryService {
	 Page<CategoryDTO> getAllCategories(int page, int size);
	 CategoryDTO getCategoryById(Long id);
	    CategoryDTO createCategory(CategoryDTO categoryDTO);
	    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
	    void deleteCategory(Long id);
}
