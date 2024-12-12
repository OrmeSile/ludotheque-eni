import {Directive, ElementRef, input, InputFunction, OnDestroy, OnInit, output,} from '@angular/core';
import { debounceTime, Observable, Subscription } from 'rxjs'
@Directive({
  selector: '[appIntersection]',
  exportAs: 'appIntersection'
})
export class IntersectionDirective implements OnInit , OnDestroy{

  constructor (private element: ElementRef) {}

  root= input<HTMLElement | null>(null)
  rootMargin = input<string>("0px 0px 0px 0px")
  threshold = input(0)
  debounceTime = input(500)
  isContinuous = input(false)

  isIntersecting = output<boolean>()
  _isIntersecting = false
  intersectionSubscription = output<Subscription | undefined>()
  private subscription: Subscription | undefined

  ngOnInit() {
    this.subscription = this.createAndObserve()
  }
  ngOnDestroy() {
    this.subscription?.unsubscribe()
  }

  createAndObserve() {
    const options: IntersectionObserverInit = {
      root: this.root(),
      rootMargin: this.rootMargin(),
      threshold: this.threshold()
    }

    return new Observable<boolean>(subscriber => {
      const intersectionObserver = new IntersectionObserver(entries => {
        const { isIntersecting } = entries[0]
        subscriber.next(isIntersecting)
        isIntersecting &&
        !this.isContinuous &&
        intersectionObserver.disconnect()
      }, options)

      intersectionObserver.observe(this.element.nativeElement)

      return {
        unsubscribe () {
          intersectionObserver.disconnect()
        },
      }
    })
      .pipe(debounceTime(this.debounceTime()))
      .subscribe(status => {
        this.isIntersecting.emit(status)
        this._isIntersecting = status
      })
  }
}
