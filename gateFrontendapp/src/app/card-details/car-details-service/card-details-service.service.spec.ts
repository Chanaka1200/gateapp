import { TestBed } from '@angular/core/testing';

import { CardDetailsServiceService } from './card-details-service.service';

describe('CardDetailsServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CardDetailsServiceService = TestBed.get(CardDetailsServiceService);
    expect(service).toBeTruthy();
  });
});
