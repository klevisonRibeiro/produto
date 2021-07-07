package com.produtos.apirest.Resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.repository.ProdutoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.models.Retorno;

@RestController
@RequestMapping(value="/api")
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/produtos")
	@ApiOperation(value = "Retorna uma lista de produtos")
	public List<Produto> listarProdutos(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/produto/{id}")
	@ApiOperation(value = "Retorna um único produto")
	public Optional<Produto> obterProdutoPorId(@PathVariable(value="id") long id){
		return produtoRepository.findById(id);
	}
	
	@PostMapping("/produto")
	@ApiOperation(value = "Cadastra um novo produto")
	public Produto salvarProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@PutMapping("/produto")
	@ApiOperation(value = "Atualiza um produto")
	public Produto atualizarProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@DeleteMapping("/produto")
	@ApiOperation(value = "Deleta um produto enviado pelo body")
	public Object deletarProduto(@RequestBody Produto produto) {
		try {
			produtoRepository.delete(produto);
			Retorno sucesso = new Retorno("SUCCESS0001", "Produto deletado com sucesso!");
			return sucesso;
		} catch (Exception e) {
			Retorno err = new Retorno("ER0001", "Erro ao deletar produto!");			
			return err;
		}
	}
	
	@DeleteMapping("/produto/{id}")
	@ApiOperation(value = "Deleta um produto enviado por parâmetro")
	public Object deletarProdutoPorId(@PathVariable(value="id") long id) {
		try {
			produtoRepository.deleteById(id);
			Retorno sucesso = new Retorno("SUCCESS0001", "Produto deletado com sucesso!");
			return sucesso;
		} catch (Exception e) {
			Retorno err = new Retorno("ER0001", "Erro ao deletar produto!");			
			return err;
		}
	}
	
}
