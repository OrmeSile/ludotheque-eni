import { Pipe, PipeTransform } from '@angular/core';
import {TimestampDTO} from '../../../proto/generated/ludotheque';

@Pipe({
  name: 'zonedTimestamp'
})
export class ZonedTimestampPipe implements PipeTransform {

  transform(value?: TimestampDTO ): string {
    if(value === undefined)
      return '';
    return new Date(value.timestamp).toLocaleString();
  }

}
