package model;

import java.time.LocalDate;
import java.util.Objects;

public class Livro {

    public static final  String TABLE_NAME = "livro";
    private long id;
    private String titulo;
    private LocalDate dataDeLancamento;

    private Editora editora;

    public Livro() {

    }

    public Livro(String titulo, LocalDate dataDeLancamento, Editora editora) {
        this.titulo = titulo;
        this.dataDeLancamento = dataDeLancamento;
        this.editora = editora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public Editora getEditora() {return editora; }

    public void setEditora(Editora editora) {this.editora = editora;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id == livro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
