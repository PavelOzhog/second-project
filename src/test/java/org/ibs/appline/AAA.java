package org.ibs.appline;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AAA {

    @ParameterizedTest
    @CsvSource({"2018/12/25,2018", "2019/02/11,2019"})
    void getYear_ShouldWorkAsExpected(
            @ConvertWith(ConverterToLocalDate.class) LocalDate date, int expected) {
        System.out.println(date);
        assertEquals(expected, date.getYear());
    }
}
