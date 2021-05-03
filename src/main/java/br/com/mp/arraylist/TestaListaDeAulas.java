package br.com.mp.arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestaListaDeAulas {

    public static void main(String[] args) {
        Aula aula1 = new Aula("Revisitando as ArrayLists", 21);
        Aula aula2 = new Aula("Lista de objetos", 15);
        Aula aula3 = new Aula("Relacionamento de Lista ee objetos", 13);

        ArrayList<Aula> aulas = new ArrayList<>();
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);

        System.out.println(aulas); // Imprimindo dessa forma, ele mostra a referência
        // Imprime: [br.com.mp.arraylist.Aula@6ce253f1, br.com.mp.arraylist.Aula@53d8d10a, br.com.mp.arraylist.Aula@e9e54c2]
        // Referência e o Hashcode (ID único)

        // O Java chama internamente o método toString

        // ---------

        // Ordenando a lista de aulas
        System.out.println("--- Ordenando");
        Collections.sort(aulas);
        System.out.println(aulas);


        // Ordenando a lista de aulas
        System.out.println("--- Ordenando - Comparable");
        Collections.sort(aulas, Comparator.comparing(Aula::getTempo));
        System.out.println(aulas);


        // A partir do java 8, você
        aulas.sort(Comparator.comparing(Aula::getTempo));
        System.out.println(aulas);
    }
}
