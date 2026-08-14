package br.com.sistemaacademico.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TesteController {

    @GetMapping("/teste-admin")
    public String testeAdmin() {
        return "Você é ADMIN!";
    }


@GetMapping("/teste-funcionario")
public String testeFuncionario() {
    return "Você é FUNCIONARIO!";
}}