import {Component, inject, input, OnInit, signal} from '@angular/core';
import {GameDTO} from '../../../proto/generated/ludotheque';
import {GameStoreService} from '../../data/game/game-store.service';
import {GameQueryService} from '../../data/game/game-query.service';
import {SecurityStore} from '../../shared/security/security-store.service';
import {ZonedTimestampPipe} from '../../shared/pipe/zoned-timestamp.pipe';

@Component({
  selector: 'app-game-page',
  imports: [
    ZonedTimestampPipe
  ],
  templateUrl: './game-page.component.html',
  styleUrl: './game-page.component.css'
})
export class GamePageComponent implements OnInit {
  id = input.required<string>();
  gameStore = inject(GameStoreService);
  game = signal<GameDTO | undefined>(undefined);
  gameService = inject(GameQueryService)
  securityStore = inject(SecurityStore);

  ngOnInit() {
    this.gameService.getGameById(this.id())
      .subscribe(game => {
          console.log("game", game)
          this.game.set(game)
      })
  }

  protected readonly JSON = JSON;
}
