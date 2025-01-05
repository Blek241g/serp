import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DashboardComponent} from './dashboard.component';
import {WelcomeRoutingModule} from '../welcome/welcome-routing.module';
import {DashboardRoutingModule} from './dashboard-routing.module';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';



@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    MatIcon,
    MatIconButton
  ]
})
export class DashboardModule { }
