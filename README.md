# Curso de Java Collections da Alura

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

---

# Aula 03 - Relacionamento

## Criando a classe Curso

```java
package br.com.mp.arraylist;

import java.util.ArrayList;

public class Curso {
    
    private String nome;
    private String instrutor;
    private ArrayList<Aula> aulas;
}
```

Nós podemos observar, que o ideal é trabalhar com a interface List e não com o ArrayList, para evitar ficar amarrado.

Qual é a diferença de trabalhar com List ou ArrayList?

Qual você trabalha com a lista, você encapsula, por exemplo, se você declarar como ArrayList e quando implementar o ArrayList e caso queria mudar para LinkedList, não vai compilar.

O ideal é deixar um pouco mais genérico. Você devolve uma lista, o ideal é se comprometer o menos possível com um objeto.

### ### Curso.java

```java
package br.com.mp.arraylist;

import java.util.List;

public class Curso {

    private String nome;
    private String instrutor;
    private List<Aula> aulas;

    public Curso(String nome, String instrutor) {
        this.nome = nome;
        this.instrutor = instrutor;
    }

    public String getNome() {
        return nome;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public List<Aula> getAulas() {
        return aulas;
    }
}
```

Certo, mas e se quisermos que o programador não tenha acesso aos métodos do List, para isso, podemos transformar a Lista de Aulas em **Collections.unmodifiableList(aulas);**

Queremos que ele só faça da primeira forma, usando nosso novo método adiciona. Como forçar isso? Não há como forçar, mas há como programar defensivamente, fazendo com que o método getAulas devolva uma cópia da coleção de aulas. Melhor ainda: podemos devolver essa cópia de tal forma que ela não possa ser alterada, ou seja, que ela seja não modificável, usando o método Collections.unmodifiableList

```java
public List<Aula> getAulas() {
    return Collections.unmodifiableList(aulas);
}
```

# Collection x List

List é uma Collection, mas mais especialista. A Collection é apenas isso: uma coleção de itens. Você pode adicionar coisas, remover coisas, iterar sobre coisas e consultar a quantidade de coisas que estão lá.

A Lista diciona a informação sobre uma sequência definida de coisas: você pode obter o elemento na posição n , pode adicionar um elemento na posição n , pode remover o elemento na posição n .

Em um Collection você não pode fazer isso: "o quinto elemento nesta coleção" porque não há uma ordem definida.

Existem também outras coleções especializadas, por exemplo: "Set" que adiciona a feature de que nunca conterá o mesmo elemento duas vezes (duplicatas).

Se você sabe que sempre vai ser um ArrayList, use a interface List. Se não souber ou puder receber List e Set, use a interface Collections.

## **LinkedList ou ArrayList?**

E o mistério da `LinkedList`? E se tivéssemos usado `ArrayList` na declaração do atributo `aulas` da classe `Curso`? O resultado seria exatamente o mesmo!

Então qual é a diferença? Basicamente performance. O `ArrayList`, como diz o nome, internamente usa um *array* para guardar os elementos. Ele consegue fazer operações de maneira muito eficiente, como invocar o método `get(indice)`. Se você precisa pegar o décimo quinto elemento, ele te devolverá isso bem rápido. Quando um `ArrayList` é lento? Quando você for, por exemplo, inserir um novo elemento na primeira posição. Pois a implementação vai precisar mover todos os elementos que estão no começo da lista para a próxima posição. Se há muitos elementos, isso vai demorar... Em computação, chamamos isso de **consumo de tempo linear**.

Já o `LinkedList` possui uma grande vantagem aqui. Ele utiliza a estrutura de dados chamada **lista ligada**, e é bastante rápido para adicionar e remover elementos na *cabeça* da lista, isto é, na primeira posição. Mas é lento se você precisar acessar um determinado elemento, pois a implementação precisará percorrer todos os elementos até chegar ao décimo quinto, por exemplo.

