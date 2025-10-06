package system;

import java.util.*;

public class Groups {
	private List<Set<Integer>> _groups;
	private Graph _graph;
	private List<Arista> _mst;
	
	//constructor 2 grupos
	public Groups(List<Arista> mst, int cantidadUsuarios, Graph graph) {
		ClusterDivider processor = new ClusterDivider();
		_graph = graph;
		_mst = mst;
		_groups = processor.dividirEnDosGrupos(mst, cantidadUsuarios);
	}
	
	public List<Integer[]> averages() {
		List<Integer[]> average = new ArrayList<Integer[]>();
		for (int i = 0; i < _groups.size(); i++) {
			average.add(new Integer[5]);
			for (Integer[] list : average) {
				for (int x = 0; x < list.length; x++) {
					list[x] = 0;
				}
			}
			
		}
		int groupNumber = 0;
		for (Integer[] list : average) {
			int quantity = 0;
			for (Integer id : _groups.get(groupNumber)) {
				quantity++;
				int[] interests = _graph.getUser(id).getAllInterests();
				for (int n = 0; n < interests.length; n++) {
					list[n] = list[n] + interests[n];
				}
			}
			for (int n = 0; n < list.length; n++) {
				list[n] = list[n]/quantity;
			}
			list[4] = averageWeightForGroup(groupNumber);
			groupNumber ++;
		}
		return average;
	}
	
	private int averageWeightForGroup(int group) {
        Set<Integer> visitados = new HashSet<>();
        int weight = 0;
        
        for (Integer id : _groups.get(group)) {
        	for (Arista a : _mst)
        		if (a.contains(id) && !visitados.contains(id)) {
        			visitados.add(a.getDestino());
        			visitados.add(a.getOrigen());
        			weight = weight + a.getPeso();
        		}
        	
        }
        weight = weight/(visitados.size())-1;
        return weight;
	}
}
