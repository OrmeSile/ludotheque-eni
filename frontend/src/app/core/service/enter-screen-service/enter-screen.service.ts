import {Injectable, signal} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnterScreenService {
  isVisible = signal(false);

  observeElement(element: HTMLElement) {
    const observer = new IntersectionObserver((entries) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          this.isVisible.set(true)
        } else {
          this.isVisible.set(false)
        }
        observer.observe(element);
      });
    })
  }
}
