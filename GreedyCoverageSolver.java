package solver;

import util.ElementSet;

// Add the set that will cover the most uncovered elements
public class GreedyCoverageSolver extends GreedySolver {
	
	public GreedyCoverageSolver() {
		_name = "Coverage";
		this.reset();
	}
	
	@Override
	public ElementSet nextBestSet() {
		int currSetCoverage = 0;
		int bestSetCoverage = 0;		
		ElementSet nextBestSet = null;	
		for (ElementSet es: _model.getElementSetIterable()) {	
			currSetCoverage = es.countElementsCovered(_elementsUncovered);
			if (currSetCoverage > bestSetCoverage) {
				bestSetCoverage = currSetCoverage;
				nextBestSet = es;
			}
		}		
		return nextBestSet;
	}

}
