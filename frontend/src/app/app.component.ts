import {Component, inject} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginButtonComponent} from './core/login-button/login-button.component';
import {GameListComponent} from './core/game-list/game-list.component';
import {DataTableComponent} from './core/data-table/data-table.component';
import {SecurityStore} from './shared/security/security-store.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoginButtonComponent, LoginButtonComponent, GameListComponent, DataTableComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  securityStore = inject(SecurityStore)
  title = "homepage"
  signedIn = this.securityStore.signedIn
}
