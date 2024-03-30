import { Component, OnInit } from '@angular/core';
import { IProduct } from './product';
import { ProductService } from 'src/app/services/product.service';
import {CartService} from "../../services/cart/cart.service";
import {UsersDataService} from "../../services/users-data.service";
import {LoginService} from "../../services/login/login.service";
import {CustomerService} from "../../services/customer/customer.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})

export class ProductsComponent implements OnInit {

  products: IProduct[] = [];

  constructor(private productService: ProductService,
              private cartService:CartService,
              private customerService:CustomerService,
              private toastr:ToastrService,
              private router :Router
  ) { }

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void {
    this.productService.getAllProducts().subscribe(
      data => {
        this.products = data;
        console.log(data);
        console.log("Hello World");
      }
    );
  }

  // products.component.ts

  addToCart(productId: number | undefined): void {
    const customerId = this.customerService.getCustomerId();

    if (!customerId) {
      this.toastr.error('Customer ID not available', 'Error');
      return;
    }

    const quantity = 1;

    this.cartService.addToCarts(customerId, productId, quantity).subscribe(
      () => {
        this.router.navigate(['/add-to-cart'])
        console.log(`Product ${productId} added to the cart for Customer ${customerId}`);
        this.toastr.success('Product added to the cart');
      },
      (error) => {
        console.error('Error adding product to the cart', error);
        this.toastr.error(`Failed to add product to the cart: ${error.message}`);
      }
    );
  }










  

}
