import { TestBed } from '@angular/core/testing';

import { CardOperationService } from './card-operation-service.service';

describe('CardOperationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CardOperationService = TestBed.get(CardOperationService);
    expect(service).toBeTruthy();
  });
});
