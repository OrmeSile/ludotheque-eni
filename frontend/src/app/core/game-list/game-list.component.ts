import {Component, inject, signal, WritableSignal} from '@angular/core';
import {GameDTO, GameListPaginationDTO} from '../../../proto/generated/ludotheque';
import {GameListService} from '../../admin/games/game-list.service';
import {SecurityStore} from '../../shared/security/security-store.service';
import {GameListItemComponent} from './game-list-item/game-list-item.component';

@Component({
  selector: 'app-game-list',
  imports: [
    GameListItemComponent
  ],
  templateUrl: './game-list.component.html',
  styleUrl: './game-list.component.css'
})
export class GameListComponent {
  gamelist: WritableSignal<GameDTO[]> = signal([])
  gameService = inject(GameListService)
  securityStore = inject(SecurityStore);
  user = this.securityStore.user

  fetchGames(page: number = 1, size: number = 50) {
    this.gameService.getGamesByPageSize(page, size).subscribe({next: games => {
      this.gamelist.set((games as GameListPaginationDTO).games)
      }})
  }

  protected readonly JSON = JSON;
}
