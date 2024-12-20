import { Routes } from '@angular/router';
import {UserListComponent} from './core/user-list/user-list.component';
import {AppComponent} from './app.component';
import {LoginButtonComponent} from './core/login-button/login-button.component';
import {GamePageComponent} from './pages/game-page/game-page.component';
import {GameListComponent} from './pages/game-list/game-list.component';

export const routes: Routes = [
  { path: 'users', component: UserListComponent },
  {path: 'game/:id', component: GamePageComponent},
  {path: '', component: GameListComponent},
  { path: '**', component:AppComponent},
];
