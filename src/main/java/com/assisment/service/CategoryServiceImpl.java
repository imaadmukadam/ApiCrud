package com.assisment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.assisment.DTO.CategoryDTO;
import com.assisment.entity.Category;
import com.assisment.payloads.ResourceNotFoundException;
import com.assisment.repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
    private CategoryRepository categoryRepository;
	public Page<CategoryDTO> getAllCategories(int page, int size) {
	    PageRequest pageable = PageRequest.of(page, size);
	    Page<Category> categoryPage = categoryRepository.findAll(pageable);
	    
	    return categoryPage.map(category -> {
	        CategoryDTO dto = new CategoryDTO();
	        dto.setId(category.getId());
	        dto.setName(category.getName());
	        return dto;
	    });
	}
	@Override
	public CategoryDTO getCategoryById(Long id) {
		Optional<Category> categoryOptional = categoryRepository.findById(id);
	    if (categoryOptional.isPresent()) {
	        Category category = categoryOptional.get();
	        CategoryDTO dto = new CategoryDTO();
	        dto.setId(category.getId());
	        dto.setName(category.getName());
	        return dto;
	    } else {
	        throw new ResourceNotFoundException("Category not found with id: " + id);
	    }
	}
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
	    category.setName(categoryDTO.getName());
	    category = categoryRepository.save(category);
	    categoryDTO.setId(category.getId());
	    return categoryDTO;
	}
	@Override
	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
		 Optional<Category> categoryOptional = categoryRepository.findById(id);
		    if (categoryOptional.isPresent()) {
		        Category category = categoryOptional.get();
		        category.setName(categoryDTO.getName());
		        category = categoryRepository.save(category);
		        categoryDTO.setId(category.getId());
		        return categoryDTO;
		    } else {
		        throw new ResourceNotFoundException("Category not found with id: " + id);
		    }
	}
	@Override
	public void deleteCategory(Long id) {
		   Optional<Category> categoryOptional = categoryRepository.findById(id);
		    if (categoryOptional.isPresent()) {
		        categoryRepository.deleteById(id);
		    } else {
		        throw new ResourceNotFoundException("Category not found with id: " + id);
		    }
		
	}

}
