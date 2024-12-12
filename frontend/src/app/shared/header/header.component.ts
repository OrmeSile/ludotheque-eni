import { Component } from '@angular/core';
import {LoginButtonComponent} from '../../core/login-button/login-button.component';

@Component({
  selector: 'app-header',
  imports: [
    LoginButtonComponent
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

}
