import {Injectable} from '@angular/core';
import Keycloak from 'keycloak-js';

export interface UserProfile {
  sub: string;
  email: string;
  given_name: string;
  family_name: string;
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  _keycloak: Keycloak | undefined;
  profile: UserProfile | undefined;

  get keycloak() {
    if (!this._keycloak) this._keycloak = new Keycloak({
      url: "http://localhost:8080", realm: "ludotheque", clientId: "ludotheque-client"
    })
    return this._keycloak
  }

  async init() {
    const authenticated = await this.keycloak.init({
      onLoad: "check-sso", silentCheckSsoRedirectUri: window.location.origin + "/assets/silent-check_sso.html"
    })
    console.log("authenticated", authenticated)

    if (!authenticated) return false

    this.profile = (await this.keycloak.loadUserInfo()) as unknown as UserProfile;
    this.profile.token = this.keycloak.token || "";
    return true;
  }

  login() {
    console.log("login", this.profile);
    return this.keycloak.login();
  }

  logout() {
    console.log("logout", this.profile);
    return this.keycloak.logout({redirectUri: "http://localhost:4200/"});
  }

}