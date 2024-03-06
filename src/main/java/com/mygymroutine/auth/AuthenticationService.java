package com.mygymroutine.auth;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygymroutine.config.JwtService;
import com.mygymroutine.token.Token;
import com.mygymroutine.token.TokenRepository;
import com.mygymroutine.token.TokenType;
import com.mygymroutine.user.Role;
import com.mygymroutine.user.User;
import com.mygymroutine.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
	
	private final TokenRepository tokenRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		var savedUser = userRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		saveUserToken(savedUser, jwtToken);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.userId(user.getId())
				.build();
	}

	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					request.getEmail(), 
					request.getPassword()
				)
		);
		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow();	
		var jwtToken = jwtService.generateToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.userId(user.getId())
				.build();
	}

	
	
	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty()) {
			return;
		}
		validUserTokens.forEach(t->{
			t.setExpired(true);
			t.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}
	
	private void saveUserToken(User savedUser, String jwtToken) {
		var token = Token.builder()
				.user(savedUser)
				.token(jwtToken)
				.tokenType(TokenType.BEARER)
				.revoked(false)
				.expired(false)
				.build();
		tokenRepository.save(token);
	}

}
