package com.mygymroutine.exceptions;

public class NewUserWithDifferentPasswordException extends RuntimeException{
	
	public NewUserWithDifferentPasswordException() {
		super("The password does not match");
	}
}
