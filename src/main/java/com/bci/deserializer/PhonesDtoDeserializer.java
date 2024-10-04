package com.bci.deserializer;

import java.io.IOException;

import com.bci.dto.PhonesDto;
import com.bci.exception.InvalidDataException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class PhonesDtoDeserializer extends JsonDeserializer<PhonesDto> {

	@Override
	public PhonesDto deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		PhonesDto phonesDto = new PhonesDto();

		phonesDto.setNumber(node.get("number").asText());

		// Verirfica que number no este vacio, Asegura que el nodo es de tipo texto.
		// y Verifica que el texto no esté vacío ni contenga solo espacios en blanco.
		JsonNode numberCodeNode = node.get("number");
		if (numberCodeNode == null || numberCodeNode.isTextual() && numberCodeNode.asText().trim().isEmpty()) {
			throw new InvalidDataException("number es una cadena y no debe estar vacía");
		}
		phonesDto.setNumber(numberCodeNode.asText());

		// Verifica que citycode sea un número
		if (!node.get("citycode").isIntegralNumber()) {
			throw new InvalidDataException("citycode debe ser un número.");
		}
		phonesDto.setCitycode(node.get("citycode").asInt());

		// Verifica que countrycode sea un número
		JsonNode contryCodeNode = node.get("contrycode");
		if (contryCodeNode == null || !contryCodeNode.isIntegralNumber()) {
			throw new InvalidDataException("countrycode debe ser un número.");
		}
		phonesDto.setContrycode(contryCodeNode.asInt());

		return phonesDto;
	}
}