package com.bci.dto;

import com.bci.deserializer.PhonesDtoDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonDeserialize(using = PhonesDtoDeserializer.class)
public class PhonesDto  {

	@Schema(description = "Phone numbe: ", example = "987654321")
	@NotBlank(message = "{number.not.null}")
	@Size(min = 5, message = "{number.invalid}")
	private String number;

	@Schema(description = "City code: ", example = "2", type = "integer")
	@NotBlank(message = "{citycode.not.null}")
	@Min(value = 0, message = "{citycode.invalid}")
	private Integer citycode;

	@Schema(description = "Country code: ", example = "56", type = "integer")
	@Min(value = 1, message = "{countrycode.invalid}")
	@NotBlank(message = "{countrycode.not.null}")
	private Integer contrycode;

	public PhonesDto() {
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

	public Integer getContrycode() {
		return contrycode;
	}

	public void setContrycode(Integer contrycode) {
		this.contrycode = contrycode;
	}

	public PhonesDto(
			@NotBlank(message = "{number.not.null}") @Pattern(regexp = "^\\d{7,15}$", message = "{number.invalid}") String number,
			@NotBlank(message = "{citycode.not.null}") @Min(value = 0, message = "{citycode.invalid}") Integer citycode,
			@Min(value = 1, message = "{countrycode.invalid}") @NotBlank(message = "{countrycode.not.null}") Integer contrycode) {
		super();
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
	}

	
}
