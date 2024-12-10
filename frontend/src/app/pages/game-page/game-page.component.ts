import {Component, inject, input, signal, WritableSignal} from '@angular/core';
import {GameDTO} from '../../../proto/generated/ludotheque';
import {GameListService} from '../../admin/games/game-list.service';

@Component({
  selector: 'app-game-page',
  imports: [],
  templateUrl: './game-page.component.html',
  styleUrl: './game-page.component.css'
})
export class GamePageComponent {
  id = input("");
  game: WritableSignal<GameDTO | undefined> = signal(undefined);
  gameService = inject(GameListService)

}
