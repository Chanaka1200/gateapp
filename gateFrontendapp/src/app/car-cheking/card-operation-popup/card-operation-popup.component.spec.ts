import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardOperationPopupComponent } from './card-operation-popup.component';

describe('CardOperationPopupComponent', () => {
  let component: CardOperationPopupComponent;
  let fixture: ComponentFixture<CardOperationPopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardOperationPopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardOperationPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
