import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { WebcamModule } from 'ngx-webcam';
import { WebCamModule } from 'ack-angular-webcam';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material-module';
import { CardDetailsViewComponent } from './card-details/card-details-view/card-details-view.component';
import { CardOperationViewComponent } from './car-cheking/card-operation-views/card-operation-view.component';
import { SharedModule } from './shared/shared.module';
import { CardCashBookComponent } from './card-reports/card-cash-book/card-cash-book.component';
import { DatePipe } from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    CardOperationViewComponent,
    CardDetailsViewComponent,
    CardDetailsViewComponent,
    CardCashBookComponent,
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    WebcamModule,
    WebCamModule,
    FormsModule,
    SharedModule,
    BrowserAnimationsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
