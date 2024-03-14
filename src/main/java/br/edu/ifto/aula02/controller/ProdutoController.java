package br.edu.ifto.aula02.controller;

import br.edu.ifto.aula02.model.repository.ProdutoRepository;
import br.edu.ifto.aula02.model.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/*
    * @Transactional - anotação específica para trabalhar com transações,
    * seguindo a mesma ideia de transação no banco de dados.
    *
    * @Controller - criar um controller, você deve anotar uma classe
    * com @Controller ou @RestController , dependendo se deseja retornar
    * uma visualização HTML ou dados em formato JSON.
    *
    * @RequestMapping - anotação de nível de classe que define o prefixo
    * de URL para todas as rotas de um controller. @GetMapping define uma
    * rota que responde a requisições HTTP GET. @PostMapping define uma
    * rota que responde a requisições HTTP POST.
    *
    * @Autowired - fornece controle sobre onde e como a ligação entre os
    * beans deve ser realizada. Pode ser usado para em métodos setter,
    * no construtor, em uma propriedade ou métodos com nomes arbitrários
    * e/ou vários argumentos.
 */
@Transactional
@Controller
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    /**
     * @param produto necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(Produto produto){
        return "/produto/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produtos", repository.produtos());
        return new ModelAndView("/produto/list", model); //Aponta o caminho da view no projeto em /templates/produto.
    }
    @PostMapping("/save")
    public ModelAndView save(Produto produto) {
        repository.save(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL.
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/produto/list"); //Aponta o caminho da view no projeto em /templates/produto.
    }
    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", repository.produto(id));
        return new ModelAndView("/produto/form", model); // Aponta o caminho da view no projeto em /templates/produto).
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        repository.update(produto);
        return new ModelAndView("redirect:/produto/list"); //Aponta o caminho da view no projeto em /templates/produto.
    }
}
