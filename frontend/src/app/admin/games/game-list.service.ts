import {inject, Injectable, signal } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GameDTO, GameListPaginationDTO} from '../../../proto/generated/ludotheque';
import {map, Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameListService {

  constructor() { }
  http = inject(HttpClient)
  gamePages = signal<GameListPaginationDTO[]>([])
  private currentPage = signal(0)

  getGamesByPageSize(page: number = 1, size: number = 50) {
    if(this.gamePages()[page - 1])
      return of(this.gamePages()[page - 1]);

    return this.http.get(`http://localhost:8081/game/?page=${page}&size=${size}`, {
      headers: new HttpHeaders(
        {'Content-Type': 'application/x-protobuf', 'Accept': 'application/x-protobuf'}
      ),
      responseType: "arraybuffer"
    }).pipe(
      map(data => {
        const decoded = GameListPaginationDTO.decode(new Uint8Array(data))
        this.gamePages.update((data) => [...data, decoded])
        return decoded
      }))
  }

  getNextPage() {
    if(this.gamePages().length === 0)
      return this.getGamesByPageSize()
    if(this.gamePages().length >= this.currentPage()) {
      const page = this.gamePages()[this.currentPage()]
      this.currentPage.update(pageNumber => pageNumber + 1)
      return of(page)
    }

    const currentNextUrl = this.gamePages()[this.gamePages().length -1].next
    this.currentPage.update(pageNumber => pageNumber + 1)
    return this.http.get(currentNextUrl ?? "http://localhost:8081/game/", {
      headers: new HttpHeaders(
        {'Content-Type': 'application/x-protobuf', 'Accept': 'application/x-protobuf'}
      ),
      responseType: "arraybuffer"
    }).pipe(
      map(data => {
        const decoded = GameListPaginationDTO.decode(new Uint8Array(data))
        this.gamePages.update((data) => [...data, decoded])
        return decoded
      })
    )
  }

  getPreviousPage() {
    if(this.gamePages().length === 0)
      return this.getGamesByPageSize()

    if(this.gamePages().length >= this.currentPage()) {
      const page = this.gamePages()[this.currentPage() - 1]
      this.currentPage.update(pageNumber => pageNumber - 1)
      return of(page)
    }
    const currentNextUrl = this.gamePages()[this.gamePages().length -1].previous
    this.currentPage.update(pageNumber => pageNumber - 1)
    return this.http.get(currentNextUrl ?? "http://localhost:8081/game/", {
      headers: new HttpHeaders(
        {'Content-Type': 'application/x-protobuf', 'Accept': 'application/x-protobuf'}
      ),
      responseType: "arraybuffer"
    }).pipe(
      map(data => {
        const decoded = GameListPaginationDTO.decode(new Uint8Array(data))
        this.gamePages.update((data) => [...data, decoded])
        return decoded
      })
    )
  }
}
