import { Injectable } from '@angular/core';
import {IProduct} from "../../components/products/product";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Endpoints} from "../../shared/endpoints/endpoints";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private selectedProducts: IProduct[] = [];
  private apiUrl = Endpoints.addToCart;
  private apiUrls="/api/v1/cart/getProductByCustomerId"
  private apiUrlss ="/api/v1/cart/deleteProductById"


  constructor(private http: HttpClient) {}

  addToCarts(customerId: number, productId: number | undefined, quantity: number): Observable<any> {
    const headers = { 'Content-Type': 'application/json' };

    // Create the request parameters
    let params = new HttpParams();
    params = params.append('customerId', customerId.toString());
    if (productId !== undefined) {
      params = params.append('productId', productId.toString());
    }
    params = params.append('quantity', quantity.toString());

    return this.http.post(this.apiUrl, null, { headers, params });
  }


  getCartsItems(customerId: number): Observable<any> {
    const url = `${this.apiUrls}?customerId=${customerId}`;
    return this.http.get<any>(url);
  }

  deleteCartsItems(customerId: number | null, productId: number): Observable<any> {
    // Ensure customerId is not null before making the request
    if (customerId !== null) {
      let params = new HttpParams()
        .set('customerId', customerId.toString())
        .set('productId', productId.toString());

      return this.http.delete(this.apiUrlss, { params });
    } else {
      console.error('customerId is null');
      return new Observable();
    }
  }




}

