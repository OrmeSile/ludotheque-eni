import { Component, inject } from '@angular/core';
import {SecurityStore} from '../../shared/security/security-store.service';

@Component({
  selector: 'app-login-button',
  imports: [],
  templateUrl: './login-button.component.html',
  styleUrl: './login-button.component.css'
})

export class LoginButtonComponent {
  securityStore = inject(SecurityStore);
  user = this.securityStore.user
  loaded = this.securityStore.loaded;
  loadedUser = this.securityStore.loadedUser;
  signedIn = this.securityStore.signedIn;
  protected readonly JSON = JSON;
}
