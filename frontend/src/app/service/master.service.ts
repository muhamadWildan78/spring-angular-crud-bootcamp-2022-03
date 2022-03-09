import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MasterService {
  constructor(private http: HttpClient) { }

  ListProducts(): Observable<any>{
    return this.http.get(environment.baseUrl+'/list').pipe(map(data => data))
  }
}
