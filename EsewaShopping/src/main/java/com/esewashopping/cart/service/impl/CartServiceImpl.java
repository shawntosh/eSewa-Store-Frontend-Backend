package com.esewashopping.cart.service.impl;

import com.esewashopping.cart.dto.CartItemDTO;
import com.esewashopping.cart.entity.CartItem;
import com.esewashopping.cart.repo.CartItemRepo;
import com.esewashopping.cart.service.CartService;
import com.esewashopping.customer.Customer;
import com.esewashopping.customer.CustomerRepo;
import com.esewashopping.exception.NotFoundException;
import com.esewashopping.product.Product;
import com.esewashopping.product.ProductRepo;
import com.esewashopping.product.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.esewashopping.shared.ErrorMessage.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    private final CartItemRepo cartItemRepo;

    @Override
    public CartItemDTO addProductToCart(Integer customerId, Integer productId, Integer quantity) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        Optional<Product> productOptional = productRepo.findById(productId);

        if (customerOptional.isPresent() && productOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Product product = productOptional.get();

            Set<CartItem> cartItems = customer.getCartItems();

            if (cartItems == null) {
                cartItems = new HashSet<>();
                customer.setCartItems(cartItems);
            }

            Optional<CartItem> existingCartItemOptional = findCartItemByProduct(cartItems, product);

            if (existingCartItemOptional.isPresent()) {
                CartItem existingCartItem = existingCartItemOptional.get();
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                existingCartItem.setTotalPrice(existingCartItem.getProduct().getPrice() * existingCartItem.getQuantity());
                cartItemRepo.save(existingCartItem);
                customerRepo.save(customer);

                return convertToCartItemDTO(existingCartItem);
            } else {
                CartItem newCartItem = new CartItem();
                newCartItem.setCustomer(customer);
                newCartItem.setProduct(product);
                newCartItem.setQuantity(quantity);
                newCartItem.setTotalPrice(product.getPrice() * quantity);

                cartItemRepo.save(newCartItem);

                return convertToCartItemDTO(newCartItem);
            }
        } else {
            throw new NotFoundException("Customer or Product not found");
        }
    }


    private Optional<CartItem> findCartItemByProduct(Set<CartItem> cartItems, Product product) {
        return cartItems.stream().filter(cartItem -> cartItem.getProduct().equals(product)).findFirst();
    }

    @Override
    public CartItemDTO getCartItem(Integer cartItemId) {
        Optional<CartItem> cartItemOptional = cartItemRepo.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem1 = cartItemOptional.get();
            return convertToCartItemDTO(cartItem1);
        } else {
            throw new NotFoundException(NOT_FOUND);
        }
    }

    @Override
    public List<ProductResponseDTO> getProductByCustomerId(Integer customerId) {
        return cartItemRepo.findProductsByCustomerId(customerId);


    }

    @Override
    public void deleteProductFromCart(Integer customerId, Integer productId) {
        cartItemRepo.removeProductFromCart(customerId, productId);
    }


    private CartItemDTO convertToCartItemDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getCartItemId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setTotalPrice((double) cartItem.getTotalPrice());
        cartItemDTO.setCustomerId(cartItem.getCustomer().getCId());
        cartItemDTO.setProductId(cartItem.getProduct().getPid());
        return cartItemDTO;
    }


}


