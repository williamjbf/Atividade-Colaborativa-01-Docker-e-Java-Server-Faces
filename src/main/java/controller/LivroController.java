package controller;

import model.Editora;
import model.Livro;
import repository.EditoraRepository;
import repository.LivroRepository;
import repository.implement.EditoraImplementJDBC;
import repository.implement.LivroImplementJDBC;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class LivroController {

    private Livro livro =  new Livro();
    private String busca = "";
    private List<Livro> livroEncontrado;
    private LivroRepository repository = new LivroImplementJDBC();

    public String salvar() {
        repository.salvar(livro);
        return "listar";
    }
    public List<Livro> listar(){
        return repository.listarLivro();
    }

    @PostConstruct
    public void init(){
        this.livroEncontrado = repository.listarLivro();
    }
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }
}
