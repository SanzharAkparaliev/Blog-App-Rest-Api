package com.example.blogapp.impl;

import com.example.blogapp.entities.Category;
import com.example.blogapp.exceptions.ResourseNotFoundException;
import com.example.blogapp.payloads.CategoryDto;
import com.example.blogapp.repositories.CategoryRepository;
import com.example.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = modelMapper.map(categoryDto,Category.class);
        Category addedCat = categoryRepository.save(cat);
        return modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category "," Category Id ",categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedcat = categoryRepository.save(cat);
        return modelMapper.map(updatedcat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
       Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category"," CategoryId",categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category","CategoryId",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
       List<Category> categories = categoryRepository.findAll();
       List<CategoryDto> categoryDtos =categories.stream().map((cat) -> modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
