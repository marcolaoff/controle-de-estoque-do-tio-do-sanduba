package com.estoque.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.estoque.model.Produto;
import com.estoque.repository.ProdutoRepository;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Lista para armazenar movimentações
    private java.util.List<Map<String, Object>> movimentacoes = new ArrayList<>();

    // Listar todos os produtos
    @GetMapping
    public java.util.List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Adicionar um novo produto
    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        movimentacoes.add(Map.of("tipo", "adicao", "nome", produto.getNome(), "quantidade", produto.getQuantidade()));
        return ResponseEntity.ok(novoProduto);
    }

    // Registrar entrada de produto
    @PostMapping("/{id}/entrada")
    public ResponseEntity<?> registrarEntrada(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
        int quantidade = payload.get("quantidade");

        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setQuantidade(produto.getQuantidade() + quantidade);
            produtoRepository.save(produto);
            movimentacoes.add(Map.of("tipo", "entrada", "nome", produto.getNome(), "quantidade", quantidade));
            return ResponseEntity.ok("Entrada registrada com sucesso. Nova quantidade: " + produto.getQuantidade());
        } else {
            return ResponseEntity.badRequest().body("Produto não encontrado.");
        }
    }

    // Registrar saída de produto
    @PostMapping("/{id}/saida")
    public ResponseEntity<?> registrarSaida(@PathVariable Long id, @RequestBody Map<String, Integer> payload) {
        int quantidade = payload.get("quantidade");

        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            if (produto.getQuantidade() >= quantidade) {
                produto.setQuantidade(produto.getQuantidade() - quantidade);
                produtoRepository.save(produto);
                movimentacoes.add(Map.of("tipo", "saida", "nome", produto.getNome(), "quantidade", quantidade));
                return ResponseEntity.ok("Saída registrada com sucesso. Nova quantidade: " + produto.getQuantidade());
            } else {
                return ResponseEntity.badRequest().body("Quantidade insuficiente em estoque.");
            }
        } else {
            return ResponseEntity.badRequest().body("Produto não encontrado.");
        }
    }

    // Geração de relatório JSON com movimentações e quantidade final
    @GetMapping("/relatorio/movimentacao")
    public ResponseEntity<?> gerarRelatorioMovimentacao() {
        java.util.List<Map<String, Object>> relatorio = new ArrayList<>();

        java.util.List<Produto> produtos = produtoRepository.findAll();
        for (Produto produto : produtos) {
            int totalEntrada = movimentacoes.stream()
                    .filter(mov -> mov.get("tipo").equals("entrada") && mov.get("nome").equals(produto.getNome()))
                    .mapToInt(mov -> (int) mov.get("quantidade"))
                    .sum();

            int totalSaida = movimentacoes.stream()
                    .filter(mov -> mov.get("tipo").equals("saida") && mov.get("nome").equals(produto.getNome()))
                    .mapToInt(mov -> (int) mov.get("quantidade"))
                    .sum();

            Map<String, Object> item = Map.of(
                "id", produto.getId(),
                "nome", produto.getNome(),
                "quantidadeEntrada", totalEntrada,
                "quantidadeSaida", totalSaida,
                "quantidadeFinal", produto.getQuantidade()
            );
            relatorio.add(item);
        }

        return ResponseEntity.ok(relatorio);
    }

    // Geração de relatório em PDF com movimentações e quantidade final
    @GetMapping("/relatorio/pdf")
    public void gerarRelatorioPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_movimentacoes.pdf");

        java.util.List<Produto> produtos = produtoRepository.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Título estilizado
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph title = new Paragraph("Relatório de Movimentações\n\n", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Tabela com quantidade final dos produtos
        PdfPTable table = new PdfPTable(5); // ID, Nome, Entrada, Saída, Quantidade Final
        table.setWidthPercentage(100);

        table.addCell("ID");
        table.addCell("Nome");
        table.addCell("Quantidade Entrada");
        table.addCell("Quantidade Saída");
        table.addCell("Quantidade Final");

        for (Produto produto : produtos) {
            int totalEntrada = movimentacoes.stream()
                    .filter(mov -> mov.get("tipo").equals("entrada") && mov.get("nome").equals(produto.getNome()))
                    .mapToInt(mov -> (int) mov.get("quantidade"))
                    .sum();

            int totalSaida = movimentacoes.stream()
                    .filter(mov -> mov.get("tipo").equals("saida") && mov.get("nome").equals(produto.getNome()))
                    .mapToInt(mov -> (int) mov.get("quantidade"))
                    .sum();

            table.addCell(String.valueOf(produto.getId()));
            table.addCell(produto.getNome());
            table.addCell(String.valueOf(totalEntrada));
            table.addCell(String.valueOf(totalSaida));
            table.addCell(String.valueOf(produto.getQuantidade()));
        }

        document.add(table);
        document.close();
    }
}
