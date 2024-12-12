import {Component, inject} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginButtonComponent} from './core/login-button/login-button.component';
import {SecurityStore} from './shared/security/security-store.service';
import {GameListComponent} from './pages/game-list/game-list.component';
import {HeaderComponent} from './shared/header/header.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoginButtonComponent, LoginButtonComponent, GameListComponent, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  securityStore = inject(SecurityStore)
  title = "homepage"
  signedIn = this.securityStore.signedIn
}
