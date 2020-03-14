import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatDialog } from '@angular/material';
import { DatePipe } from '@angular/common';
import { CardCashBookServiceService } from '../card-cash-book-service/card-cash-book-service.service';
import { CardDataSource } from './card-data.source';
import { FormControl } from '@angular/forms';
import { tap } from 'rxjs/operators';
import { merge } from 'rxjs';

@Component({
  selector: 'app-card-cash-book',
  templateUrl: './card-cash-book.component.html',
  styleUrls: ['./card-cash-book.component.css']
})
export class CardCashBookComponent implements OnInit {

  displayedColumns: string[] = ['transactionId', 'barcode', 'checkIn', 'checkOut', 'credit', 'debit', 'date'];
  dataSource: CardDataSource;

  startDate: string;
  endDate: string;
  date;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(
    public dialog: MatDialog,
    public apiService: CardCashBookServiceService,
    private datePipe: DatePipe,
    //private cardService: CardDataSource
  ) { }

  ngOnInit() {
    this.paginator.pageSize = 5;
    this.startDate = this.datePipe.transform(new Date(), "yyyy-MM-dd");
    this.endDate = this.datePipe.transform(new Date(), "yyyy-MM-dd");
    this.date = new FormControl(new Date())
    this.dataSource = new CardDataSource(this.apiService);
    this.dataSource.loadCardData(this.startDate, this.endDate, this.paginator.pageIndex, this.paginator.pageSize, this.sort.active, this.sort.direction);
  }

  ngAfterViewInit() {
    this.dataSource.counter$
      .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
      ).subscribe();

    // when paginator event is invoked, retrieve the related data
    /*this.paginator.page
    .pipe(
       tap(() => this.dataSource.loadHotels(this.startDate, this.endDate, this.errorType, this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();*/

    merge(this.paginator.page, this.sort.sortChange)
      .pipe(
        tap(() => this.dataSource.loadCardData(this.startDate, this.endDate, this.paginator.pageIndex, this.paginator.pageSize, this.sort.active, this.sort.direction))
      )
      .subscribe();
  }

  startDateChange(event) {
    this.startDate = this.datePipe.transform(event.value, "yyyy-MM-dd");
    this.startDate = this.startDate;
    this.dataSource.loadCardData(this.startDate, this.endDate, this.paginator.pageIndex, this.paginator.pageSize, this.sort.active, this.sort.direction);
  }

  endDateChange(event) {
    this.endDate = this.datePipe.transform(event.value, "yyyy-MM-dd");
    this.endDate = this.endDate;
    this.dataSource.loadCardData(this.startDate, this.endDate, this.paginator.pageIndex, this.paginator.pageSize, this.sort.active, this.sort.direction);
  }
}
