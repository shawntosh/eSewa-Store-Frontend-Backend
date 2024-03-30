import { Injectable } from '@angular/core';
import {IProduct} from "../../components/products/product";
import {Observable} from "rxjs";
import {Endpoints} from "../../shared/endpoints/endpoints";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Register} from "../../components/register/register";

@Injectable({
  providedIn: 'root'
})
export class PostProductService {

  apiUrl=Endpoints.addProduct

  constructor(private http:HttpClient) { }


  // postProduct(product: IProduct[]):Observable<any>{
  //   return this.http.post<any>(this.apiUrl,product);
  // }


  postProduct(formData: FormData, customerId: number | null, catId: number): Observable<any> {
    const headers = new HttpHeaders({ 'enctype': 'multipart/form-data' });

    return this.http.post<any>(`${this.apiUrl}/${catId}/${customerId}`, formData, { headers });
  }


}
