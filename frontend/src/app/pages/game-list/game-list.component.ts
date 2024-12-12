import {Component, inject} from '@angular/core';
import {GameQueryService} from '../../data/game/game-query.service';
import {SecurityStore} from '../../shared/security/security-store.service';
import {GameListItemComponent} from './game-list-item/game-list-item.component';
import {IntersectionDirective} from '../../shared/directive/intersection.directive';
import {GameStoreService} from '../../data/game/game-store.service';

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
  gameStore = inject(GameStoreService);
  games = this.gameStore.getAllGames()
  gameService = inject(GameQueryService)
  securityStore = inject(SecurityStore)
  user = this.securityStore.user

  fetchGames(page: number = 1, size: number = 50) {
    this.gameService.getGamePage(page, size).subscribe()
  }

  isIntersecting(status: boolean){
    if(status){
      this.gameService.getNextPage().subscribe()
    }
  }

  protected readonly JSON = JSON;
  protected readonly document = document;
  protected readonly Math = Math;
}
