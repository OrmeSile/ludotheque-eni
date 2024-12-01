import {computed, inject, Injectable, signal} from '@angular/core';
import {SecurityStore} from '../../shared/security/security-store.service';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private securityStore = inject(SecurityStore);
  private admin = signal(false);
  private http = inject(HttpClient);
  isAdmin = computed(() => this.admin())

  checkIfAdmin() {
    this.admin.set(this.securityStore.hasResourceRole("delete-users"))
  }

  findAll(){
    return this.http.get<any>("http://localhost:8081/user/")
  }
}
