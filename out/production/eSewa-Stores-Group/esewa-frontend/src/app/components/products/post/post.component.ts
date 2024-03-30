import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { switchMap } from 'rxjs/operators';
import {ICategory} from "../../category/category";
import {PostProductService} from "../../../services/post/post-product.service";
import {CategoryService} from "../../../services/category/category.service";
import {CustomerService} from "../../../services/customer/customer.service";


@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  productForm!: FormGroup;
  categories!: ICategory[]; // Ensure to import the ICategory interface
  productImage!: File | null;

  constructor(
    private fb: FormBuilder,
    private postProductService: PostProductService,
    private categoryService: CategoryService,
    private customerService: CustomerService,
    private toaster: ToastrService
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.getAllCategories();
  }

  initForm(): void {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      price: [0, Validators.required],
      quantity: [0, Validators.required],
      description: [''],
      productImage: [''],
      catId: [null, Validators.required],
    });
  }

  getAllCategories(): void {
    this.categoryService.getAllProducts().subscribe(
      (categoryList) => {
        this.categories = categoryList;
      },
      (error) => {
        this.toaster.error(`Error fetching categories: ${error.message}`);
      }
    );
  }

  onFileSelected(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      this.productImage = inputElement.files[0];
    }
  }

  submitForm(): void {
    if (this.productForm.valid) {
      const customerId = this.customerService.getCustomerId();

      if (customerId && customerId !== undefined) {
        const formData: FormData = new FormData();

        if (this.productImage) {
          formData.append('image', this.productImage);
        }

        const productDto = {
          name: this.productForm.value.name,
          price: parseFloat(this.productForm.value.price),
          quantity: parseInt(this.productForm.value.quantity),
          description: this.productForm.value.description,
          productImage: this.productImage
        };

        formData.append('productDto', JSON.stringify(productDto));

        const catId = this.productForm.value.catId;

        this.postProductService.postProduct(formData, catId, customerId).subscribe(
          (response) => {
            this.toaster.success('Product posted successfully');
            this.resetForm();
          },
          (error) => {
            this.toaster.error(`Error posting product: ${error.message}`);
          }
        );
      } else {
        console.error('Customer ID is null or undefined');
      }
    }
  }

  resetForm(): void {
    this.productForm.reset({
      name: '',
      price: 0,
      quantity: 0,
      description: '',
      productImage: '',
      catId: null,
    });
    this.productImage = null; // Reset the productImage after submitting the form
  }
}
