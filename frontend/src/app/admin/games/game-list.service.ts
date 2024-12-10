import {inject, Injectable, signal } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GameDTO, GameListPaginationDTO} from '../../../proto/generated/ludotheque';
import {map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameListService {

  constructor() { }
  http = inject(HttpClient)
  gamePages = signal<GameListPaginationDTO[]>([])

  getGamesByPageSize(page: number = 1, size: number = 50) {
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
    const currentNextUrl = this.gamePages()[this.gamePages().length -1].next
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
    if(this.gamePages().length <= 1)
      return this.getGamesByPageSize()
    const currentNextUrl = this.gamePages()[this.gamePages().length -1].previous
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
