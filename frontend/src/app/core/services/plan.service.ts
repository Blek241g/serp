import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment.development';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Plan} from '../models'

@Injectable({
  providedIn: 'root'
})
export class PlanService {

  baseUrl = `${environment.apiUrl}/plan`;
  constructor(private http:HttpClient) { }


  getPlans():Observable<Plan[]>{
    let plans :Plan[] = [];

    for(let i=0; i<100; i++){
      plans.push({
        id: i,
        name: `plan${i}@`,
      })
    };
    return of(plans);
  }
}
