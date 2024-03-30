import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor() {
  }
  private customerId: number | null = null;
  private email:string|null=null;

  setCustomerId(id: number): void {
    this.customerId = id;
    console.log(this.customerId);
  }

  getCustomerId(): number | null {
    return this.customerId;
  }

  setCustomerEmail(email:string){
    this.email=email;
  }

  getCustomerEmail():string|null{
    return this.email;
  }
}
