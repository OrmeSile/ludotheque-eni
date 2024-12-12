import { ZonedTimestampPipe } from './zoned-timestamp.pipe';

describe('ZonedTimestampPipe', () => {
  it('create an instance', () => {
    const pipe = new ZonedTimestampPipe();
    expect(pipe).toBeTruthy();
  });
});
