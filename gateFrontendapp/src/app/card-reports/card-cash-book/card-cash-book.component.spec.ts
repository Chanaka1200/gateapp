import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardCashBookComponent } from './card-cash-book.component';

describe('CardCashBookComponent', () => {
  let component: CardCashBookComponent;
  let fixture: ComponentFixture<CardCashBookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardCashBookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardCashBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
