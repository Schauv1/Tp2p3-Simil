package system;

public class Arista {
    private int origen, destino, peso;

    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public int getOrigen() { return origen; }
    public int getDestino() { return destino; }
    public int getPeso() { return peso; }

    @Override
    public String toString() {
        return "(" + origen + " - " + destino + ") peso=" + peso;
    }
    
    public boolean contains(int posicion) {
    	return posicion == origen || posicion == destino;
    }
}
