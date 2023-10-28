package br.com.gerenciamento.controller;

import br.com.gerenciamento.repository.ProdutoRepository;
import br.com.gerenciamento.dao.ProdutoQuery;
import br.com.gerenciamento.model.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

import javax.validation.Valid;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/inserirProdutos")
    public ModelAndView insertProdutos(Produto produto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Produto/formProduto");
        modelAndView.addObject("produto", new Produto());
        return modelAndView;
    }

    @PostMapping("InsertProdutos")
    public ModelAndView inserirProduto(@Valid Produto produto, BindingResult bindingResult) throws ClassNotFoundException, SQLException {
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("Produto/formProduto");
            modelAndView.addObject("produto");
        } else {
        modelAndView.setViewName("redirect:/produtos-ativos");
        produtoRepository.save(produto);
        ProdutoQuery prodQuery = new ProdutoQuery();
        Long produtoId = produto.getProdid();
        String tagusuario = prodQuery.UserQuery();
        prodQuery.InserirLogin(produtoId, tagusuario);
        }
        return modelAndView;
    }

    @GetMapping("produtos-adicionados")
    public ModelAndView listagemProdutos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Produto/listProdutos");
        modelAndView.addObject("produtosList", produtoRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{prodid}")
    public ModelAndView editar(@PathVariable("prodid")Long prodid) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Produto/editar");
        Produto produto = produtoRepository.getById(prodid);
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Produto produto) {
        ModelAndView modelAndView = new ModelAndView();
        produtoRepository.save(produto);
        modelAndView.setViewName("redirect:/produtos-inativos");
        return modelAndView;
    }

    @GetMapping("/remover/{prodid}")
    public String removerProduto(@PathVariable("prodid") Long prodid) {
        produtoRepository.deleteById(prodid);
        return "redirect:/produtos-inativos";
    }

    @GetMapping("filtro-produtos")
    public ModelAndView filtroProdutos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Produto/filtroProdutos");
        return modelAndView;
    }

    @GetMapping("produtos-ativos")
    public ModelAndView listaProdutosAtivos() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Produto/produtos-ativos");
        modelAndView.addObject("produtosList", produtoRepository.findByStatusAtivo());
        return modelAndView;
    }
    

    @GetMapping("produtos-inativos")
    public ModelAndView listaProdutosInativos() throws ClassNotFoundException, SQLException {
        ModelAndView modelAndView = new ModelAndView();
        ProdutoQuery prodQuery = new ProdutoQuery();
        //Usuario usertag = new Usuario();
        modelAndView.setViewName("Produto/produtos-inativos");
        modelAndView.addObject("produtosList", produtoRepository.findByLogin(prodQuery.UserQuery()));
        return modelAndView;
    }


    //@PostMapping("/pesquisar-produto")
    //public ModelAndView pesquisarProduto(@RequestParam(required = false) String nome) {
    //    ModelAndView modelAndView = new ModelAndView();
    //    List<Produto> listaProdutos;
    //    if(nome == null || nome.trim().isEmpty()) {
    //        listaProdutos = produtoRepository.findAll();
    //    } else {
    //        listaProdutos = produtoRepository.findByNomeContainingIgnoreCase(nome);
    //    }
    //    modelAndView.addObject("ListaDeProdutos", listaProdutos);
    //    modelAndView.setViewName("Produto/pesquisa-resultado");
    //    return modelAndView;
    //}

}
