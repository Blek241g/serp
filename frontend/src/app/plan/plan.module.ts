import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlansComponent } from './plans/plans.component';
import {PlanRoutingModule} from './plan-routing.module';
import { AddPlanComponent } from './components/add-plan/add-plan.component';
import {MaterialModule} from '../material';
import {SharedModule} from '../shared/shared.module';

@NgModule({
  declarations: [
    PlansComponent,
    AddPlanComponent
  ],
  imports: [
    CommonModule,
    PlanRoutingModule,
    MaterialModule,
    SharedModule
  ]
})
export class PlanModule { }
