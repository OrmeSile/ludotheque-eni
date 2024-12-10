import { TestBed } from '@angular/core/testing';

import { EnterScreenService } from './enter-screen.service';

describe('EnterScreenService', () => {
  let service: EnterScreenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnterScreenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
