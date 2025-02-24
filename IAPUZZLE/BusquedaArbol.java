package IAPUZZLE;

import java.util.*;

public class BusquedaArbol {
    String estadoInicial="586207134";//123456708
    String estadoFinal="123456780";

    public void buscarSolucionAnchura() {
        Queue<Nodo> queue = new LinkedList<>();
        Set<String> visitados = new HashSet<>();
        int iteraciones = 0;


        queue.add(new Nodo(estadoInicial));
        visitados.add(estadoInicial);

        while (!queue.isEmpty()) {
            Nodo currentNode = queue.poll();
            iteraciones++;
            String estadoActual = currentNode.estado;

            if (estadoFinal.equals(estadoActual)) {
                imprimirCamino(currentNode);
                System.out.println("Iteraciones BFS: " + iteraciones);
                return;
            }

            for (String sucesor : getSuccessors(estadoActual)) {
                if (!visitados.contains(sucesor)) {
                    queue.add(new Nodo(sucesor, currentNode));
                    visitados.add(sucesor);
                }
            }
        }

        System.out.println("No se encontró solución.");
    }


    public void buscarSolucionProfundidadIterativa() {
        int limite = 1;
        while (true) {
            System.out.println("Buscando con límite de profundidad: " + limite);
            boolean solucionEncontrada = buscarDFSConLimite(limite);

            // Si encontramos la solución, terminamos
            if (solucionEncontrada) {
                break;
            }

            // Si no se encuentra la solución, aumentamos el límite
            limite++;
        }
    }

    // Función de Búsqueda DFS con límite de profundidad
    private boolean buscarDFSConLimite(int limite) {
        Stack<Nodo> stack = new Stack<>();
        Set<String> visitados = new HashSet<>();
        int iteraciones = 0; // Contador de iteraciones

        stack.push(new Nodo(estadoInicial));  // Agregar el nodo inicial al stack

        while (!stack.isEmpty()) {
            Nodo currentNode = stack.pop();
            iteraciones++;
            String estadoActual = currentNode.estado;

            // Si encontramos la solución, imprimimos el camino y salimos
            if (estadoFinal.equals(estadoActual)) {
                imprimirCamino(currentNode);
                //System.out.println("Solución encontrada en " + currentNode.movimientos + " movimientos.");
                System.out.println("Iteraciones DFS con límite " + limite + ": " + iteraciones);
                return true;  // Se encontró la solución
            }

            // Evitar ciclos y no explorar más allá del límite de profundidad
            if (!visitados.contains(estadoActual) && currentNode.movimientos < limite) {
                visitados.add(estadoActual);  // Marcamos el estado como visitado
                List<String> sucesores = getSuccessors(estadoActual);  // Obtener sucesores

                // Agregar los sucesores al stack
                for (String sucesor : sucesores) {
                    stack.push(new Nodo(sucesor, currentNode));  // Añadir sucesor al stack con el nodo actual como padre
                }
            }
        }

        // Si no encontramos la solución en este límite, retornamos false
        System.out.println("No se encontró solución con límite de profundidad: " + limite);
        return false;
    }
    // Función Costo Uniforme
    public void buscarSolucionCostoUniforme() {
        PriorityQueue<Nodo> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.movimientos));
        Set<String> visitados = new HashSet<>();
        int iteraciones = 0; // Contador de iteraciones

        queue.add(new Nodo(estadoInicial)); // Se crea el nodo raíz sin pasar null
        visitados.add(estadoInicial);

        while (!queue.isEmpty()) {
            Nodo currentNode = queue.poll();
            iteraciones++; // Incrementar contador de iteraciones
            String estadoActual = currentNode.estado;

            if (estadoFinal.equals(estadoActual)) {
                imprimirCamino(currentNode);
                System.out.println("Iteraciones Costo Uniforme: " + iteraciones); // Imprimir el número de iteraciones
                return;
            }

            for (String sucesor : getSuccessors(estadoActual)) {
                if (!visitados.contains(sucesor)) {
                    queue.add(new Nodo(sucesor, currentNode)); // Contamos los movimientos
                    visitados.add(sucesor);
                }
            }
        }

        System.out.println("No se encontró solución.");
    }

    // Genera los sucesores (movimientos posibles) de un estado dado
    private List<String> getSuccessors(String estado) {
        List<String> sucesores = new ArrayList<>();
        int emptyPos = estado.indexOf('0');

        int[] movimientos = {-1, 1, -3, 3}; // Movimientos izquierda, derecha, arriba, abajo
        for (int mov : movimientos) {
            int nuevaPos = emptyPos + mov;
            if (nuevaPos >= 0 && nuevaPos < 9 && esMovimientoValido(emptyPos, nuevaPos)) {
                char[] nuevoEstado = estado.toCharArray();
                nuevoEstado[emptyPos] = nuevoEstado[nuevaPos];
                nuevoEstado[nuevaPos] = '0';
                sucesores.add(new String(nuevoEstado));
            }
        }
        return sucesores;
    }

    // Valida los movimientos (evita movimientos inválidos, como saltos en las columnas)
    private boolean esMovimientoValido(int actual, int nuevo) {
        return !((actual % 3 == 0 && nuevo % 3 == 2) || (actual % 3 == 2 && nuevo % 3 == 0));
    }

    // Imprime el camino de estados desde el nodo raíz hasta el nodo solución
    private void imprimirCamino(Nodo nodo) {
        List<String> camino = new ArrayList<>();
        while (nodo != null) {
            camino.add(nodo.estado);
            nodo = nodo.padre;
        }
        Collections.reverse(camino);

        for (String estado : camino) {
            System.out.println(estado.substring(0, 3));
            System.out.println(estado.substring(3, 6));
            System.out.println(estado.substring(6, 9));
            System.out.println("**********");


        }
        System.out.println("Solución encontrada en " + (camino.size() - 1) + " movimientos:");
    }


}