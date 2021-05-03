package br.com.mp.arraylist;

public class Aula implements Comparable<Aula> {

    private String titulo;
    private int tempo;

    public Aula(String titulo, int tempo) {
        this.titulo = titulo;
        this.tempo = tempo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getTempo() {
        return tempo;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "titulo='" + titulo + '\'' +
                ", tempo=" + tempo +
                '}';
    }

    @Override
    public int compareTo(Aula outraAula) {
        return this.titulo.compareTo(outraAula.titulo);
    }

    // É um tipo de implementação, mas é melhor delegar
//    @Override
//    public int compareTo(Aula outraAula) {
//        if (this.titulo.compareTo(outraAula.titulo) < 0) {
//            return -1;
//        }
//        return 0;
//    }
}
