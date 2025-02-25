package IAPUZZLE;


public class Aplicacion  {


        public static void main(String[] args) {

            BusquedaArbol puzzle = new BusquedaArbol();
            long startTime = System.currentTimeMillis();  // Tiempo de inicio
          puzzle.buscarSolucionAnchura();
           // puzzle.buscarSolucionCostoUniforme();
            //puzzle.buscarSolucionProfundidadIterativa();
            // puzzle.buscarProfundidadConLimite(22);
            long endTime = System.currentTimeMillis(); // Tiempo final
            System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");



        }
    }

