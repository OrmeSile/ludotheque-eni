import {inject, Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {GameDTO, GameListPaginationDTO} from '../../../proto/generated/ludotheque';
import {map, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameListService {

  constructor() { }
  http = inject(HttpClient)

  getGamesByPageSize(page: number = 1, size: number = 50) {
    return this.http.get(`http://localhost:8081/game/?page=${page}&size=${size}`, {
      headers: new HttpHeaders(
        {'Content-Type': 'application/x-protobuf', 'Accept': 'application/x-protobuf'}
      ),
      responseType: "arraybuffer"
    }).pipe(
      map(data => {
        const decoded = GameListPaginationDTO.decode(new Uint8Array(data))
        console.log(decoded)
        return decoded
      }
      ))
  }
}
