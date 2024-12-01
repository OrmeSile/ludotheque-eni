package dev.orme.ludotheque.converters;

import dev.orme.ludotheque.TimestampDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TimestampConverter implements DtoConverter<ZonedDateTime, TimestampDTO> {

    @Override
    public TimestampDTO toDto(ZonedDateTime zonedDateTime) {
        return TimestampDTO.newBuilder()
                .setSeconds(zonedDateTime.getSecond())
                .setNanos(zonedDateTime.getNano())
                .setTimezone(zonedDateTime.getZone().toString())
                .build();
    }

    @Override
    public ZonedDateTime fromDto(TimestampDTO timestampDto) {
        return ZonedDateTime.ofInstant(
                Instant.ofEpochSecond(
                        timestampDto.getSeconds(),
                        timestampDto.getNanos()),
                ZoneId.of(timestampDto.getTimezone())
        );
    }
}
