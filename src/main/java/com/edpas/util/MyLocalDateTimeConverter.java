package com.edpas.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MyLocalDateTimeConverter implements AttributeConverter<java.time.LocalDateTime, java.sql.Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		return attribute == null ? null : java.sql.Timestamp.valueOf(attribute);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		return dbData == null ? null : dbData.toLocalDateTime();
	}

	private static DateTimeFormatter getFormatter() {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
		    // date/time
		    .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
		    // offset (hh:mm - "+00:00" when it's zero)
		    .optionalStart().appendOffset("+HH:MM", "+00:00").optionalEnd()
		    // offset (hhmm - "+0000" when it's zero)
		    .optionalStart().appendOffset("+HHMM", "+0000").optionalEnd()
		    // offset (hh - "Z" when it's zero)
		    .optionalStart().appendOffset("+HH", "Z").optionalEnd()
		    // create formatter
		    .toFormatter();
		return formatter;
	}
	
	public static String converToString(LocalDateTime value) {
		return value.format(getFormatter());
	}
	
	public static LocalDateTime converToLocalDateTime(String value) {
		return LocalDateTime.parse(value, getFormatter());
	}
}
