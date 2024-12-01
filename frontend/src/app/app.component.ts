import {Component} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginButtonComponent} from './core/login-button/login-button.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoginButtonComponent, LoginButtonComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = "homepage"
}
