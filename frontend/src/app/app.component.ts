import {Component, inject, signal, WritableSignal} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {KeycloakService} from './services/keycloak.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  result : WritableSignal<undefined | boolean | string>= signal(undefined)
  title = 'frontend';
  keycloakService = inject(KeycloakService)
  async tryLogin(){
    const loginResult = await this.keycloakService.tryLogin()

    this.result.set(loginResult() as boolean | string)
  }
}
