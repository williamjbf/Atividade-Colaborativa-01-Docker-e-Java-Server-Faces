package model;

import java.time.LocalDate;
import java.util.Objects;

public class Livro {

    private long id;
    private String titulo;
    private LocalDate dataDeLancamento;

    public Livro() {

    }

    public Livro(long id, String titulo, LocalDate dataDeLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.dataDeLancamento = dataDeLancamento;
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
