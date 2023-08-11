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

import com.assisment.DTO.CategoryDTO;
import com.assisment.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	 @Autowired
	    private CategoryService categoryService;

	    @GetMapping
	    public ResponseEntity<Page<CategoryDTO>> getAllCategories(@RequestParam(defaultValue = "0") int page,
	                                                              @RequestParam(defaultValue = "10") int size) {
	        Page<CategoryDTO> categories = categoryService.getAllCategories(page, size);
	        return ResponseEntity.ok(categories);
	    }
	    
	    @PostMapping
	    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
	        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
	        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
	        return ResponseEntity.ok(categoryDTO);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
	        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
	        return ResponseEntity.ok(updatedCategory);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	        categoryService.deleteCategory(id);
	        return ResponseEntity.noContent().build();
	    }
}
