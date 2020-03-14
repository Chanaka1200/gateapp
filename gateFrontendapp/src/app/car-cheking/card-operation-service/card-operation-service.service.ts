import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { HttpClientModule } from '@angular/common/http'; 
import { ConstantsService } from 'src/app/shared/common/constants.service';
import { CardOperrationModel } from '../card-operation-model/card-operation-model';
//import { HttpModule } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class CardOperationService {

  // API path
  base_path: string;
  // api perfix
  perfix: string;

  constructor(
    private http: HttpClient,
    private _constant: ConstantsService
  ) { 
    this.base_path = _constant.baseAppUrl;
    this.perfix = "/gate/";
    this.base_path = this.base_path + this.perfix;    
  }

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

   // Handle API errors
   handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };


  postOperationDetails(barcode, saveStatus, price, frontImage, backImage, forntOutImage, backOutImage): Observable<string>{
    console.log("price" + price);
    var operationDetails  = new CardOperrationModel(0, null, null, saveStatus, price, false, barcode, frontImage, backImage, "", "", forntOutImage, backOutImage, "", "");
    return this.http
      .post( this.base_path + "cardOperationDetails", operationDetails, { responseType: 'text' })
      .pipe(
        retry(0),
        catchError(this.handleError)
      )
  }

  getOperationDetailsByBarcode(Id): Observable<CardOperrationModel>{
    return this.http
      .get<CardOperrationModel>(this.base_path + 'cardOperationDetailsByBarcode?barcode=' + Id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  putOperationDetails(operationId, checkIn, checkOut, saveStatus, price, paid, barcode,frontImage, backImage, frontImageFilePath, backImageFilePath, forntOutImage, backOutImage, forntOutFilePath, backOutImageFilePath): Observable<string>{
    var operationDetails = new CardOperrationModel(operationId, checkIn, checkOut, saveStatus, price, paid, barcode, frontImage,backImage, frontImageFilePath, backImageFilePath, forntOutImage, backOutImage, forntOutFilePath, backOutImageFilePath);
    return this.http
      .put(this.base_path + "cardOperationDetails", operationDetails, { responseType: 'text' })
      .pipe(
        retry(0),
        catchError(this.handleError)
      )
  }

}
