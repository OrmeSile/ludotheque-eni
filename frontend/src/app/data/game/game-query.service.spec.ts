import { TestBed } from '@angular/core/testing';

import { GameQueryService } from './game-query.service';

describe('GameQueryService', () => {
  let service: GameQueryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GameQueryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
