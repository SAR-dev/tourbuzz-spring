package com.backend.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ConverterService {
    public LocalDate parseStringToDate(String dateString, String dateFormat) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            return LocalDate.parse(dateString, formatter);
        } catch (Exception e){
            throw new Exception("Check the date format of input fields");
        }
    }
}
