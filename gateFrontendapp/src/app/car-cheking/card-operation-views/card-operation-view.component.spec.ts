import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardOperationViewComponent } from './card-operation-view.component';

describe('CardOperationViewComponent', () => {
  let component: CardOperationViewComponent;
  let fixture: ComponentFixture<CardOperationViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardOperationViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardOperationViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
