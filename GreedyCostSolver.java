package solver;

import java.util.TreeMap;
import util.ElementSet;

public class GreedyCostSolver extends GreedySolver {
	
	public GreedyCostSolver() {
		_name = "Cost";
		this.reset();
	}
	
	@Override
	public ElementSet nextBestSet() {
		TreeMap<Double, ElementSet> tm = new TreeMap<Double, ElementSet>();
		for (ElementSet es: _model.getElementSetIterable()) {
			tm.put(es.getCost(), es);
		}
		while (tm.get(tm.firstKey()).countElementsCovered(_elementsUncovered) < 1) {
			tm.remove(tm.firstKey());				
		}
		return tm.get(tm.firstKey());		
	}

}
