import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Products} from "../model/products.model";

@Injectable({
  providedIn: 'root'
})
export class MasterService {
  constructor(private http: HttpClient) { }

  ListProducts(): Observable<any>{
    return this.http.get(environment.baseUrl+'/list').pipe(map(data => data))
  }
  saveProducts(products: Products ): Observable<any>{
    let url = '/input';
    return this.http.post(environment.baseUrl+url, products)
      .pipe( map(data => data))
  }
  getProductsById(id: number): Observable<any> {
    return this.http.get(environment.baseUrl + '/findById/' + id)
      .pipe(map(data => data))
  }
}
