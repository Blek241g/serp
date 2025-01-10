import { Injectable } from '@angular/core';
import {SignUpRequest} from '../requests/sign-up.request';
import {environment} from '../../../environments/environment.development';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ApiResponse} from '../models/api-response';
import {Observable, of} from 'rxjs';
import {User} from '../models'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl = `${environment.apiUrl}/users`;
  constructor(private http:HttpClient) { }

  signup(request: SignUpRequest) {
    return this.http.post<ApiResponse>(`${this.baseUrl}/auth/signup`, request,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    })
  }

  getUsers():Observable<User[]>{
    let users :User[] = [];

    for(let i=0; i<100; i++){
      users.push({
        id: i,
        email: `user${i}@gmail.com`,
        firstname: `firstname${i}`,
        lastname: `lastname${i}`,
      })
    };
    return of(users);
  }
}
