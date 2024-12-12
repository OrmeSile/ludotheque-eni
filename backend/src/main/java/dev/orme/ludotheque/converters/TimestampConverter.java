package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.TimestampDTO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class TimestampConverter implements DtoConvertable<ZonedDateTime, TimestampDTO> {

    @Override
    public TimestampDTO toDto(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) return null;

        return TimestampDTO.newBuilder()
                           .setTimestamp(Timestamp.valueOf(zonedDateTime.toLocalDateTime())
                                                  .toString())
                           .setOffset(zonedDateTime.getOffset()
                                                   .toString())
                           .setZoneInfo(zonedDateTime.getZone()
                                                     .getId())
                           .build();
    }

    @Override
    public ZonedDateTime fromDto(TimestampDTO timestampDto) {
        if (timestampDto == null) return null;

        return ZonedDateTime.ofLocal(Timestamp.valueOf(timestampDto.getTimestamp())
                                              .toLocalDateTime(), ZoneId.of(timestampDto.getZoneInfo()), ZoneOffset.of(timestampDto.getOffset()));
    }
}
