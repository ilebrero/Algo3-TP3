package exercises3;

import java.util.ArrayList;
import java.util.TreeSet;

public class Ejercicio1 {
	private Grafo grafo;
	
	public Ejercicio1() {
		grafo = new Grafo();
	}
	
	private Grafo kosaraju(Grafo grafo){
		Grafo grafoFinal = new Grafo();
		boolean[] used = new boolean[grafo.size()];
		ArrayList<Materia> S = new ArrayList<Materia>();
		TreeSet<Materia> set = grafo.materias();
		ArrayList<Integer> stack = new ArrayList<Integer>();
		for (int i = 0; i < used.length; i++) {
			if (!used[i]){
				stack.add(dfs(grafo,used, i));
			}
		}
		while (S.size() != grafo.size()){
			Materia materia = set.pollFirst();
			
		}
		return grafoFinal;
	}
}




List<Integer>[] reverseGraph = new List[n];
for (int i = 0; i < n; i++)
  reverseGraph[i] = new ArrayList<>();
for (int i = 0; i < n; i++)
  for (int j : graph[i])
    reverseGraph[j].add(i);

List<List<Integer>> components = new ArrayList<>();
Arrays.fill(used, false);
Collections.reverse(order);

for (int u : order)
  if (!used[u]) {
    List<Integer> component = new ArrayList<>();
    dfs(reverseGraph, used, component, u);
    components.add(component);
  }

return components;
}

static void dfs(List<Integer>[] graph, boolean[] used, List<Integer> res, int u) {
used[u] = true;
for (int v : graph[u])
  if (!used[v])
    dfs(graph, used, res, v);
res.add(u);
}