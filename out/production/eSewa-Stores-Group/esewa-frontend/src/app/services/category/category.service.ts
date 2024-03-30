import { Injectable } from '@angular/core';
import {Endpoints} from "../../shared/endpoints/endpoints";
import {Observable, observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {IProduct} from "../../components/products/product";
import {ICategory} from "../../components/category/category";


@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  apiUrl=Endpoints.getAllCategory

  constructor(private http:HttpClient) {
  }

  getAllProducts(): Observable<ICategory[]> {
    console.log("getAllProducts");
    return this.http.get<ICategory[]>(this.apiUrl);
  }




}
