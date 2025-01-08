import {UserType} from '../constants/user-type';

export interface AddNewUserRequest {
  email:string;
  username:string;
  lastname:string;
  password:string;
  firstname:string;
  phoneNumber:string;
  createdBy:number;
  avatar:string;
  userType:UserType;
}
