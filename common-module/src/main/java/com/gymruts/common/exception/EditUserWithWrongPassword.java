package com.gymruts.common.exception;

public class EditUserWithWrongPassword extends RuntimeException{
	
	public EditUserWithWrongPassword() {
		super("Las contraseñas es incorrecta");
	}
}
