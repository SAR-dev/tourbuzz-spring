package com.backend.api.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ValidationService {
    public boolean areDatesNotUnique(List<LocalDate> tourLogsDates) {
        Set<LocalDate> dateSet = new HashSet<>();

        for (LocalDate date : tourLogsDates) {
            if (!dateSet.add(date)) {
                return true;
            }
        }
        return false;
    }

    public boolean areDatesNotInRange(List<LocalDate> tourLogsDates, LocalDate startDate, LocalDate endDate) {
        for (LocalDate date : tourLogsDates) {
            if (date.isBefore(startDate) || date.isAfter(endDate)) {
                return true;
            }
        }
        return false;
    }
}
