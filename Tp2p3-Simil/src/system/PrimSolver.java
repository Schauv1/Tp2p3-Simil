package system;

import java.util.*;

public class PrimSolver {
    private Graph grafo;
    private LinkedList<Arista> solution;

    public PrimSolver(Graph grafo) {
        if (grafo == null || grafo.size() == 0)
            throw new IllegalArgumentException("El grafo no puede ser nulo ni vacío.");
        this.grafo = grafo;
        this.solution = solve();
    }
    
    public PrimSolver() {
    	
    }
    
    public void updateGraph(Graph grafo) {
    	this.grafo = grafo;
        this.solution = solve();
    }

    private LinkedList<Arista> solve() {
        LinkedList<Arista> resultado = new LinkedList<>();
        Set<Integer> visitados = new HashSet<>();

        
        visitados.add(0);

        while (visitados.size() < grafo.size()) {
            int minPeso = Integer.MAX_VALUE;
            int desde = -1, hasta = -1;

            for (int v : visitados) {
                User u = grafo.getUser(v);

                for (User vecino : grafo.neighborsOf(v)) {
                    int idVecino = vecino.getId();
                    if (!visitados.contains(idVecino)) {
                        int peso = similCalc(u, vecino);
                        if (peso < minPeso) {
                            minPeso = peso;
                            desde = v;
                            hasta = idVecino;
                        }
                    }
                }
            }

           
            if (hasta != -1) {
                visitados.add(hasta);
                resultado.add(new Arista(desde, hasta, minPeso));
            } else {
               
                break;
            }
        }

        return resultado;
    }
    
    int similCalc(User u1, User u2) {
        return Math.abs(u1.getInteresTango() - u2.getInteresTango()) +
               Math.abs(u1.getInteresFolclore() - u2.getInteresFolclore()) +
               Math.abs(u1.getInteresRock() - u2.getInteresRock()) +
               Math.abs(u1.getInteresUrbano() - u2.getInteresUrbano());
    }

   
    public LinkedList<Arista> getSolution() {
        return solution;
    }
}


