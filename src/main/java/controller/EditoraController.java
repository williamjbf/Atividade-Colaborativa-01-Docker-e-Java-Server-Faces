package controller;

import model.Editora;
import repository.EditoraRepository;
import repository.implement.EditoraImplementEmMemoria;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EditoraController implements Serializable {
    private Editora editora = new Editora();
    private String busca = "";
    private List<Editora> editorasEncontrada;
    private EditoraRepository repository = new EditoraImplementEmMemoria();

    public String salvar() {
        repository.salvar(editora);
        return "listar";
    }

    public List<Editora> listar(){
        return repository.listarEditora();
    }

    @PostConstruct
    public void init(){
        this.editorasEncontrada = repository.listarEditora(); //lazy
    }
    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }
}
