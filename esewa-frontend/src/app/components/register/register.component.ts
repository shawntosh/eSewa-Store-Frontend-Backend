import { Component, OnInit } from '@angular/core';
import {Register} from "./register";
import {UsersDataService} from "../../services/users-data.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  formData: Register = {
    fullName: '',
    email: '',
    password: '',
    address: '',
    contact: 0
  };

  constructor(private userDataService:UsersDataService,
  private toast :ToastrService
) { }

  ngOnInit(): void {
   }

  onSubmits() :void {
    this.userDataService.createUser(this.formData).subscribe({
      next:(response)=>{
        console.log("successfully save")
        this.toast.success("successfully register")
      },
      error:(error)=>{
        this.toast.error(error)
      }
    })

  }

}
