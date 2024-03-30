import { Injectable } from '@angular/core';
import {Endpoints} from "../../shared/endpoints/endpoints";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl=Endpoints.loginUser

  constructor(private http:HttpClient) { }

  login(email:string,password:string):Observable<number>{
    const loginRequest={email,password};
    return this.http.post<number>(this.apiUrl,loginRequest);
  }
}
