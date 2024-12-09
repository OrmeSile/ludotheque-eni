import {Component, computed, input, signal, WritableSignal} from '@angular/core';
import {GameDTO} from '../../../../proto/generated/ludotheque';

@Component({
  selector: 'app-game-list-item',
  imports: [],
  templateUrl: './game-list-item.component.html',
  styleUrl: './game-list-item.component.css'
})
export class GameListItemComponent {
  game = input<GameDTO>()
  name = computed(() => this.game()?.name)
  genres = computed(() => this.game()?.genres)
}
