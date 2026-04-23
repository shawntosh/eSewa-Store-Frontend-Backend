import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Endpoints} from "../shared/endpoints/endpoints";
import {Register} from "../components/register/register";

@Injectable({
  providedIn: 'root'
})
export class UsersDataService {

  private apiUrl=Endpoints.registerUser;

  constructor(private http:HttpClient) { }

  createUser(formData:Register):Observable<Register>{
    console.log("this is user service")
    return this.http.post<any>(this.apiUrl,formData);
  }
}
