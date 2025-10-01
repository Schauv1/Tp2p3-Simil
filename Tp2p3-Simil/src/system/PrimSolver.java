package system;

import java.util.HashSet;
import java.util.LinkedList;

public class PrimSolver {
	private Graph _grafo;
	private LinkedList<Arista>_solution;
	
	
	public PrimSolver(Graph grafo) {
		_grafo  = grafo;
		_solution = solve();
	}
	
	private LinkedList<Arista> solve() {
		LinkedList<Integer> unexplored = new LinkedList<Integer>();
		for (int i= 0; i < _grafo.size(); i++) {
			unexplored.add(i);
		}
		unexplored.removeFirst();
		for (int i= 0; i < _grafo.size(); i++) {
			int min = 60;
			int minId = 0;
			HashSet<User> neighboring = _grafo.neighborsOf(i);
			if (hasOne(neighboring,unexplored)) {
				User user = _grafo.getUser(i);
				for (User u:neighboring) {
					int similarity = similCalc(user, u);
					if (similarity < min) {
						min = similarity;
						minId = u.get_id();
					}
				}
				unexplored.remove(minId);
				_solution.add(new Arista(user.get_id(),minId,min));
			}
		}
		return null;
	}

	private int similCalc (User u1, User u2) {
		int sim_t = u1.get_interesTango() - u2.get_interesTango();
		int sim_f = u1.get_interesFolclore() - u2.get_interesFolclore();
		int sim_r = u1.get_interesRock() - u2.get_interesRock();
		int sim_u = u1.get_interesUrbano() - u2.get_interesUrbano();
		return (sim_t + sim_f + sim_r + sim_u);
	}
	
	private boolean hasOne(HashSet<User> h, LinkedList<Integer> un) {
		for (Integer i: un) {
			if (h.contains(_grafo.getUser(i)))
				return true;
		}
		return false;
	}
}
