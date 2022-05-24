package com.example.commonholidaysapi;

import com.example.commonholidaysapi.model.Holiday;
import com.example.commonholidaysapi.model.HolidayResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MainServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    MainService mainService;

    @Captor
    ArgumentCaptor<String> urlCaptor;

    @Test
    void shouldGetCorrectURL() {
        String countryCode1 = "UK";
        String countryCode2 = "lv";
        int year = 1999;

        Mockito.when(restTemplate.getForObject(urlCaptor.capture(), Mockito.any() )).thenReturn(new Holiday[0]);
        mainService.getCommonHolidays(countryCode1, countryCode2, year);

        List<String> urlsCalled = urlCaptor.getAllValues();
        Assertions.assertTrue(urlsCalled.contains("https://date.nager.at/api/v3/PublicHolidays/1999/GB"));
        Assertions.assertTrue(urlsCalled.contains("https://date.nager.at/api/v3/PublicHolidays/1999/LV"));
    }

    @Test
    void shouldCorrectlyCombineHolidays() {
        Holiday country1Holiday1 = new Holiday(LocalDate.of(2022, 1, 1),"Jaunais gads");
        Holiday country1Holiday2 = new Holiday(LocalDate.of(2022, 6, 24),"Jāņi");
        Holiday country1Holiday3 = new Holiday(LocalDate.of(2022, 7, 18),"Kautkādi svētki");

        Holiday country2Holiday1 = new Holiday(LocalDate.of(2022, 1, 1),"New year");
        Holiday country2Holiday2 = new Holiday(LocalDate.of(2022, 9, 1),"English holiday");

        Holiday[] allCountry1Holidays = {country1Holiday1,country1Holiday2,country1Holiday3};
        Holiday[] allCountry2Holidays = {country2Holiday1,country2Holiday2};

        List<HolidayResult> result = mainService.combineCommonHolidays(allCountry1Holidays,allCountry2Holidays);

        List<HolidayResult> expectedResult = List.of(
                new HolidayResult(LocalDate.of(2022, 1, 1), "Jaunais gads", "New year")
        );

        Assertions.assertIterableEquals(result,expectedResult);
    }
}
