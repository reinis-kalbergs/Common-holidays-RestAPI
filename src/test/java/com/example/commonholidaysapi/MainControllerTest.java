package com.example.commonholidaysapi;

import com.example.commonholidaysapi.MainController;
import com.example.commonholidaysapi.MainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class MainControllerTest {

    @Mock
    MainService mainService;

    @InjectMocks
    MainController mainController;

    @Test
    public void countryCodeIsNumeric() {
        String countryCode1 = "LV";
        String countryCode2 = "123";
        Integer year=2022;

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                ()->mainController.getCommonHolidays(countryCode1,countryCode2,year));

        Assertions.assertEquals(exception.getStatus(), HttpStatus.BAD_REQUEST);
    }
}
