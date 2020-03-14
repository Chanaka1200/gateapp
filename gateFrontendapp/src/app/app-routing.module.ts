import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'; 
import { CardOperationViewComponent } from './car-cheking/card-operation-views/card-operation-view.component';
import { CardDetailsViewComponent } from './card-details/card-details-view/card-details-view.component';
import { CardCashBookComponent } from './card-reports/card-cash-book/card-cash-book.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: CardDetailsViewComponent
  },
  {
    path: 'operation',
    component: CardOperationViewComponent
  },
  {
    path: 'cash-book',
    component: CardCashBookComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
