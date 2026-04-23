import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductsComponent} from './components/products/products.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {CartComponent} from "./components/cart/cart.component";
import {CheckoutComponent} from "./components/checkout/checkout/checkout.component";
import {PostComponent} from "./components/products/post/post.component";

const routes: Routes = [
  {path: 'home', component:ProductsComponent },
  {path: '', component:ProductsComponent },
  {path:'post', component:PostComponent},
  {path:'login', component:LoginComponent},
  {path:'register', component:RegisterComponent},
  {path:'add-to-cart',component:CartComponent},
  {path:'checkout',component:CheckoutComponent},
  {path:'admin',component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }



