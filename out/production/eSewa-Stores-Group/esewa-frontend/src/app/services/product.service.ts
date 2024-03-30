import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {IProduct} from '../components/products/product';
import {Endpoints} from "../shared/endpoints/endpoints";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = Endpoints.product;

  constructor(private http: HttpClient) {}

  getAllProducts(): Observable<IProduct[]> {
    console.log("getAllProducts");
    return this.http.get<IProduct[]>(this.apiUrl);
  }


}
