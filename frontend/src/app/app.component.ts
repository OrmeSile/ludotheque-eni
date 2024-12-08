import {Component} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginButtonComponent} from './core/login-button/login-button.component';
import {GameListComponent} from './core/game-list/game-list.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoginButtonComponent, LoginButtonComponent, GameListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = "homepage"
}
