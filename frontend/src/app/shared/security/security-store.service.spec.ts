import { TestBed } from '@angular/core/testing';

import { SecurityStoreService } from './security-store.service';

describe('SecurityStoreService', () => {
  let service: SecurityStoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SecurityStoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
