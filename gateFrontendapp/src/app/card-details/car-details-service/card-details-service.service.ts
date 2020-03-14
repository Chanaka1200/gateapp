import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { ConstantsService } from 'src/app/shared/common/constants.service';
import { CardDetailsModel } from '../card-details-model/card-details-model';

@Injectable({
  providedIn: 'root'
})
export class CardDetailsServiceService {

  // API path
  base_path: string;
  // api perfix
  perfix: string;

  constructor(
    private http: HttpClient,
    private _constant: ConstantsService,
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

  getCardDetails(barcodeInputValue): Observable<CardDetailsModel>{
    return this.http
      .get<CardDetailsModel>(this.base_path + "cardDetailsByBarcode?barcode=" + barcodeInputValue)
      .pipe(
        retry(0),
        catchError(this.handleError)
      )
  }

  postCardDetails(barcodeInputValue, selectStatus, selectCardType, selectCardPolicy): Observable<string>{
    var cardDetails = new CardDetailsModel(0, barcodeInputValue, null, selectStatus, selectCardType, selectCardPolicy);
    return this.http
      .post(this.base_path + "cardDetails", cardDetails, { responseType: 'text' })
      .pipe(
        retry(0),
        catchError(this.handleError)
      )
  }

  putCardDetails(barcodeId, barcodeInputValue, selectStatus, selectCardType, selectCardPolicy): Observable<string>{
    var cardDetails = new CardDetailsModel(barcodeId, barcodeInputValue, null, selectStatus, selectCardType, selectCardPolicy);
    return this.http
      .put(this.base_path + "cardDetails", cardDetails, { responseType: 'text' })
      .pipe(
        retry(0),
        catchError(this.handleError)
      )
  }

}
