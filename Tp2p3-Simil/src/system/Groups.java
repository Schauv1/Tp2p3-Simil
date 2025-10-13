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
	
	public void updateGroups(List<Set<Integer>> grupos) {
		_groups = grupos;
	}
	
	public List<String[]> averagesWritten() {
		List<Integer[]> averages = averages();
		List<String[]> text = new ArrayList<String[]>();
		for (int i =0; i<averages.size(); i++) {
			text.add(new String[5]);
			for (int area = 0; area < averages.get(i).length; area++) {
				switch (area) {
				case 0:
					text.get(i)[area] = "Int. prom. tango: " + averages.get(i)[area];
					break;
				case 1:
					text.get(i)[area] = "Int. prom. folclore: " + averages.get(i)[area];
					break;
				case 2:
					text.get(i)[area] = "Int. prom. rock: " + averages.get(i)[area];
					break;
				case 3:
					text.get(i)[area] = "Int. prom. urbano: " + averages.get(i)[area];
					break;
				case 4:
					text.get(i)[area] = "Similaridad prom.: " + averages.get(i)[area];
					break;
				}
			}
		}
		return text;
	}
	
	public List<Integer[]> averages() {
		List<Integer[]> average = new ArrayList<>();
		for (int i = 0; i < _groups.size(); i++) {
			Integer[] groupAverages = new Integer[5];

			Arrays.fill(groupAverages, 0); 
			average.add(groupAverages);

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
	
	public List<Set<Integer>> get_groups() {
		return _groups;
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
