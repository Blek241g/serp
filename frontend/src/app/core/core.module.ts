import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { AccountCircleMenuComponent } from './components/account-circle-menu/account-circle-menu.component';
import {MaterialModule} from '../material';
import { AddNewUserComponent } from './components/add-new-user/add-new-user.component';
import {MatInput} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [
    PageNotFoundComponent,
    AccountCircleMenuComponent,
    AddNewUserComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    MatInput,
    ReactiveFormsModule
  ]
})
export class CoreModule { }
