package system;

import java.util.*;

public class ClusterDivider {

    public List<Set<Integer>> dividirEnDosGrupos(List<Arista> mst, int cantidadUsuarios) {
        if (mst == null || mst.isEmpty()) 
            throw new IllegalArgumentException("El MST no puede estar vacío");

        Arista maxEdge = getMaxWeightEdge(mst);

        List<Arista> reducido = new ArrayList<>(mst);
        reducido.remove(maxEdge);

        Map<Integer, Set<Integer>> adj = buildAdjacencyMap(reducido, cantidadUsuarios);
        List<Set<Integer>> grupos = getConnectedComponent(adj, cantidadUsuarios);
        
        return grupos;
    }

    private List<Set<Integer>> getConnectedComponent(Map<Integer, Set<Integer>> adj, int cantidadUsuarios) {
        Set<Integer> visitados = new HashSet<>();
        List<Set<Integer>> componentes = new ArrayList<>();

        for (int i = 0; i < cantidadUsuarios; i++) {
            if (!visitados.contains(i)) {
                Set<Integer> componente = new HashSet<>();
                dfs(i, adj, visitados, componente);
                componentes.add(componente);
            }
        }
        return componentes;
	}

	private Map<Integer, Set<Integer>> buildAdjacencyMap(List<Arista> aristas, int cantidadUsuarios) {
    	Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i = 0; i < cantidadUsuarios; i++) {
            adj.put(i, new HashSet<>());
        }

        for (Arista a : aristas) {
            adj.get(a.getOrigen()).add(a.getDestino());
            adj.get(a.getDestino()).add(a.getOrigen()); 
        }
        
        return adj;
	}

	private Arista getMaxWeightEdge(List<Arista> aristas) {
    	if (aristas == null || aristas.isEmpty())
            throw new IllegalArgumentException("La lista de aristas está vacía o es nula");

        Arista max = aristas.get(0);
        for (Arista a : aristas) {
            if (a.getPeso() > max.getPeso()) {
                max = a;
            }
        }
        
        return max;
	}

	private void dfs(int nodo, Map<Integer, Set<Integer>> adj, Set<Integer> visitados, Set<Integer> grupo) {
        visitados.add(nodo);
        grupo.add(nodo);

        for (int vecino : adj.get(nodo)) {
            if (!visitados.contains(vecino)) {
                dfs(vecino, adj, visitados, grupo);
            }
        }
    }
}
