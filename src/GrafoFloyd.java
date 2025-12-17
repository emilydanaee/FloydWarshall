import java.util.*;

public class GrafoFloyd {

    private static final int INF = 1000000000;

    private final List<String> vertices = new ArrayList<>();
    private final Map<String, Integer> indice = new HashMap<>();

    // Matriz de distancias.
    private int[][] dist;

    // Matriz de reconstrucción de caminos.
    private int[][] next;

    public void agregarVertice(String label) {
        if (indice.containsKey(label)) return;
        indice.put(label, vertices.size());
        vertices.add(label);
    }

    public void inicializarMatrices() {
        int totalVertices = vertices.size();

        dist = new int[totalVertices][totalVertices];
        next = new int[totalVertices][totalVertices];

        for (int i = 0; i < totalVertices; i++) {
            for (int j = 0; j < totalVertices; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
                // -1 indica que aún no hay camino conocido
                next[i][j] = -1;
            }
        }
    }

    public void agregarAristaNoDirigida(String a, String b, int peso) {
        int i = indice.get(a);
        int j = indice.get(b);

        dist[i][j] = Math.min(dist[i][j], peso);
        dist[j][i] = Math.min(dist[j][i], peso);

        next[i][j] = j;
        next[j][i] = i;
    }

    public void agregarAristaDirigida(String a, String b, int peso) {
        int i = indice.get(a);
        int j = indice.get(b);

        dist[i][j] = Math.min(dist[i][j], peso);

        next[i][j] = j;
    }

    public void floydWarshall() {
        int totalVertices = vertices.size();

        for (int k = 0; k < totalVertices; k++) {      // Nodo intermedio
            for (int i = 0; i < totalVertices; i++) {  // Origen
                if (dist[i][k] == INF) continue;
                for (int j = 0; j < totalVertices; j++) { // Destino
                    if (dist[k][j] == INF) continue;
                    int nuevaDistancia = dist[i][k] + dist[k][j];
                    if (nuevaDistancia < dist[i][j]) {
                        dist[i][j] = nuevaDistancia;
                        // Se actualiza el siguiente salto
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    public int getDistancia(String origen, String destino) {
        int i = indice.get(origen);
        int j = indice.get(destino);
        return dist[i][j];

    }

    public List<String> getCamino(String origen, String destino) {
        List<String> camino = new ArrayList<>();
        int i = indice.get(origen);
        int j = indice.get(destino);

        if (next[i][j] == -1) return camino;

        camino.add(origen);

        while (i != j) {
            i = next[i][j];
            camino.add(vertices.get(i));
        }

        return camino;
    }


    public void imprimirMatriz() {
        int totalVertices = vertices.size();

        System.out.print("     ");
        for (String v : vertices){
            System.out.printf("%6s", v);
        }
        System.out.println();

        for (int i = 0; i < totalVertices; i++) {
            System.out.printf("%4s ", vertices.get(i));
            for (int j = 0; j < totalVertices; j++) {
                if (dist[i][j] >= INF / 2) {
                    System.out.printf("%6s", "INF");
                } else {
                    System.out.printf("%6d", dist[i][j]);
                }
            }
            System.out.println();
        }
    }

    public void imprimirMatrizNext() {
        int totalVertices = vertices.size();

        System.out.print("     ");
        for (String v : vertices) {
            System.out.printf("%6s", v);
        }
        System.out.println();

        for (int i = 0; i < totalVertices; i++) {
            System.out.printf("%4s ", vertices.get(i));

            for (int j = 0; j < totalVertices; j++) {
                if (next[i][j] == -1) {
                    System.out.printf("%6s", "-");
                } else {
                    System.out.printf("%6s", vertices.get(next[i][j]));
                }
            }
            System.out.println();
        }
    }

}
