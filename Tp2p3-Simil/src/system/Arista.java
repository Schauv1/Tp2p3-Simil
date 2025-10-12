package system;

import java.util.Objects;


public class Arista implements Comparable<Arista> {
 
 private int origen, destino, peso;

 public Arista(int origen, int destino, int peso) {
     this.origen = origen;
     this.destino = destino;
     this.peso = peso;
 }


 @Override
 public int compareTo(Arista otra) {
     return Integer.compare(this.peso, otra.getPeso());
 }

 @Override
 public String toString() {
     return "(" + origen + " - " + destino + ") peso=" + peso;
 }

 @Override
 public int hashCode() {
     return Objects.hash(origen, destino, peso);
 }
 
 @Override
 public boolean equals(Object obj) {
     if (this == obj) return true;
     if (!(obj instanceof Arista)) return false;
     Arista other = (Arista) obj;
     return (this.origen == other.origen && this.destino == other.destino || this.origen == other.destino && this.destino == other.origen) 
    		 && this.peso == other.peso;
 }
//--- Getters ---
public int getOrigen() { return origen; }
public int getDestino() { return destino; }
public int getPeso() { return peso; }
 
 public boolean contains(int posicion) {
 	return posicion == origen || posicion == destino;
 }
}