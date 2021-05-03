package br.com.mp.arraylist;

public class TestaCurso {

    public static void main(String[] args) {
        Curso javaColecoes = new Curso("Dominando as coleções do Java", "Paulo Silveira");

        // Aulas e javaColecoes.getAulas - Se referenciam ao mesmo objeto
//        List<Aula> aulas = javaColecoes.getAulas();

        //aulas.add(new Aula("Trabalhando com ArrayList", 21));
//        javaColecoes.getAulas().add(new Aula("Trabalhando com ArrayList", 21));
//        System.out.println(aulas);

        javaColecoes.adiciona(new Aula("Trabalhando com ArrayList", 21));
        javaColecoes.adiciona(new Aula("Criando uma Aula", 20));
        javaColecoes.adiciona(new Aula("Modelando com colecoes", 24));

        // tentando adicionar da maneira "antiga". Podemos fazer isso? Teste:
//        javaColecoes.getAulas().add(new Aula("Trabalhando com ArrayList", 21));

        // Nesse caso, como a referência é mesma, ele imprime as aulas.
        System.out.println(javaColecoes.getAulas());

        // Comparando as referências, elas são iguais
//        System.out.println(aulas == javaColecoes.getAulas());
    }
}
