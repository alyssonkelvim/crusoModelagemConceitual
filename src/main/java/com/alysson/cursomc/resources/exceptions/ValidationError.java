package com.alysson.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {

	private static final long serialVersionUID = 1L;
	private List<FieldMessage> list = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void setErrors(List<FieldMessage> list) {
		this.list = list;
	}
	
	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message));
	}

}
