import { CollectionViewer, DataSource} from "@angular/cdk/collections";
import { Observable, BehaviorSubject, of } from 'rxjs';
import { catchError, finalize } from 'rxjs/operators';
import { CardCashBookServiceService } from '../card-cash-book-service/card-cash-book-service.service';

export class CardDataSource implements DataSource<any> {

    // need to check sort and filter


    // add variables to hold the data and number of total records retrieved asynchronously
   // BehaviourSubject type is used for this purpose
   private usersSubject = new BehaviorSubject<any[]>([]);
 
   // to show the total number of records
   private countSubject = new BehaviorSubject<number>(0);
   public counter$ = this.countSubject.asObservable();
 
    constructor(private cardCashBookService: CardCashBookServiceService) {
        //super();
    }
    loadCardData(startDate, endDate, pageNumber: number, pageSize: number, sortBy, sortOrder) {
        console.log("load hotel data");
        console.log("sort by" + sortBy);
        console.log("sort order" + sortOrder);
        // use pipe operator to chain functions with Observable type
        this.cardCashBookService.getListByDateAndTypeAndPageable(startDate, endDate, pageNumber, pageSize, sortBy, sortOrder)
        
        // subscribe method to receive Observable type data when it is ready
        .subscribe((result : any) => {
            console.log("result of pagable" + JSON.stringify(result));
           this.usersSubject.next(result.hotelList);
           this.countSubject.next(result.totalElements);
          }
        );
     }
    connect(collectionViewer: CollectionViewer): Observable<any[]> {
        //return this.hotelDataService.getHotelPagabale();
        console.log("Connecting data source");
        return this.usersSubject.asObservable();
    }
    disconnect(collectionViewer: CollectionViewer): void {
        this.usersSubject.complete();
        this.countSubject.complete();
    }
}