import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConstantsService  {

  readonly baseAppUrl: string = 'http://localhost:8080';
  //readonly distLocation: string = 'MyApplication/';
  constructor(private http: HttpClient) { 
  }
}
