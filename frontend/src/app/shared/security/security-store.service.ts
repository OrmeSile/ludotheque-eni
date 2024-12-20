import {computed, inject, Injectable, PLATFORM_ID, signal} from '@angular/core';
import {KeycloakService} from './keycloak.service';
import {isPlatformServer} from '@angular/common';
import {ResourceRole} from './ResourceRole';

interface User {
  id: string | null,
  email: string | null,
  name: string | null,
  anonymous: boolean,
  bearer: string | null
}

const ANONYMOUS_USER: User = {
  id: null,
  email: null,
  name: null,
  anonymous: true,
  bearer: null
}

@Injectable({ providedIn: "root" })
export class SecurityStore {
  #keycloakService = inject(KeycloakService);

  loaded = signal(false);
  user = signal<User | undefined>(undefined);

  loadedUser = computed(() => (this.loaded() ? this.user() : undefined));
  signedIn = computed(() => this.loaded() && !this.user()?.anonymous);

  constructor() {
    this.onInit();
  }

  async onInit() {
    const isServer = isPlatformServer(inject(PLATFORM_ID));
    const keycloakService = inject(KeycloakService);
    if (isServer) {
      this.user.set(ANONYMOUS_USER);
      this.loaded.set(true);
      return;
    }

    const isLoggedIn = await keycloakService.init();
    if (isLoggedIn && keycloakService.profile) {
      const { sub, email, given_name, family_name, token } =
        keycloakService.profile;
      const user = {
        id: sub,
        email,
        name: `${given_name} ${family_name}`,
        anonymous: false,
        bearer: token,
      };
      this.user.set(user);
      this.loaded.set(true);
    } else {
      this.user.set(ANONYMOUS_USER);
      this.loaded.set(true);
    }
  }


  hasResourceRole(role: ResourceRole): boolean {
    return this.loaded() ? this.#keycloakService.keycloak.hasResourceRole(role) : false
  }

  async signIn() {
    await this.#keycloakService.login();
  }

  async signOut() {
    await this.#keycloakService.logout();
  }
}
