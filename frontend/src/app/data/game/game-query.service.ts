import {inject, Injectable, signal } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ErrorResponse, GameDTO, GameListResponse, GetGameResponse} from '../../../proto/generated/ludotheque';
import {catchError, filter, map, Observable, of, throwError} from 'rxjs';
import {GameStoreService} from './game-store.service';

@Injectable({
  providedIn: 'root'
})
export class GameQueryService {

  constructor() { }
  http = inject(HttpClient)
  gameStore = inject(GameStoreService)
  private currentPage = this.gameStore.currentPage;
  private totalPages = this.gameStore.totalPages;

  getGamePage(page: number = 1, size: number = 50) {
    return this.http.get(`http://localhost:8081/game?page=${page}&pageSize=${size}`, {
      headers: new HttpHeaders(
        {'Content-Type': 'application/x-protobuf', 'Accept': 'application/x-protobuf'}
      ),
      responseType: "arraybuffer"
    }).pipe(
      map(data => {
        const decoded = GameListResponse.decode(new Uint8Array(data))
        this.gameStore.setGamePage(decoded)
        this.currentPage.set(decoded.page)
        return decoded
      }))
  }

  getNextPage() {
    if(this.totalPages() === 0)
      return this.getGamePage()

    if(this.totalPages() > this.currentPage() + 1) {
      const page = this.gameStore.getPage(this.currentPage() + 1)
      this.currentPage.update(currentPage => currentPage + 1)
      return of(page)
    }

    const currentNextPage = this.gameStore.getPage(this.currentPage())
    console.log("currentNextPage", currentNextPage)
    const currentNextPageUrl = currentNextPage?.next
    if(!currentNextPageUrl)
      return of(this.gameStore.getPage(this.currentPage()))
    this.currentPage.update(currentPage => currentPage + 1)
    return this.http.get(currentNextPageUrl ?? "http://localhost:8081/game", {
      headers: new HttpHeaders(
        {'Content-Type': 'application/x-protobuf', 'Accept': 'application/x-protobuf'}
      ),
      responseType: "arraybuffer"
    }).pipe(
      map(data => {
        const decoded = GameListResponse.decode(new Uint8Array(data))
        this.gameStore.setGamePage(decoded)
        return decoded
      })
    )
  }

  getPreviousPage() {
    if(this.totalPages() === 0)
      return this.getGamePage()

    if(this.totalPages() >= this.currentPage()) {
      const page = this.gameStore.getPage(this.currentPage() - 1)
      this.currentPage.update(pageNumber => pageNumber - 1)
      return of(page)
    }
    const currentPreviousPageUrl = this.gameStore.getPage(this.gameStore.totalPages())?.previous
    if(!currentPreviousPageUrl)
      return of(this.gameStore.getPage(this.currentPage() - 1))
    this.currentPage.update(pageNumber => pageNumber - 1)
    return this.http.get(currentPreviousPageUrl ?? "http://localhost:8081/game", {
      headers: new HttpHeaders(
        {'Content-Type': 'application/x-protobuf', 'Accept': 'application/x-protobuf'}
      ),
      responseType: "arraybuffer"
    }).pipe(
      map(data => {
        const decoded = GameListResponse.decode(new Uint8Array(data))
        this.gameStore.setGamePage(decoded)
        return decoded
      })
    )
  }

  getGameById(id : string) : Observable<GameDTO | undefined> {
    const localGame = this.gameStore.getGameById(id)
    if(localGame)
      return of(localGame)
    return this.http.get(`http://localhost:8081/game/${id}`, {
      headers: new HttpHeaders(
        {'Content-Type': 'application/x-protobuf', 'Accept': 'application/x-protobuf'}
      ),
      responseType: "arraybuffer"})
      .pipe(
        filter(x => x !== undefined),
        map(proto => {
          const getGameResponse = GetGameResponse.decode(new Uint8Array(proto))
          this.gameStore.setGame(getGameResponse.game!)
          return getGameResponse.game
          }),
      )}
}
