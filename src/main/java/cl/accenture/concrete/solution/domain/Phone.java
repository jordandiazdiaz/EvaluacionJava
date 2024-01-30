package cl.accenture.concrete.solution.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Phone {

	@JsonProperty(value = "number")
	private Integer number;

	@JsonProperty(value = "citycode")
	private String cityCode;

	@JsonProperty(value = "contrycode")
	private String countryCode;

}
