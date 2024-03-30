package com.esewashopping.cart.service;


import com.esewashopping.cart.dto.CartItemDTO;
import com.esewashopping.product.ProductResponseDTO;

import java.util.List;

public interface CartService {

    CartItemDTO addProductToCart(Integer customerId, Integer productId, Integer quantity);

    CartItemDTO getCartItem(Integer cartItemId);

    List<ProductResponseDTO> getProductByCustomerId(Integer customerId);

    void deleteProductFromCart(Integer customerId, Integer productId);


}
