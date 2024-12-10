import {Component, input} from '@angular/core';

@Component({
  selector: 'app-data-table-row',
  imports: [],
  templateUrl: './data-table-row.component.html',
  styleUrl: './data-table-row.component.css'
})
export class DataTableRowComponent {
  rowData = input.required<Object>()
  protected readonly Object = Object;

  extractIntrospectedValues(nestedObjectValue: unknown) {
    if(nestedObjectValue === null || typeof nestedObjectValue !== 'object') {
      console.log("not object", nestedObjectValue);
      return nestedObjectValue;
    }
    console.log(nestedObjectValue);
    return this.generateTable(nestedObjectValue);
  }

  generateTable(nestedObject: Object): string {
    let headers: string[] = []
    let values = []
    for (let [key, value] of Object.entries(nestedObject)) {
      console.warn(`in loop for ${key}:${value}`)
      if(value === null || value.length === 0)
        continue
      headers.push(key)
      if(typeof value === 'object') {
        values.push(this.generateTable(value))
      }
      else {
        values.push(value)
      }
    }
    return `<table><tr>${headers.map(header => `<th>${header}</th>`)}</tr>${values.map(value => `<td>${value}</td>`)}</table>`
  }
}
