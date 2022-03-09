import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MasterService} from "../../service/master.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Products} from "../../model/products.model";

@Component({
  selector: 'app-insert',
  templateUrl: './insert.component.html'
})
export class InsertComponent implements OnInit {
  formInsert!: FormGroup;
  id!: number;

  constructor(
    private formBuild: FormBuilder,
    private mast: MasterService,
    private ruter: Router,
    private route: ActivatedRoute) {
    this.formInsert = this.formBuild.group({
      'id': new FormControl(null,[Validators.required,
        Validators.minLength(1)]),
      'name': new FormControl(null,[Validators.required,
        Validators.minLength(4)]),
      'price': new FormControl(null,[Validators.required,
        Validators.minLength(3)]),
      'category': new FormControl(null,[Validators.required,
        Validators.minLength(2)]),
      'create_date': new FormControl(null,[Validators.required,
        Validators.minLength(2)]),
      'create_by': new FormControl(null,[Validators.required,
        Validators.minLength(2)])
    })
  }

  ngOnInit(): void {
    this.route.params.subscribe(rute => {
      this.id = rute ['id'];
      if (this.id) {
        console.log(this.id)
        this.mast.getProductsById(this.id).subscribe({
          next: value => {
            this.formInsert.controls['id'].setValue(value.id)
            this.formInsert.controls['name'].setValue(value.name)
            this.formInsert.controls['price'].setValue(value.price)
            this.formInsert.controls['category'].setValue(value.category)
            this.formInsert.controls['create_date'].setValue(value.create_date)
            this.formInsert.controls['create_by'].setValue(value.create_by)
          }
        })
      }
    })
  }

  simpan(): void {
    console.log(this.formInsert.controls)
    console.log(this.formInsert.valid)
    if (this.formInsert.valid) {
      let products = <Products>{};
      products.id = this.formInsert.controls['id'].value
      products.name = this.formInsert.controls['name'].value
      products.price = this.formInsert.controls['price'].value
      products.category = this.formInsert.controls['category'].value
      products.create_date = this.formInsert.controls['create_date'].value
      products.create_by = this.formInsert.controls['create_by'].value
      if (this.id) {
        products.id = this.id;
      }
      this.mast.saveProducts(products).subscribe({
        next: hasil => {
          alert('Simpan Berhasil')
        },
        error: err => {
          console.log(err)
        }
      });
    } else {
      alert("Form Harus Terisi")
    }
  }
}
