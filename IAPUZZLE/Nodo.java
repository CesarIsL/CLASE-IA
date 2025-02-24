package IAPUZZLE;


class Nodo {
    String estado;
    Nodo padre;
    int movimientos;


    public Nodo(String estado, Nodo padre) {
        this.estado = estado;
        this.padre = padre;
        this.movimientos = (padre == null) ? 0 : padre.movimientos + 1;
    }

    // Constructor para el nodo raíz
    public Nodo(String estado) {
        this(estado, null);
    }
}