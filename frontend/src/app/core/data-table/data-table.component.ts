import {AfterViewInit, Component, computed, inject, OnInit, signal} from '@angular/core';
import {DataTableHeaderComponent} from './data-table-header/data-table-header.component';
import {DataTableRowComponent} from './data-table-row/data-table-row.component';
import {GameQueryService} from '../../data/game/game-query.service';
import {HTTP_INTERCEPTORS} from '@angular/common/http';

@Component({
  selector: 'app-data-table',
  imports: [
    DataTableHeaderComponent,
    DataTableRowComponent
  ],
  templateUrl: './data-table.component.html',
  styleUrl: './data-table.component.css'
})
export class DataTableComponent implements OnInit {
  private fetchedData = signal<Object[]>([]);
  data = computed(() => this.fetchedData())
  gameListService = inject(GameQueryService)

  ngOnInit() {
    this.gameListService
      .getGamePage()
      .subscribe(d =>{
        this.fetchedData.set(d.games)
      })
  }
}
