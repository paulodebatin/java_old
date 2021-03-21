package com.crud.api.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.exception.EntidadeNaoEncontradaException;
import com.crud.service.CadastroPessoaService;
import com.crud.vendas.domain.model.Pessoa;
import com.crud.vendas.domain.repository.PessoaRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class PessoaController implements IPessoaController{

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CadastroPessoaService cadastroPessoa;
	
	
	 @Value("${security.token.timeout}")
	 private int tokenTimeout;
	 
	 // Outra forma de pegar o parâmetro do application.properties: env.getProperty("ecurity.token.timeout");
//	 @Autowired
//	 private Environment env; 
	
	

	@Override 
	public String logar(String username, String pwd) {
		return getJWTToken(username);
		
	}
	
	
	private String getJWTToken(String username) {
		var secretKey = "mySecretKey";
		var grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		var token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + tokenTimeout)) 
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return token;
	}
	
	
	@Operation(summary = "Retornar todas as pessoas")
	@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
	@GetMapping
	@Cacheable(key = "listaPessoa")
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	@Operation(summary = "Retorna uma pessoa")
	@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
	@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	@GetMapping("/{pessoaId}")
	public ResponseEntity<?> buscar(@Parameter(description = "id da pessoa a ser consultada") @PathVariable Long pessoaId) {
		
		pessoaRepository.fazOutraCoisa();
		
		var pessoa = pessoaRepository.findById(pessoaId);
		
		CacheControl cache = CacheControl.maxAge(60, TimeUnit.SECONDS); // Não funcionando
		
		if (!pessoa.isPresent()) {
			throw new EntidadeNaoEncontradaException("Pessoa não encontrada");
		}
		
		return ResponseEntity.ok().cacheControl(cache).body(pessoa.get());
		
		
	}
	
	@Operation(summary = "Cria uma pessoa")
	@ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@Valid @RequestBody Pessoa pessoa) {
		return cadastroPessoa.salvar(pessoa);
	}
	
	@Operation(summary = "Alterar/Atualizar uma pessoa")
	@ApiResponse(responseCode = "200", description = "Pessoa alterada com sucesso")
	@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	@PutMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> atualizar(@Valid @Parameter(description = "id da pessoa a ser atualizada")  @PathVariable Long pessoaId,
			@RequestBody Pessoa pessoa) {
		
		if (!pessoaRepository.existsById(pessoaId)) {
			throw new EntidadeNaoEncontradaException("Pessoa não encontrada");
		}
		
		
		pessoa.setId(pessoaId);
		pessoa = cadastroPessoa.salvar(pessoa);
		
		return ResponseEntity.ok(pessoa);
	}
	
	@DeleteMapping("/{pessoaId}")
	@Operation(summary = "Exclui uma pessoa")
	@ApiResponse(responseCode = "204", description = "Pessoa excluída com sucesso")
	@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	public ResponseEntity<Void> remover(@Parameter(description = "id da pessoa a ser excluída") @PathVariable Long pessoaId) {
		if (!pessoaRepository.existsById(pessoaId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroPessoa.excluir(pessoaId);
		
		return ResponseEntity.noContent().build();
	}
	
}
