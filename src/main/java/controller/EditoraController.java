package controller;

import model.Editora;
import model.Livro;
import repository.EditoraRepository;
import repository.implement.EditoraImplementJDBC;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class EditoraController implements Serializable {
    private Editora editora = new Editora();
    private String busca = "";
    private List<Editora> editorasEncontrada;
    private EditoraRepository repository = new EditoraImplementJDBC();


    public String salvar() {
        repository.salvar(editora);
        return "listar";
    }

    public List<Editora> listar(){
        return repository.listarEditora();
    }
    public String filtrar(){
        if(null==busca || "".equals(busca.trim())){
            this.editorasEncontrada = repository.listarEditora(); //lazy
        }else{
            this.editorasEncontrada = this.repository.porLocalDeOrigem(busca);
        }
        return null;
    }
    public String salvarEditora() {
        if (editora.equals(new Editora())){
            this.editora = repository.salvar(editora);
        }else {
            this.editora = repository.atualizarEditora(editora);
        }
        return "";
    }
    public String editar(Editora editora){
        this.editora = editora;
        return "";
    }
    public String excluir(Editora editora){
        Logger.getLogger(LivroController.class.getName()).log(Level.SEVERE,null,editorasEncontrada);
        repository.excluirEditora(editora.getCodigo());
        return "";
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
