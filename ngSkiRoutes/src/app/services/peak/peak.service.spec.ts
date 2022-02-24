import { TestBed } from '@angular/core/testing';

import { PeakService } from './peak.service';

describe('PeakService', () => {
  let service: PeakService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PeakService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
