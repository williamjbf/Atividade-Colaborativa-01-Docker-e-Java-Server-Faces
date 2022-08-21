package repository.implement;

import model.Editora;
import repository.EditoraRepository;

import java.util.ArrayList;
import java.util.List;

public class EditoraImplementEmMemoria implements EditoraRepository {

    private List<Editora> editoraSalva = new ArrayList<>() ;

    public void salvar(Editora editora){
        editoraSalva.add(editora);
    }
    public List<Editora> listarEditora(){
        return editoraSalva;
    }
}
