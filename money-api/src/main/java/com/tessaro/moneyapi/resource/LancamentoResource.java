package com.tessaro.moneyapi.resource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tessaro.moneyapi.event.RecursoCriadoEvent;
import com.tessaro.moneyapi.model.Lancamento;
import com.tessaro.moneyapi.model.enumeration.LancamentoEnum;
import com.tessaro.moneyapi.service.LancamentoService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher; /* Utilizado para publicar o URI que é criado no RecursoCriadoEvent*/
	
	@GetMapping
	public List<Lancamento> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Lancamento>> findById(@PathVariable Long id) {
		Optional<Lancamento> lancamento = service.findById(id);
		return !lancamento.isEmpty() ? ResponseEntity.ok().body(lancamento) : ResponseEntity.notFound().build(); /* se a lancamento existir então ele retornarar o que foi encontrado na busca, caso contrario ele retornarar Not Found (404) * .build() irá trazer o um response sem corpo (Que é necessario vir).  */
	}

	@PostMapping
	public ResponseEntity<Lancamento> save (@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalva = service.save(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getCodigo())); /* this se refere ao objeto que criou o evento*/
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @Valid @RequestBody Lancamento lancamento){
		Lancamento lancamentoSalva = service.atualizar(id, lancamento);
		return ResponseEntity.ok(lancamentoSalva);
	}
	
	@PutMapping("/{id}/descricao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarDescricao(@PathVariable Long id, @RequestBody String variavel){
		service.atualizarPropriedadeDescricao(id, variavel);
	}
	
	@PutMapping("/{id}/dataVencimento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarAtivo(@PathVariable Long id, @RequestBody LocalDate variavel){
		service.atualizarPropriedadeDataVencimento(id, variavel);
	}
	
	@PutMapping("/{id}/dataPagamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarDataPagamento(@PathVariable Long id, @RequestBody LocalDate variavel){
		service.atualizarPropriedadeDataPagamento(id, variavel);
	}
	
	@PutMapping("/{id}/valor")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarValor(@PathVariable Long id, @RequestBody BigDecimal variavel){
		service.atualizarPropriedadeValor(id, variavel);
	}
	
	@PutMapping("/{id}/observacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarObservacao(@PathVariable Long id, @RequestBody String variavel){
		service.atualizarPropriedadeObservacao(id, variavel);
	}
	
	@PutMapping("/{id}/tipo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarTipo(@PathVariable Long id, @RequestBody LancamentoEnum variavel){
		service.atualizarPropriedadeTipo(id, variavel);
	}
	
	@PutMapping("/{id}/pessoa")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPessoa(@PathVariable Long id, @RequestBody Long variavel){
		service.atualizarPropriedadePessoa(id, variavel);
	}
	
	@PutMapping("/{id}/categoria")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarCategoria(@PathVariable Long id, @RequestBody Long variavel){
		service.atualizarPropriedadeCategoria(id, variavel);
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}
	
	@PutMapping("/{id}/{propriedade}")
	public void atualizarErrado(@PathVariable Long id, @PathVariable String propriedade) throws NotFoundException{
		throw new NotFoundException("varaivel não existe");
	}
	
}
