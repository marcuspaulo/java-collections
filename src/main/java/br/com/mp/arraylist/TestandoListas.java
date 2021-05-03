package br.com.mp.arraylist;

import java.util.ArrayList;
import java.util.Collections;

public class TestandoListas {
    public static void main(String[] args) {
        String aula1 = "Conhecendo mais de listas";
        String aula2 = "Modelando a classe aula";
        String aula3 = "Trabalhando com cursos e Sets";

        ArrayList<String> aulas = new ArrayList<>();
        // Adicionando um objeto na lista
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);

        aulas.forEach(aula -> {
            System.out.print("Aulas: ");
            System.out.println(aula);
        });

        System.out.println("-----");
        //Remover aula - mas a remoção, pode ser feita pelo objeto
        aulas.remove(0);

        aulas.forEach(System.out::println);

        // Obtendo a primeira aula
        System.out.println("A primeira aula é: " + aulas.get(0));

        // Tamanho da lista
        System.out.println("Tamanho da lista: " + aulas.size());

        // Fizemos até i < aulas.size() pois size retorna o total de elementos.
        // Se acessássemos até i <= aulas.size() teríamos um problema! Uma exception do tipo IndexOutOfBoundsException
        // seria lançada! Quer ver? Vamos imprimir o size e faça o teste com o código que temos até aqui:

        aulas.add("Aula nova");
        System.out.println("\n--------\n");
        // Ordenando a lista - CollectionS - Ordena Lexográfica - ordem alfabética
        Collections.sort(aulas);
        System.out.println("Depois de utilizar o CollectionS");
        aulas.forEach(aula -> {
            System.out.println("Aula: " + aula);
        });


        // Ordenando sem utilizar o CollectionS - é necessário utilizar um Comparable

    }
}
