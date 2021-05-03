# Java Collections

Documentação: [https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html)

# ArrayList

ArrayList é um tipo que aceita tipos genéricos.

## Vantagens do forEach

Paralelismo, imutabilidade

```java
aulas.forEach(System.out::println);

// ou 
aulas.forEach(aula -> {
    System.out.print("Aulas: ");
    System.out.println(aula);
});

```

# Ordenar listar - CollectionS (um monte de métodos estáticos)

```java
package arraylist;

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
```

---

```
// Imprimindo dessa forma, ele mostra a referência
// Imprime: [br.com.mp.arraylist.Aula@6ce253f1, br.com.mp.arraylist.Aula@53d8d10a, br.com.mp.arraylist.Aula@e9e54c2]
// Referência e o Hashcode (ID único)

// O Java chama internamente o método toString
```

### toString

Após a implementação, ao mandar imprimir, ele chama o toString

```java
package br.com.mp.arraylist;

public class Aula {

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
}
```

# Ordenando a lista de aulas

Ao tentar chamar o Collections, ele dá um erro de compilação, por que, ele não sabe como ordenar aulas.

![Java%20Collections%2071220e06a73a48a1ad64dfa2b7eb06d1/Untitled.png](Java%20Collections%2071220e06a73a48a1ad64dfa2b7eb06d1/Untitled.png)

Certo mas por que funciona com String e não com o Objeto. É que classe String implementa o método compareTo(anotherString);

```java
Returns:
the value 0 if the argument string is equal to this string; 
a value less than 0 if this string is lexicographically less than the string 
argument; and a value greater than 0 if this string is lexicographically 
greater than the string argument.
```

O problema é que a classe aula, não possui o método compareTo(anotherString);

Certo, mas por que a classe String tem essa comparação, por que a classe String, implementa o Comparable, conforme o exemplo abaixo: 

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence,
               Constable, ConstantDesc {
```

# Implementando o Comparable na classe Aula

```java
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

// Se o Título for Nulo, pode tomar um NullPointerException (Tem que tratar)
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
```

# Ordenando de outra forma

Imagine que agora, seja necessário implementar uma ordenação pelo tempo da aula. 

Então, o Collections.sort, tem uma versão, onde você pode fazer o comparator

```java
// Ordenando a lista de aulas
System.out.println("--- Ordenando - Comparable");
Collections.sort(aulas, Comparator.comparing(Aula::getTempo));
```

## Código final

```java
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
```