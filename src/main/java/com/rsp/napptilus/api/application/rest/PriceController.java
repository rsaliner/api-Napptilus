package com.rsp.napptilus.api.application.rest;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rsp.napptilus.api.application.rest.response.GetPriceResponse;
import com.rsp.napptilus.api.domain.port.in.PriceInPort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/prices")
@Tag(name = "Price: Price operations", description = "All the operations related with the prices")
public class PriceController {

	@Autowired
	private PriceInPort priceInPort;

	@Operation(summary = "Get price")
	@ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = GetPriceResponse.class), mediaType = "application/json") })
	@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) })
	@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
	@GetMapping
	public ResponseEntity<GetPriceResponse> getPrice(
			@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime date,
			@Valid @Min(value = 1) @RequestParam BigInteger idProduct,
			@Valid @Min(value = 1) @RequestParam BigInteger idBrand) {

		try {
			GetPriceResponse response = GetPriceResponse.fromDomain(priceInPort.obtainPrice(date, idProduct, idBrand));

			return ResponseEntity.ok(response);
		} catch (RuntimeException exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
		}
	}

}
