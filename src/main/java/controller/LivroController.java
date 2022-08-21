package controller;

import model.Livro;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LivroController {

    private Livro livro =  new Livro();


}
