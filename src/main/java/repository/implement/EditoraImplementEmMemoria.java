package repository.implement;

import model.Editora;
import repository.EditoraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EditoraImplementEmMemoria implements EditoraRepository {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private List<Editora> editoraSalva = new ArrayList<>() ;

    public Editora salvar(Editora editora){
        Editora editoraSalvar = new Editora(editora.getLocalDeOrigem(),editora.getNomeFantasia());
        editoraSalvar.setCodigo(ID_GENERATOR.getAndIncrement());
        editoraSalva.add(editoraSalvar);
        return editoraSalvar;
    }
    public List<Editora> listarEditora(){
        return editoraSalva;
    }

    @Override
    public Editora buscarEditora(int id) {
        return null;
    }

    @Override
    public Editora atualizarEditora(Editora editora) {
        return null;
    }

    @Override
    public void excluirEditora(int codigo) {
    }

    @Override
    public List<Editora> porLocalDeOrigem(String localDeOrigem) {
        return null;
    }

}

