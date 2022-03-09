import { Component, OnInit } from '@angular/core';
import {Products} from "../../model/products.model";
import {MasterService} from "../../service/master.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  Listproducts! : Products[]
  constructor(private mast: MasterService) { }

  ngOnInit(): void {
    this.mast.ListProducts().subscribe({
      next: hasil =>{
        this.Listproducts = hasil
      },
      error: err => {
        console.log(err)
      }, complete: ()=>{

      }
    })
  }

}
