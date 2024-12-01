import { Routes } from '@angular/router';
import {UserListComponent} from './core/user-list/user-list.component';
import {AppComponent} from './app.component';
import {LoginButtonComponent} from './core/login-button/login-button.component';

export const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: '*', component:AppComponent},
];
