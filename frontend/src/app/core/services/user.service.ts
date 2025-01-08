import { Injectable } from '@angular/core';
import {SignUpRequest} from '../requests/sign-up.request';
import {environment} from '../../../environments/environment.development';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ApiResponse} from '../models/api-response';

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
}
