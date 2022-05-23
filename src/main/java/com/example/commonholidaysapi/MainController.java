package com.example.commonholidaysapi;

import com.example.commonholidaysapi.model.HolidayResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/myperfectapp")
public class MainController {

    private final MainService service;

    public MainController(MainService service) {
        this.service = service;
    }

    @Operation(summary = "Get common holidays")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the common holidays",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HolidayResult.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid year", content = @Content),
            @ApiResponse(responseCode = "404", description = "Unknown country id", content = @Content)})
    @GetMapping("/{countryCode1}/{countryCode2}/{year}")
    public List<HolidayResult> getCommonHolidays(@PathVariable("countryCode1") String countryCode1,
                                                 @PathVariable("countryCode2") String countryCode2,
                                                 @PathVariable("year") Integer year) {
        checkForCorrectFormat(countryCode1, countryCode2);
        return service.getCommonHolidays(countryCode1, countryCode2, year);
    }

    void checkForCorrectFormat(String countryCode1, String countryCode2) {
        // Because API also accepts numbers for country codes, but that can cause confusion
        if (isNumeric(countryCode1) || isNumeric(countryCode2)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong input");
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
