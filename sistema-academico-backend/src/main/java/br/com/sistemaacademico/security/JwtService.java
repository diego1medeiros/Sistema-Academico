package br.com.sistemaacademico.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	private final long expiration = 1000 * 60 * 60; // 1 hora

	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String gerarToken(String login, String perfil) {

		Date agora = new Date();

		Date expiracao = new Date(agora.getTime() + expiration);

		return Jwts.builder().subject(login).claim("perfil", perfil).issuedAt(agora).expiration(expiracao)
				.signWith(getKey()).compact();
	}

	public String getLogin(String token) {

		return getClaims(token).getSubject();
	}

	public String getPerfil(String token) {

		return getClaims(token).get("perfil", String.class);
	}

	public boolean validarToken(String token) {

		try {

			getClaims(token);

			return true;

		} catch (Exception e) {

			return false;
		}
	}

	private Claims getClaims(String token) {

		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
	}
}