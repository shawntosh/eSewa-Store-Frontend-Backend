import {Component, OnInit} from '@angular/core';
import {CartService} from "../../services/cart/cart.service";
import {ToastrService} from "ngx-toastr";
import {CustomerService} from "../../services/customer/customer.service";


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  productDetails: any[] = [];
  constructor(
    private cartService: CartService,
    private toastr: ToastrService,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    // You may want to load the cart items when the component initializes
    this.loadCartItems();
  }
  private loadCartItems(): void {
    const customerId = this.customerService.getCustomerId();
    if (customerId) {
      this.cartService.getCartsItems(customerId).subscribe({
        next: (productDetails) => {
          this.productDetails=productDetails;
          console.log(productDetails)
        },
        error:(err)=>{
          console.error("Error Loading product details",err)
        }
      })
    }
  }

  removeProduct(productId: any): void {
    const customerId: number | null = this.customerService.getCustomerId();

    if (customerId && productId) {
      this.cartService.deleteCartsItems(customerId, productId).subscribe({
        next: () => {
          this.loadCartItems();
          this.toastr.warning('Product Successfully Removed');
        },
        error: (error) => {
          // Handle the error if needed
          console.error('Error removing product', error);
        }
      });
    }
  }

}





