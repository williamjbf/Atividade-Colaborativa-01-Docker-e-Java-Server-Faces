package controller;

import model.Livro;
import repository.LivroRepository;
import repository.implement.LivroImplementJDBC;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ViewScoped
@Named
public class LivroController implements Serializable {

    private Livro livro =  new Livro();
    private Livro livroSelecionado;
    private String busca = "";
    private List<Livro> livroEncontrado;
    private LivroRepository repository = new LivroImplementJDBC();

    public String salvar() {
        System.out.println(livro);
        if (livro.equals(new Livro())){
            this.livro = repository.salvar(livro);
        }else {
            this.livro = repository.atualizar(livro);
        }
        System.out.println(livro);
        return "";
    }
    public List<Livro> listar(){
        return repository.listarLivro();
    }

    public String editar(Livro livro){
        System.out.println(livro);
        this.livro = livro;
        return "";
    }

    public String excluir(Livro livro){
        Logger.getLogger(LivroController.class.getName()).log(Level.SEVERE,null,livroSelecionado);
        repository.excluir(livro.getId());
        return "";
    }

    public void limpar() {
        this.livro = new Livro();
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

    public List<Livro> getLivroEncontrado() {
        return livroEncontrado;
    }

    public void setLivroEncontrado(List<Livro> livroEncontrado) {
        this.livroEncontrado = livroEncontrado;
    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
    }
}
