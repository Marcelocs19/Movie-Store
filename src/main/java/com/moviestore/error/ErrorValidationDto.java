package com.moviestore.error;

public class ErrorValidationDto {

	private String field;
	private String messageError;
	
	
	public ErrorValidationDto(String field, String messageError) {
		super();
		this.field = field;
		this.messageError = messageError;
	}

	public String getField() {
		return field;
	}


	public String getMessageError() {
		return messageError;
	}
	
	
}
