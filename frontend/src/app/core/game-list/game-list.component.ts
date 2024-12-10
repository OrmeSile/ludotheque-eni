import {Component, inject, signal, WritableSignal} from '@angular/core';
import {GameDTO, GameListPaginationDTO} from '../../../proto/generated/ludotheque';
import {GameListService} from '../../admin/games/game-list.service';
import {SecurityStore} from '../../shared/security/security-store.service';
import {GameListItemComponent} from './game-list-item/game-list-item.component';
import {EnterScreenService} from '../service/enter-screen-service/enter-screen.service';
import {IntersectionDirective} from '../../shared/directive/intersection.directive';

@Component({
  selector: 'app-game-list',
  imports: [
    GameListItemComponent,
    IntersectionDirective
  ],
  templateUrl: './game-list.component.html',
  styleUrl: './game-list.component.css'
})
export class GameListComponent {
  gamelist: WritableSignal<GameDTO[]> = signal([])
  gameService = inject(GameListService)
  securityStore = inject(SecurityStore)
  enterScreenService = inject(EnterScreenService)
  isVisible = this.enterScreenService.isVisible
  user = this.securityStore.user

  fetchGames(page: number = 1, size: number = 50) {
    this.gameService.getGamesByPageSize(page, size).subscribe({next: games => {
      this.gamelist.set((games as GameListPaginationDTO).games)
      }})
  }

  isIntersecting(status: boolean){
    if(status){
      this.gameService.getNextPage()
        .subscribe({next: games => {this.gamelist.update((currentGames) => [...currentGames, ...games.games])}})
    }
  }
  protected readonly JSON = JSON;
  protected readonly document = document;
}
