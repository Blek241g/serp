import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {AddNewUserComponent} from '../core/components/add-new-user/add-new-user.component';
import {UserType} from '../core/constants/user-type';
import {UserService} from '../core/services/user.service';
import {DataSource} from '@angular/cdk/collections';
import {User} from '../core/models';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-users',
  standalone: false,

  templateUrl: './users.component.html',
  styleUrl: './users.component.scss'
})
export class UsersComponent implements OnInit{
  displayedColumns: string[] = ['id', 'firstname', 'lastname', 'email'];
  dataSource!: DataSource<User>;

  constructor(private userService: UserService, private dialog: MatDialog) {
  }

  ngOnInit() {
    this.userService.getUsers().subscribe({
      next: results => {
        this.dataSource = new MatTableDataSource(results)
      }
    })
  }

  openAdduserDialog() {
    this.dialog.open(AddNewUserComponent, {})
  }
}
