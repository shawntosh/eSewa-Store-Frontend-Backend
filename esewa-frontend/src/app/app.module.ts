import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {FooterComponent} from './components/footer/footer.component';
import {NavbarComponent} from './components/navbar/navbar.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {HttpClientModule} from '@angular/common/http';
import {ProductsComponent} from './components/products/products.component';
import {PostComponent} from './components/products/post/post.component'
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MenubarModule} from 'primeng/menubar';
import {DividerModule} from 'primeng/divider';
import {ChipsModule} from "primeng/chips";
import {ButtonModule} from "primeng/button";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import {InputTextareaModule} from "primeng/inputtextarea";
import {PaginatorModule} from "primeng/paginator";
import {SpinnerModule} from "primeng/spinner";
import { CartComponent } from './components/cart/cart.component';
import {CheckboxModule} from "primeng/checkbox";
import {RadioButtonModule} from "primeng/radiobutton";
import {PaymentComponent} from "./components/payment/payment/payment.component";
import {CheckoutComponent} from "./components/checkout/checkout/checkout.component";
import { CategoryComponent } from './components/category/category.component';
import {AdminModule} from "./admin/admin.module";



@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    ProductsComponent,
    PostComponent,
    PostComponent,
    CartComponent,
    PaymentComponent,
    CheckoutComponent,
    CategoryComponent,


  ],
    imports: [
      AdminModule,
        BrowserModule,
        AppRoutingModule,
        FontAwesomeModule,
        HttpClientModule,
        FormsModule,
        MenubarModule,
        DividerModule,
        ChipsModule,
        ChipsModule,
        ButtonModule,
        BrowserAnimationsModule,
        ToastrModule.forRoot({
            timeOut: 3000,
            preventDuplicates: true,
            closeButton: true,
            onActivateTick: true,
            positionClass: 'toast-top-right'
        }),
        InputTextareaModule,
        PaginatorModule,
        SpinnerModule,
        CheckboxModule,
        RadioButtonModule,
        ReactiveFormsModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
