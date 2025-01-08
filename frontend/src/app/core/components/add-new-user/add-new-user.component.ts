import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserType} from '../../constants/user-type';
import {AddNewUserRequest} from '../../requests/add-new-user.request';

@Component({
  selector: 'app-add-new-user',
  standalone: false,

  templateUrl: './add-new-user.component.html',
  styleUrl: './add-new-user.component.scss'
})
export class AddNewUserComponent implements OnInit{
  private formBuilder = inject(FormBuilder);
  addNewUserForm!: FormGroup;

  ngOnInit() {
    this.addNewUserForm = this.formBuilder.group({
      firstname: [
        null,
        Validators.required
      ],

      lastname: [
        null,
        Validators.required
      ],
      username: [
        null,
        Validators.required
      ],
      type: [
        null,
        Validators.required
      ],
      email: [
        null,
        Validators.required
      ],
      phoneNumber: [
        null,
        Validators.required
      ],
      password:[
        null,
        [Validators.required]
      ]
    });
  }

  handleSubmit() {
    console.log(this.addNewUserForm.value)
    let addUserData : AddNewUserRequest = this.addNewUserForm.value;
    console.log(addUserData);
  }

  protected readonly UserType = UserType;
}
