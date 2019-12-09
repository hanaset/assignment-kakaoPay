package com.hanaset.luke.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime entityValue) {
        if (entityValue == null)
            entityValue = ZonedDateTime.now(ZoneId.of("UTC"));

        if (!entityValue.getZone().equals(ZoneId.of("UTC"))) {
            entityValue = entityValue.withZoneSameInstant(ZoneId.of("UTC"));
        }

        return Timestamp.from(entityValue.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp databaseValue) {
        if (databaseValue == null)
            return ZonedDateTime.now(ZoneId.systemDefault());

        LocalDateTime localDateTime = databaseValue.toLocalDateTime();

        return localDateTime.atZone(ZoneId.systemDefault());
    }
}
