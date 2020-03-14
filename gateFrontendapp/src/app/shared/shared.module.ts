import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';

import { ConstantsService } from './common/constants.service';

import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { MatToolbarModule, MatIconModule, MatSidenavModule, MatListModule, MatButtonModule, MatMenuModule } from  '@angular/material';
import { MaterialModule } from '../material-module';


@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    NoopAnimationsModule,
    MaterialModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule
  ],
  declarations: [
    HeaderComponent,
    FooterComponent,
  ],
  exports: [
    HeaderComponent,
    FooterComponent,
    NoopAnimationsModule,
    MaterialModule,
    MatMenuModule
  ],
  providers: [ConstantsService]
})
export class SharedModule { }