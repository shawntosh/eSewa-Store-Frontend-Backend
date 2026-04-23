import { Component, OnInit } from '@angular/core';
import { PrimeIcons } from 'primeng/api';
import {AuthenticationService} from "../../services/auth/authentication.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

 items: any[] = [
  { label: 'Home', icon: 'pi pi-fw pi-home', routerLink: '/home' },
  { label: 'PostProduct', icon: 'pi pi-fw pi-info-circle', routerLink: '/post' },
  { label: '', icon: 'pi pi-fw pi-cart-plus', routerLink: '/add-to-cart' }, // Adjusted icon

  // Add more menu items as needed
];


  constructor(private authService:AuthenticationService) { }

  ngOnInit(): void {
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  logout(): void {
    this.authService.logout();
    // You can add additional logic after logout if needed
  }


}
