import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home/home.component";
import {InsertComponent} from "./insert/insert/insert.component";

const routes: Routes = [
  {
    path:'home' , component:HomeComponent
  },
  {
    path:'insert' , component:InsertComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
