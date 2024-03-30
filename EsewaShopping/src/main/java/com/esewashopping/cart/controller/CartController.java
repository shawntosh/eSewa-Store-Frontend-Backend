package com.esewashopping.cart.controller;

import com.esewashopping.cart.dto.CartItemDTO;
import com.esewashopping.cart.service.CartService;
import com.esewashopping.product.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.esewashopping.shared.Routes.ROOT_CART;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping(ROOT_CART + "/addProduct")
    public CartItemDTO addProductToCart(@RequestParam Integer customerId,
                                        @RequestParam Integer productId,
                                        @RequestParam Integer quantity) {
        return cartService.addProductToCart(customerId, productId, quantity);
    }

    @GetMapping(ROOT_CART + "/allCartItem")
    public CartItemDTO getCartItem(@RequestParam Integer cartItemId) {
        return cartService.getCartItem(cartItemId);
    }

    @GetMapping(ROOT_CART + "/getProductByCustomerId")
    public List<ProductResponseDTO> getProductByCustomerId(@RequestParam Integer customerId) {
        return cartService.getProductByCustomerId(customerId);
    }

    @DeleteMapping(ROOT_CART+"/deleteProductById")
    public void  deletProductById(@RequestParam Integer customerId,@RequestParam Integer productId){
        cartService.deleteProductFromCart(customerId,productId);
    }



}
