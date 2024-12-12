import { IntersectionDirective } from './intersection.directive';
import {ElementRef} from '@angular/core';

describe('IntersectionDirective', () => {
  it('should create an instance', () => {
    const directive = new IntersectionDirective(new ElementRef("div"));
    expect(directive).toBeTruthy();
  });
});
