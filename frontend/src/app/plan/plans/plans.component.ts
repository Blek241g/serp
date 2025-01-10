import {Component, OnInit} from '@angular/core';
import {DataSource} from '@angular/cdk/collections';
import {Plan, User} from '../../core/models';
import {PlanService} from '../../core/services/plan.service';
import {MatDialog} from '@angular/material/dialog';
import {MatTableDataSource} from '@angular/material/table';
import {AddPlanComponent} from '../components/add-plan/add-plan.component';

@Component({
  selector: 'app-plans',
  standalone: false,

  templateUrl: './plans.component.html',
  styleUrl: './plans.component.scss'
})
export class PlansComponent implements OnInit{
  displayedColumns: string[] = ['id', 'name'];
  displayedHeaders: string[] = ['ID', 'Name'];
  dataSource!: DataSource<Plan>;

  constructor(private planService:PlanService, private dialog: MatDialog) {
  }

  ngOnInit() {
    this.planService.getPlans().subscribe({
      next: results => {
        this.dataSource = new MatTableDataSource(results)
      }
    })
  }

  opnAddPlanDialog(){
    this.dialog.open(AddPlanComponent, {})
  }
}
