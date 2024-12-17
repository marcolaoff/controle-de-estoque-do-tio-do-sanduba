package com.estoque.controller;

import com.estoque.model.Produto;
import com.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        return ResponseEntity.ok(novoProduto);
    }
}

@GetMappingpublic ResponseEntity<List<Produto>> listarProdutos() {     List<Produto> produtos = produtoRepository.findAll();    return ResponseEntity.ok(produtos); }

@PostMapping("/{id}/entrada")public ResponseEntity<Produto> registrarEntrada(@PathVariable Long id, @RequestBody int quantidade) {     Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));     produto.setQuantidade(produto.getQuantidade() + quantidade);    Produto produtoAtualizado = produtoRepository.save(produto); return ResponseEntity.ok(produtoAtualizado); }


@PostMapping("/{id}/entrada")public ResponseEntity<Produto> registrarEntrada(@PathVariable Long id, @RequestBody int quantidade) {     Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));     produto.setQuantidade(produto.getQuantidade() + quantidade);    Produto produtoAtualizado = produtoRepository.save(produto); return ResponseEntity.ok(produtoAtualizado); }

@PostMapping("/{id}/saida")
public ResponseEntity<Produto> registrarSaida(@PathVariable Long id, @RequestBody int quantidade) {
    Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    if (produto.getQuantidade() < quantidade) {
        throw new RuntimeException("Quantidade insuficiente no estoque");
    }
    produto.setQuantidade(produto.getQuantidade() - quantidade);
    Produto produtoAtualizado = produtoRepository.save(produto);
    return ResponseEntity.ok(produtoAtualizado);
}