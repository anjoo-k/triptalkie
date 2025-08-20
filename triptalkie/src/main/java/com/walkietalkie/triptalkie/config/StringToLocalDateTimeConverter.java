package com.walkietalkie.triptalkie.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
// @Component → Spring Bean으로 등록
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
// Converer 인터페이스 구현하는 클래스이므로 Spring 데이터 바인딩 과정에서 자동으로 호출된다.

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDateTime convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        // yyyy-MM-dd → LocalDateTime at 00:00:00
        return LocalDate.parse(source, DATE_FORMATTER).atStartOfDay();
    }
}