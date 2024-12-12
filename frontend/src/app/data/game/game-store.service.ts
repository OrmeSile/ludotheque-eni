import {computed, Injectable, signal} from '@angular/core';
import {GameDTO, GameListResponse} from '../../../proto/generated/ludotheque';

@Injectable({
  providedIn: 'root'
})
export class GameStoreService {

  private gamePages = signal(new Map<number, GameListResponse>());
  private games = signal(new Map<string, GameDTO>())
  currentPage = signal(0)
  totalPages = computed(() => this.gamePages().size)


  setGamePage(games: GameListResponse) {
    this.gamePages.update((previousPages) =>previousPages.set(games.page, games));
    for(const game of games.games) {
      this.games.update((previousGames) => previousGames.set(game.id, game));
    }
  }

  setGame(game: GameDTO) {
    this.games.update(previousGames => previousGames.set(game.id, game))
  }

  getAllGames(){
    return this.games;
  }

  getGames(offset: number = 0, size: number = 50 ): GameDTO[] {
    return [...this.games().values()].slice(offset, size);}

  getGameById(id: string): GameDTO | undefined {
    return this.games().get(id);
  }

  getPage(id: number): GameListResponse | undefined {
    const gamePage = this.gamePages().get(id);
    console.log(gamePage, id);
    console.log(this.gamePages());
    return gamePage;
  }
}
