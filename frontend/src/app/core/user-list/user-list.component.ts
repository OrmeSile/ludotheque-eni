import {Component, computed, inject, signal} from '@angular/core';
import {UserService} from '../../admin/users/user.service';
import {SecurityStore} from '../../shared/security/security-store.service';

@Component({
  selector: 'app-user-list',
  imports: [],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {
  private userService = inject(UserService);
  private securityStore = inject(SecurityStore)
  isVisible = computed(() => this.userService.isAdmin());
  userList = signal<{id: string} | undefined>(undefined);

  testAuthorizations(){
    this.userService.findAll()
      .subscribe(
        {
          next: result => this.userList = result,
        }
      );
  }

  getRoles(){
    this.userService.checkIfAdmin()
    console.log("is admin", this.userService.isAdmin());
  }
}
