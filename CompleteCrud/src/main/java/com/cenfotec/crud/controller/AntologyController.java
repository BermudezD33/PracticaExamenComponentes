package com.cenfotec.crud.controller;

import com.cenfotec.crud.domain.Article;
import com.cenfotec.crud.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cenfotec.crud.domain.Antology;
import com.cenfotec.crud.service.AntologyService;

import java.util.Optional;

@Controller
public class AntologyController {

    @Autowired
    ArticuloService articuloService;

    @Autowired
    AntologyService anthologyService;

    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.GET)
    public String insertarPage(Model model) {
        model.addAttribute(new Antology());
        return "insertar";
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST)
    public String insertarAction(Antology antology, BindingResult result, Model model) {
        anthologyService.save(antology);
        return "index";
    }

    @RequestMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("anthologies", anthologyService.getAll());
        return "listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") long id, Model model) {
        Antology antology = anthologyService.get(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("antology", antology);
        return "editar";

    }

    @PostMapping("/update/{id}")
    public String editarPost(@PathVariable("id") long id, Antology antology,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            antology.setId(id);
            return "editar";
        }

        anthologyService.save(antology);
        model.addAttribute("antology", anthologyService.getAll());
        return "redirect:/listar";

    }




    @GetMapping("/detalle/{id}")
    public String saveEdition(Model model, @PathVariable long id) {
        Optional<Antology> possibleData = anthologyService.get(id);
            model.addAttribute("antology",possibleData.get());
            return "detalle";

    }


    @GetMapping("/agregar/{id}")
    public String agregarGet(Model model, @PathVariable long id) {
        Optional<Antology> antology = anthologyService.get(id);
        Article newArticle = new Article();
        newArticle.setAnthology(antology.get());
        model.addAttribute("antology", antology.get());
        model.addAttribute("article", newArticle);
        return "agregar";

    }

    @PostMapping("/agregar/{id}")
    public String agregarArticulo(Article article, Model model, @PathVariable long id) {
        Optional<Antology> antology = anthologyService.get(id);
        article.setAnthology(antology.get());
        articuloService.save(article);
        return "index";

    }

}