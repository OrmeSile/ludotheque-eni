import { Component, input } from '@angular/core';

@Component({
  selector: 'app-data-table-header',
  imports: [],
  templateUrl: './data-table-header.component.html',
  styleUrl: './data-table-header.component.css'
})
export class DataTableHeaderComponent {
  headerData = input.required<Object>();
  protected readonly Object = Object;
}
