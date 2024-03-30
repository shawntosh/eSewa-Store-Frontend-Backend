package com.esewashopping.category;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer catId);

    void deleteCategory(Integer catId);

    CategoryDto categoryById(Integer catId);

    List<CategoryResponseDTO> getAllCategory();
}
