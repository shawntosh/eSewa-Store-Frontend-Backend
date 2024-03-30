package com.esewashopping.product;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Integer pid;
    private String name;
    private Float price;
    private Integer quantity;
    private String description;
    private String productImage;

    public ProductResponseDTO(Integer pid, String name, Float price, Integer quantity, String description, String productImage) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.productImage = productImage;
    }


    public ProductResponseDTO() {
    }
}
