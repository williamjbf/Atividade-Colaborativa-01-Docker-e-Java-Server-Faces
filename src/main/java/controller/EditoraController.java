package controller;

import model.Editora;
import model.Livro;
import repository.EditoraRepository;
import repository.implement.EditoraImplementJDBC;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class EditoraController implements Serializable {
    private Editora editora = new Editora();
    private String busca = "";
    private List<Editora> editorasEncontrada;
    private EditoraRepository repository = new EditoraImplementJDBC();

    public List<Editora> listar(){
        return repository.listarEditora();
    }

    public String filtrar(){
        if(null==busca || "".equals(busca.trim())){
            this.editorasEncontrada = repository.listarEditora();
        }else{
            this.editorasEncontrada = this.repository.porLocalDeOrigem(busca);
        }
        return null;
    }
    public String salvarEditora() {
        System.out.println(editora);
        if (editora.equals(new Editora())){
            this.editora = repository.salvar(editora);
        }else {
            this.editora = repository.atualizarEditora(editora);
        }
        System.out.println(editora);
        return "";
    }
    public String editar(Editora editora){
        System.out.println(editora);
        this.editora = editora;
        System.out.println(this.editora);
        return "";
    }
    public String excluir(Editora editora){
        System.out.println(editora);
        repository.excluirEditora(editora.getCodigo());
        return "";
    }

    public void limpar() {
        this.editora = new Editora();
    }

    @PostConstruct
    public void init(){
        this.editorasEncontrada = repository.listarEditora();
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
