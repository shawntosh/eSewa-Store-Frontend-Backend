import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  private isAuthcenticated=false;

  login():void{
    this.isAuthcenticated=true
  }
  logout():void{
    this.isAuthcenticated=false;
  }
  isLoggedIn():boolean{
    return this.isAuthcenticated;
  }
}
