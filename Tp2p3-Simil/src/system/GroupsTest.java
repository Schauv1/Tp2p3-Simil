package system;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroupsTest {
	private Graph grafo;
	private PrimSolver solver;
    
    @BeforeEach
	void setUp() {
		grafo = new Graph();
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
	void test() {
		Groups groups = new Groups(solver.getSolution(), grafo.size(), grafo);
		List<Integer[]> av = groups.averages();
		for (int i = 0;  i < groups.get_groups().size(); i++) {
			int average_tango = 0;
			int average_folclore = 0;
			int average_rock = 0;
			int average_urbano = 0;
			for (Integer user : groups.get_groups().get(i)) {
					average_tango = average_tango + grafo.getUser(user).getInteresTango();
					average_folclore = average_folclore + grafo.getUser(user).getInteresFolclore();
					average_rock = average_rock + grafo.getUser(user).getInteresRock();
					average_urbano = average_urbano + grafo.getUser(user).getInteresUrbano();
			}
			average_tango = average_tango/groups.get_groups().get(i).size();
			average_folclore = average_folclore/groups.get_groups().get(i).size();
			average_rock = average_rock/groups.get_groups().get(i).size();
			average_urbano = average_urbano/groups.get_groups().get(i).size();
			assertEquals(av.get(i)[0], average_tango);
			assertEquals(av.get(i)[1], average_folclore);
			assertEquals(av.get(i)[2], average_rock);
			assertEquals(av.get(i)[3], average_urbano);
		}
	}
}
