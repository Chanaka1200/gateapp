import { TestBed } from '@angular/core/testing';

import { CardCashBookServiceService } from './card-cash-book-service.service';

describe('CardCashBookServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CardCashBookServiceService = TestBed.get(CardCashBookServiceService);
    expect(service).toBeTruthy();
  });
});
