package com.esewashopping.product;

import com.esewashopping.category.Category;
import com.esewashopping.category.CategoryRepo;
import com.esewashopping.customer.Customer;
import com.esewashopping.customer.CustomerRepo;
import com.esewashopping.exception.ResourceNotFoundException;
import com.esewashopping.product.file.FileService;
import com.esewashopping.shared.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final ModelMapper modelMapper;

    private final CategoryRepo categoryRepo;

    private final CustomerRepo customerRepo;

    private final FileService fileService;

    @Value("${project.image}")
    private String path;


    @Override
    public String addProduct(ProductDto productDto, Integer catId, Integer cId, String fileName) throws IOException {
        Product product = modelMapper.map(productDto, Product.class);
        product.setProductImage(fileName);
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", catId));
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", cId));
        float total = product.getPrice() * product.getQuantity();
        if (customer.getStatus().equals(Status.UNVERIFIED) && total >= 5000) {
            fileService.deleteImage(path, product.getProductImage());
            return "Sorry you are unverified customer and product total price exceeds 5000";
        } else {
            product.setCustomer(customer);
            product.setCategory(category);
            productRepo.save(product);
            return "Successfully added Product";
        }
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer pid) {
        Product existingProduct = productRepo.findById(pid).orElseThrow(() -> new ResourceNotFoundException("Product", "ProductId", pid));
        Product updateProduct = modelMapper.map(productDto, Product.class);
        existingProduct.setName(updateProduct.getName());
        existingProduct.setQuantity(updateProduct.getQuantity());
        existingProduct.setPrice(updateProduct.getPrice());
        existingProduct.setDescription(updateProduct.getDescription());
        productRepo.save(existingProduct);
        return modelMapper.map(productRepo.save(existingProduct), ProductDto.class);
    }


    @Override
    public void deleteProduct(Integer pid) {
        Product product = productRepo.findById(pid).orElseThrow(() -> new ResourceNotFoundException("Product", "ProductId", pid));
        productRepo.delete(product);
    }

    @Override
    public ProductDto getById(Integer pid) {
        Product product = productRepo.findById(pid).orElseThrow(() -> new ResourceNotFoundException("Product", "ProductId", pid));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductResponseDTO> getAllProduct() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(li -> modelMapper.map(li, ProductResponseDTO.class))
                .toList();
    }

    @Override
    public List<ProductDto> productByCategory(Integer catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", catId));
        List<Product> products = productRepo.findByCategory(category);
        return products.stream().map(li -> modelMapper.map(li, ProductDto.class)).toList();
    }

    @Override
    public List<ProductDto> productByName(String name) {
        List<Product> products = productRepo.findByNameContains(name);
        return products.stream().map(li -> modelMapper.map(li, ProductDto.class)).toList();
    }

    @Override
    public List<ProductDto> productaddByCustomer(Integer cId) {
        Customer customer = customerRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", cId));
        List<Product> products = productRepo.findByCustomer(customer);
        return products.stream().map(li -> modelMapper.map(li, ProductDto.class)).toList();
    }

    @Override
    public List<ProductDto> productByStatus(Status status) {
        return null;
    }

    @Override
    public String changeProductStatus(Integer pId) {
        return null;
    }
}
