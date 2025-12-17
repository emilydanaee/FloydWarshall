import java.util.List;

public class Principal {
    public static void main(String[] args) {

        GrafoFloyd grafo = new GrafoFloyd();

        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("C");
        grafo.agregarVertice("D");

        grafo.inicializarMatrices();

        grafo.agregarAristaDirigida("A", "B", 4);
        grafo.agregarAristaDirigida("A", "C", 3);
        grafo.agregarAristaDirigida("B", "D", 2);
        grafo.agregarAristaDirigida("C", "A", 8);
        grafo.agregarAristaDirigida("C", "D", 5);
        grafo.agregarAristaDirigida("D", "A", 3);

        System.out.println("Matriz de distancias inicial:");
        grafo.imprimirMatriz();

        System.out.println("\nMatriz next inicial:");
        grafo.imprimirMatrizNext();

        grafo.floydWarshall();

        System.out.println("\nMatriz de distancias final:");
        grafo.imprimirMatriz();

        System.out.println("\nMatriz next final:");
        grafo.imprimirMatrizNext();

        String origen = "A";
        String destino = "D";

        int distancia = grafo.getDistancia(origen, destino);

        System.out.println("\nDistancia mínima de " + origen + " a " + destino + ": " + distancia);
        System.out.println("Camino mínimo: " + grafo.getCamino(origen, destino));
    }
}

