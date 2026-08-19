package br.com.sistemaacademico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.sistemaacademico.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.authorizeHttpRequests(auth -> auth

					    .requestMatchers(
					        "/swagger-ui/**",
					        "/swagger-ui.html",
					        "/v3/api-docs/**"
					    ).permitAll()

					    .requestMatchers("/actuator", "/actuator/**").permitAll()

					    // Login público
					    .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

					    // Cadastro público
					    .requestMatchers(HttpMethod.POST, "/usuarios/cadastrar").permitAll()

					    // Usuários - somente ADMIN
					    .requestMatchers("/usuarios/**").hasRole("ADMIN")

					    // Alunos
					    .requestMatchers("/alunos/**")
					        .hasAnyRole("ADMIN", "FUNCIONARIO")

					    // Cursos
					    .requestMatchers("/cursos/**")
					        .hasAnyRole("ADMIN", "FUNCIONARIO")

					    // Disciplinas
					    .requestMatchers("/disciplinas/**")
					        .hasAnyRole("ADMIN", "FUNCIONARIO")

					    // Turmas
					    .requestMatchers("/turmas/**")
					        .hasAnyRole("ADMIN", "FUNCIONARIO")

					    // Matrículas
					    .requestMatchers("/matriculas/**")
					        .hasAnyRole("ADMIN", "FUNCIONARIO")

					    .anyRequest().authenticated()
					)

				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}