Confuso? Não tem problema. Sabe o que é interessante? Você não precisa tomar essa decisão desde já e oficializar para sempre. Como utilizamos a referência a `List`, comprometendo-nos pouco, podemos *sempre* mudar a implementação, isso é, em quem damos `new`, caso percebamos que é melhor uma ou outra lista nesse caso em particular.

Um problema do ArrayList, é que se você inserir um elemento, na primeira posição, ele vai empurrar o restante da lista.

O Problema da LinkedList é, que se você pede o elemento 1.000, ele vai buscando desde o primeiro elemento, e isso é muito ruim.

## Diferença entre ArrayList e LinkedList

E o mistério da `LinkedList`? E se tivéssemos usado `ArrayList` na declaração do atributo `aulas` da classe `Curso`? O resultado seria exatamente o mesmo!

Então qual é a diferença? Basicamente performance. A `ArrayList`, como diz o nome, internamente usa uma array para guardar os elementos. Ela consegue fazer umas operações de maneira muito eficiente, como invocar o método `get(indice)`. Se você precisa pegar o décimo quinto elemento, ele te devolve isso bem rápido. Onde uma `ArrayList` é lenta? Quando você for, por exemplo, inserir um novo elemento na primeira posição. Pois a implementação vai precisar mover todos os elementos que estão no começo da lista para a próxima posição. Se há muitos elementos, isso vai demorar... chamamos isso em computação de consumo de tempo linear.

Já a `LinkedList` possui uma grande vantagem aqui. Ela utiliza a estrutura de dados chamada lista ligada. Ela é muito rápida para adicionar e remover elementos na *cabeça* da lista, isso é, na primeira posição. Mas ela é lenta se você precisar acessar um determinado elemento, pois a implementação precisará percorrer todos os elementos até chegar ao décimo quinto, por exemplo.

Confuso? Não tem problema. Sabe o que é interessante? Você não precisa tomar essa decisão desde já e oficializar para sempre. Como utilizamos a referência a `List`, comprometendo-nos pouco, podemos *sempre* mudar a implementação, isso é, em quem damos `new`, caso percebamos que é melhor uma ou outra lista nesse caso em particular.

Ainda está confuso? Veja código abaixo que testa a inserção de 1 milhão de elementos com `ArrayList` e `LinkedList`, medindo o tempo. Além disso, estamos removendo 100 elementos, sempre tirando do início da lista. Execute e veja a diferença:

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TesteListas {

    public static void main(String[] args) {

        System.out.println("**** ArrayList vs LinkedList ***");

        List<Integer> numerosArrayList = new ArrayList<>();
        List<Integer> numerosLinkedList = new LinkedList<>();
        int quantidadeElementos = 1000000;

        long tempoArrayList  = insereElementosNo(numerosArrayList, quantidadeElementos);
        long tempoLinkedList = insereElementosNo(numerosLinkedList, quantidadeElementos);

        System.out.println("Inserção na ArrayList demorou  " + tempoArrayList);
        System.out.println("Inserção na LinkedList demorou " + tempoLinkedList);

        tempoArrayList = removePrimeirosElementos(numerosArrayList);
        tempoLinkedList = removePrimeirosElementos(numerosLinkedList);

        System.out.println("Remoção da ArrayList demorou  " + tempoArrayList);
        System.out.println("Remoção da LinkedList demorou " + tempoLinkedList);
    }

    /*
     * removendo 100 elementos sempre na primeira posição
     */
    private static long removePrimeirosElementos(List<Integer> numeros) {
        long ini = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            numeros.remove(0); //removendo sempre o primeiro elemento
        }
        long fim = System.currentTimeMillis();
        return fim-ini;
    }

    private static long insereElementosNo(List<Integer> numeros, int quantidade) {
        long ini = System.currentTimeMillis();
        for (int i = 0; i < quantidade; i++) {
            numeros.add(i);
        }
        long fim = System.currentTimeMillis();
        return fim-ini;
    }

}
```

### Resultado

```java
**** ArrayList vs LinkedList ***
Inserção na ArrayList demorou  29
Inserção na LinkedList demorou 95
Remoção da ArrayList demorou  15
Remoção da LinkedList demorou 0
```