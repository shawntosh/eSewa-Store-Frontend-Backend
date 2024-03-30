package com.esewashopping.product;

import com.esewashopping.product.file.FileService;
import com.esewashopping.shared.ApiResponse;
import com.esewashopping.shared.Status;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")

public class ProductController {

    private final ProductService productService;

    private final FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/addProduct/{catId}/{cId}")
    public ResponseEntity<String> addProduct(@RequestPart("image") MultipartFile image, @RequestPart ProductDto productDto, @PathVariable Integer catId, @PathVariable Integer cId) throws IOException {
        String fileName = this.fileService.uploadImage(path, image);
        return new ResponseEntity<>(productService.addProduct(productDto, catId, cId, fileName), HttpStatus.CREATED);
    }

    @PostMapping("/update/{pid}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer pid) {
        return ResponseEntity.ok(productService.updateProduct(productDto, pid));
    }

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer pid) {
        productService.deleteProduct(pid);
        return new ResponseEntity<>(new ApiResponse("Product Deleted successfully", false), HttpStatus.OK);
    }

    @GetMapping("/productById/{pid}")
    public ResponseEntity<ProductDto> productById(@PathVariable Integer pid) {
        return ResponseEntity.ok(productService.getById(pid));
    }

    @GetMapping("/allProduct")
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/productByCategory/{catId}")
    public ResponseEntity<List<ProductDto>> productByCategory(@PathVariable Integer catId) {
        return ResponseEntity.ok(productService.productByCategory(catId));
    }

    @GetMapping("/productByName/{name}")
    public ResponseEntity<List<ProductDto>> productByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.productByName(name));
    }

    @GetMapping("/productsAddByCustomer/{cId}")
    public ResponseEntity<List<ProductDto>> productsAddByCustomer(@PathVariable Integer cId) {
        return ResponseEntity.ok(productService.productaddByCustomer(cId));
    }


    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

    @GetMapping("/productByStatus/{status}")
    public ResponseEntity<List<ProductDto>> productByStatus(@PathVariable String status){
        return ResponseEntity.ok(productService.productByStatus(Status.valueOf(status)));
    }

    @GetMapping("/changeStatus/{pid}")
    public ResponseEntity<String> changeProductStatus(@PathVariable Integer pid){
        return ResponseEntity.ok(productService.changeProductStatus(pid));
    }


}
