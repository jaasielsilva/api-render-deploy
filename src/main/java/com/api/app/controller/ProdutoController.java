package com.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.app.model.Produto;
import com.api.app.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método GET para exibir a página de cadastro de produtos e lista de produtos
    @GetMapping("/cadastro-produtos")
    public String mostrarPaginaCadastroProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll()); // Lista de produtos
        return "cadastro_produtos";  // Exibe a página de cadastro
    }

    // Método POST para salvar um novo produto
    @PostMapping("/salvar")
    public String salvarProduto(@RequestParam String nome, 
                                 @RequestParam String descricao, 
                                 @RequestParam Double preco) {
        Produto produto = new Produto(null, nome, descricao, preco);
        produtoRepository.save(produto);  // Salva o produto no banco
        return "redirect:/produtos/cadastro-produtos";  // Redireciona para a página de cadastro
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
        model.addAttribute("produto", produto);  // Passa o produto para a página
        return "editar_produto";  // Retorna a página de edição
    }


    @PostMapping("/editar/{id}")
    public String salvarAlteracoesProduto(@PathVariable Long id, @ModelAttribute Produto produto) {
    // Atribui o ID ao produto para garantir que a atualização seja no produto correto
    produto.setId(id);  
    
    // Atualiza o produto no banco de dados
    produtoRepository.save(produto);  
    
    // Redireciona para a página de listagem de produtos
    return "redirect:/produtos/cadastro-produtos";  
    }


    // Método GET para deletar um produto
    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido"));
        produtoRepository.delete(produto);  // Deleta o produto do banco
        return "redirect:/produtos/cadastro-produtos";  // Redireciona para a lista de produtos
    }
}
