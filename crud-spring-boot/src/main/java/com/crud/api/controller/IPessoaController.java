package com.crud.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@CrossOrigin //(origins = "http://localhost:8089")

@RequestMapping("/pessoas")
@Tag(name = "pessoas", description = "API para manipulação de dados de pessoa")
public interface IPessoaController {
    
    @Operation(summary = "Logar uma pessoa")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    @GetMapping("/login")
    public String logar(@RequestParam("username") String username, @RequestParam("password") String pwd);
    
    
}
