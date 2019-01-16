package solver;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import model.SCPModel;
import util.ElementSet;

/** This is the main method that all solvers inherit from.  It is important
 *  to note how this solver calls nextBestSet() polymorphically!  Subclasses
 *  should *not* override solver(), they need only override nextBestSet().
 * 
 *  We'll assume models are well-defined here and do not specify Exceptions
 *  to throw.
 *
 */
public abstract class GreedySolver {
	
	protected String _name;			  // name of algorithm type
	protected double _alpha;          // minimum required coverage level in range [0,1]
	protected SCPModel _model;        // the SCP model we're currently operating on
	protected double _objFn;          // objective function value (*total cost sum* of all sets used)
	protected double _coverage;       // actual coverage fraction achieved
	protected long _compTime;         // computation time (ms)
	// newly added
	protected SortedSet<ElementSet> _solnSets; 
	protected Set<Integer> _elementsUncovered; 
	protected boolean _solved;
	
	public GreedySolver() {
		_name = "Unknown"; // Set it in subclasses' constructors
		_model = null;
		reset();
	}
	
	// Basic setter (only one needed)
	public void setMinCoverage(double alpha) { _alpha = alpha; }
	public void setModel(SCPModel model) { _model = model; }
	
	// Basic getters
	public double getMinCoverage() { return _alpha; }
	public double getObjFn() { return _objFn; }
	public double getCoverage() { return _coverage; }
	public long getCompTime() { return _compTime; }
	public String getName() { return _name; }	
	// newly added
	public boolean solved() { return _solved; }
			
	// TODO: Add any helper methods you need
	
	/** Run the simple greedy heuristic -- add the next best set until either
	 *  (1) The coverage level is reached, or 
	 *  (2) There is no set that can increase the coverage.
	 */
	public void solve() {
		
		// Reset the solver
		reset();
		
		// TODO: Preliminary initializations
		_elementsUncovered = new HashSet<Integer>();
		_elementsUncovered.addAll(_model.getElementsCopy()); // Initially every element in _model is uncovered, so add them all into _elementsNotCovered

		// Begin the greedy selection loop
		long start = System.currentTimeMillis();
		System.out.println("Running '" + getName() + "'...");

		// TODO: Fill in the main loop, pseudocode given below
		//
		// NOTE: In order to match the solution, pay attention to the following
		//       calculations (where you have to replace ALL-CAPS parts)
		//
		// int num_to_cover = (int)Math.ceil(_alpha * TOTAL_NUMBER_OF_ELEMENTS_IN_SCPMODEL);
		// int num_can_leave_uncovered = TOTAL_NUMBER_OF_ELEMENTS_IN_SCPMODEL - num_to_cover;
		//
		// while (NUM_ELEMENTS_NOT_COVERED > num_can_leave_uncovered 
		//        && ALL_POSSIBLE_SETS_HAVE_NOT_BEEN_SELECTED)
		//
		//      Call nextBestSet() to get the next best ElementSet to add (if there is one)
		// 		Update solution and local members
				
		// Record final set coverage, compTime and print warning if applicable
		
		int num_to_cover = (int)Math.ceil(_alpha * _model.getNumElements());
		int num_can_leave_uncovered = _model.getNumElements() - num_to_cover;
		boolean ALL_POSSIBLE_SETS_HAVE_NOT_BEEN_SELECTED = false; 
		while (_elementsUncovered.size() > num_can_leave_uncovered && !ALL_POSSIBLE_SETS_HAVE_NOT_BEEN_SELECTED) {
			ElementSet nextBestSet = this.nextBestSet();			
			if (nextBestSet == null) { // No set left
				ALL_POSSIBLE_SETS_HAVE_NOT_BEEN_SELECTED = true; 
				break;
			}
			_objFn += nextBestSet.getCost(); // Add the cost of the new set to total cost
			_solnSets.add(nextBestSet); // Add the new set to total set
			_elementsUncovered.removeAll(nextBestSet.getElements()); // remove the set that has been selected from _elementsNotCovered
			System.out.println("- Selected: " + nextBestSet.toString());
		}
		
//		_coverage = -1d; // TODO: Correct this, should be coverage of solution found
		_coverage = (_model.getNumElements() - _elementsUncovered.size())/(double)_model.getNumElements();
		_solved = true;
		_compTime = System.currentTimeMillis() - start;
		if (_coverage < _alpha)
			System.out.format("\nWARNING: Impossible to reach %.2f%% coverage level.\n", 100*_alpha);
		System.out.println("Done.");
	}
	
	public void reset() {
		_objFn = 0;
		_coverage = 0;
		_compTime = 0;
		_solnSets = new TreeSet<ElementSet>();
		_elementsUncovered = null;
		_solved = false;
	}
	
	/** Returns the next best set to add to the solution according to the heuristic being used.
	 * 
	 *  NOTE 1: This is the **only** method to be implemented in child classes.
	 *  
	 *  NOTE 2: If no set can improve the solution, returns null to allow the greedy algorithm to terminate.
	 *  
	 *  NOTE 3: This references an ElementSet class which is a tuple of (Set ID, Cost, Integer elements to cover)
	 *          which you must define.
	 * 
	 * @return
	 */
	public abstract ElementSet nextBestSet(); // Abstract b/c it must be implemented by subclasses
	
	/** Print the solution
	 * 
	 */
	public void print() {
		System.out.println("\n'" + getName() + "' results:");
		System.out.format("'" + getName() + "'   Time to solve: %dms\n", _compTime);
		System.out.format("'" + getName() + "'   Objective function value: %.2f\n", _objFn);
		System.out.format("'" + getName() + "'   Coverage level: %.2f%% (%.2f%% minimum)\n", 100*_coverage, 100*_alpha);
		System.out.format("'" + getName() + "'   Number of sets selected: %d\n", _solnSets.size());
		System.out.format("'" + getName() + "'   Sets selected: ");
		for (ElementSet s : _solnSets) {
			System.out.print(s.getId() + " ");
		}
		System.out.println("\n");
	}
	
	/** Print the solution performance metrics as a row
	 * 
	 */
	public void printRowMetrics() {
		System.out.format("%-25s%12d%15.4f%17.2f\n", getName(), _compTime, _objFn, 100*_coverage);
	}	
}
