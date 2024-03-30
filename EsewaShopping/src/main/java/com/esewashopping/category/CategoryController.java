package com.esewashopping.category;

import com.esewashopping.shared.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PostMapping("/updateCategory/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer catId) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, catId));
    }

    @DeleteMapping("/delete/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
        categoryService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/getById/{catId}")
    public ResponseEntity<CategoryDto> categoryById(@PathVariable Integer catId) {
        return ResponseEntity.ok(categoryService.categoryById(catId));
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

}
