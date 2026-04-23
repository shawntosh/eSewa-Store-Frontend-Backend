import { Component } from '@angular/core';
import {CategoryService} from "../../services/category/category.service";
import {ICategory} from "./category";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent{

  categories: ICategory[]=[]

  constructor(private categoryService:CategoryService,
              private toaster:ToastrService) {


  }
/*
  getAllCategory(): void {
    this.categoryService.getAllProducts().subscribe({
      next: (categories:ICategory) => {
        this.categories = categories;
      },
      error: () => {
        this.toaster.error("Error fetching");
      },
    });
  }
*/



}
