import {Component, computed, inject, input} from '@angular/core';
import {GameDTO} from '../../../../proto/generated/ludotheque';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-game-list-item',
  templateUrl: './game-list-item.component.html',
  styleUrl: './game-list-item.component.css'
})
export class GameListItemComponent {
  game = input<GameDTO>()
  name = computed(() => this.game()?.name)
  genres = computed(() => this.game()?.genres)
  gameId = computed(() => this.game()?.id)
  router = inject(Router);

  async goToGamePage(gameId?: string) {
    if(gameId) {
      window.location.origin
      void this.router.navigate([`/game/${gameId}`]);
    }
  }
}
