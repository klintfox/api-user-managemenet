package com.bci.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;


public class PhonesDto implements Serializable {

	private static final long serialVersionUID = 4093730209867093750L;

	@Schema(description = "Phone numbe: ", example = "987654321")
	private String number;

	@Schema(description = "City code: ", example = "2", type = "integer")
	private int citycode;

	@Schema(description = "Country code: ", example = "56", type = "integer")
	private int countrycode;

	public PhonesDto() {
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public int getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}

	public PhonesDto(String number, int citycode, int countrycode) {
		super();
		this.number = number;
		this.citycode = citycode;
		this.countrycode = countrycode;
	}
}
