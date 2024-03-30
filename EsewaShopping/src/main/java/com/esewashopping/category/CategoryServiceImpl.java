package com.esewashopping.category;

import com.esewashopping.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;

    private final CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return modelMapper.map(categoryRepo.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", catId));
        if (categoryDto.getName() != null) {
            category.setName(categoryDto.getName());
        }
        Category updateCategory = categoryRepo.save(category);
        return modelMapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", catId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto categoryById(Integer catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", catId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategory() {
        List<Category> listOfCategory = categoryRepo.findAll();
        return listOfCategory.stream()
                .map(category -> modelMapper.map(category, CategoryResponseDTO.class))
                .collect(Collectors.toList());
    }

}
