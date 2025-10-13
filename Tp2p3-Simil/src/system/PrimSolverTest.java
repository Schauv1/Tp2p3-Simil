package system;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrimSolverTest {
	
	private Graph grafo;
	private PrimSolver solver;

	@BeforeEach
	void setUp() {
		Graph grafo = new Graph();
		grafo.addUser(new User(0, "Ana", 5, 3, 2, 1));
		grafo.addUser(new User(1, "Beto", 3, 1, 1, 1));
		grafo.addUser(new User(2, "Carla", 5, 4, 5, 5));
		grafo.addUser(new User(3, "Damián", 5, 2, 4, 5));
		grafo.addUser(new User(4, "Fek", 5, 3, 2, 2));
		grafo.addUser(new User(5, "Fabian", 3, 2, 4, 1));
		grafo.addUser(new User(6, "Fernando", 1, 5 , 5, 1));
		grafo.addUser(new User(7, "Ricardo", 1, 1, 5, 5));
		grafo.addUser(new User(8, "Ivan", 5, 5, 2, 5));

		solver = new PrimSolver(grafo);
	}

	@Test
	void SolveLengthTest() {
		List<Arista> mst = solver.getSolution();
		
		int quantity = 0;
		
		for (Arista a: mst) {
			for (User u: grafo.getAllUsers()) {
				if (a.getOrigen() == u.getId())
					quantity ++;
			}
		}
		
		assertEquals(grafo.size()-1, quantity);
	}
	
	@Test
	void SolveWeightTest() {
		List<Arista> mst = solver.getSolution();
		
		boolean allLower = true;
		System.out.println(mst);
		ArrayList<Integer> visited = new ArrayList<Integer>();
		
		for (Arista a: mst) {
			for (int x = 0; x < grafo.size(); x++) {
				if (x != a.getOrigen() && x != a.getDestino() && solver.similCalc(grafo.getUser(a.getOrigen()), grafo.getUser(x)) < a.getPeso()) {
					Arista arista = new Arista(a.getOrigen(), x, solver.similCalc(grafo.getUser(a.getOrigen()), grafo.getUser(x)));
					if (!mst.contains(arista) && (!visited.contains(arista.getOrigen()) || !visited.contains(arista.getDestino()))) {
						System.out.println(arista);
						allLower = false;
					}
				}
				visited.add(x);
			}
		}
		assertTrue(allLower);
	}
	
}
