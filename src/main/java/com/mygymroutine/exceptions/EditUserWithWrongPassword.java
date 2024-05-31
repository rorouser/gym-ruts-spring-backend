package com.mygymroutine.exceptions;

public class EditUserWithWrongPassword extends RuntimeException{
	
	public EditUserWithWrongPassword() {
		super("Las contraseñas es incorrecta");
	}
}
