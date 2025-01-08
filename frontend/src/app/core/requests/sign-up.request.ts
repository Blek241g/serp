import {UserType} from '../constants/user-type';

export interface SignUpRequest{
  username:string;
  lastname:string;
  firstname:string;
  password:string;
  email:string;
  phoneNumber:string;
}
