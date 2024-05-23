package com.mygymroutine.exceptions;

public class NewUserWithDifferentPasswordException extends RuntimeException{
	
	public NewUserWithDifferentPasswordException() {
		super("Las contraseñas no coinciden");
	}
}
