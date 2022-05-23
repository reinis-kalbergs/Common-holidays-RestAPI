package com.example.commonholidaysapi;

import com.example.commonholidaysapi.model.Holiday;
import com.example.commonholidaysapi.model.HolidayResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    private final RestTemplate restTemplate;

    public MainService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<HolidayResult> getCommonHolidays(String countryCode1, String countryCode2, Integer year) {
        try {
            Holiday[] response1 = restTemplate
                    .getForObject(getHolidaysUrl(countryCode1, year), Holiday[].class);
            Holiday[] response2 = restTemplate
                    .getForObject(getHolidaysUrl(countryCode2, year), Holiday[].class);
            return combineCommonHolidays(response1, response2);
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    String getHolidaysUrl(String countryCode, int year) {
        final String PUBLIC_HOLIDAYS_URL = "https://date.nager.at/api/v3/PublicHolidays/";
        countryCode = formatCountryCode(countryCode);
        return PUBLIC_HOLIDAYS_URL + year + "/" + countryCode;
    }

    private String formatCountryCode(String countryCode) {
        if (countryCode.equalsIgnoreCase("UK")) {
            return "GB";
        } else {
            return countryCode.toUpperCase();
        }
    }

    List<HolidayResult> combineCommonHolidays(Holiday[] firstCountry, Holiday[] secondCountry) {
        List<HolidayResult> results = new ArrayList<>();
        for (Holiday holiday1 : firstCountry) {
            for (Holiday holiday2 : secondCountry) {
                if (holiday1.getDate().equals(holiday2.getDate())) {
                    results.add(new HolidayResult(holiday1.getDate(), holiday1.getLocalName(), holiday2.getLocalName()));
                }
            }
        }
        return results;
    }

}
