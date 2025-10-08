package system;

import java.util.*;

public class MainPrueba {

	public static void main(String[] args) {
		ClusterDivider si = new ClusterDivider();
		Graph grafo = new Graph();
		grafo.addUser(new User(0, "Ana", 5, 3, 2, 1));
		grafo.addUser(new User(1, "Beto", 3, 1, 1, 1));
		grafo.addUser(new User(2, "Carla", 5, 4, 5, 5));
		grafo.addUser(new User(3, "Damián", 5, 2, 4, 5));
		grafo.addUser(new User(4, "Fek"));
		grafo.addUser(new User(5));
		grafo.addUser(new User(6));
		grafo.addUser(new User(7));
		grafo.addUser(new User(8));

		PrimSolver solver = new PrimSolver(grafo);
		List<Arista> mst = solver.getSolution();

		List<Set<Integer>> grupos = si.dividirEnDosGrupos(mst, grafo.size());
		
		List<Set<Integer>> grupos1 = si.dividirEnXGrupos(mst, grafo.size(), 3);
		
		Groups groups = new Groups(mst, grafo.size(), grafo);
 

		System.out.println("Grupo 1:");
		for (int id : grupos.get(0))
		    System.out.println("  " + grafo.getUser(id));

		System.out.println("\nGrupo 2:");
		for (int id : grupos.get(1))
		    System.out.println("  " + grafo.getUser(id));

		for (int i = 0; i < grupos1.size(); i++) {
			System.out.println("Grupoalt " + i + ":");
			for (int id : grupos1.get(i))
			    System.out.println("  " + grafo.getUser(id));
		}

	}

}
