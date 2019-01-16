package solver;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
import java.util.TreeMap;
import util.ElementSet;

public class ChvatalSolver extends GreedySolver{
	
	public ChvatalSolver() {
		_name = "Chvatal";
		this.reset();
	}

	@Override
	public ElementSet nextBestSet() {
		TreeMap<Double, ElementSet> tm = new TreeMap<Double, ElementSet>();
		for (ElementSet es: _model.getElementSetIterable()) {
			tm.put(es.getCost()/es.countElementsCovered(_elementsUncovered), es);
		}
		while (tm.get(tm.firstKey()).countElementsCovered(_elementsUncovered) < 1) {
			tm.remove(tm.firstKey());				
		}
		return tm.get(tm.firstKey());
	}	

}
