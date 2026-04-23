import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../services/login/login.service";
import {ToastrService} from "ngx-toastr";
import { AuthenticationService} from "../../services/auth/authentication.service";
import {CustomerService} from "../../services/customer/customer.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formData: any = {};

  email: string = '';
  password: string = '';

  constructor(private loginService: LoginService,
              private toastr: ToastrService,
              private authService: AuthenticationService,
              private customerService: CustomerService) { }

  ngOnInit(): void {
  }

  login(): void {
    if (!this.email || !this.password) {
      this.toastr.error('Please enter both email and password.', 'Validation Error');
      return;
    }

    this.loginService.login(this.email, this.password).subscribe(
      (customerId) => {
        this.authService.login();
        this.customerService.setCustomerId(customerId);
        this.customerService.setCustomerEmail(this.email)
        console.log(customerId);
        console.log(this.email)

        this.toastr.success('Login Successful');
      },
      error => {
        this.toastr.error('Cannot login', 'Login Failed');
      }
    );
  }
}
