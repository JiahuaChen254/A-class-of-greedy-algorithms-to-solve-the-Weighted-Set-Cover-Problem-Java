package model;

//import java.util.Arrays; // for testing
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;
import util.ElementSet;

public class SCPModel {
	private SortedSet<ElementSet> _ts;
	
	public SCPModel() { 
		_ts = new TreeSet<ElementSet>();
	}
	
	public void addSetToCover(int id, double cost, Collection<Integer> elements) {
		_ts.add(new ElementSet(id, cost, elements));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nWeighted SCP model:\n" + "---------------------\n");
		sb.append("Number of elements (n): " + this.getNumElements() + "\n");
		sb.append("Number of sets (m): " + this.getNumElementSets() + "\n\n");
		sb.append("Set details:\n" + "----------------------------------------------------------\n");
		for (ElementSet e : _ts) {
			sb.append(e + "\n");
		}
		return sb.toString();
	}
	
	public int getNumElements() {
		return getElementsCopy().size();
	}
	
	public int getNumElementSets() {
		return _ts.size();
	}
	
	public SortedSet<Integer> getElementsCopy() {
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for (ElementSet es : _ts) {
			Iterable<Integer> iterable = es.getElementIterable();
			for (int i : iterable) {
				ts.add(i);
			}
		}
		return ts;
	}
	
	public Iterable<ElementSet> getElementSetIterable() {
		Iterable<ElementSet> iterable = _ts;
		return iterable;	
	}

	
/*	public List<Integer> getElementsSCP (){	
		return _es.getElements(); // return _elements;
	}

	
	public static void main(String[] args) throws Exception {
		// My test for SCPModel
		SCPModel m = new SCPModel();
		System.out.println("1. Testing addSetToCover(int id, double cost, List<Integer> elements)");
		m.addSetToCover(6, 2.0, Arrays.asList(new Integer[] {4,8}));
		m.addSetToCover(4, 5.0, Arrays.asList(new Integer[] {2,4,6,8,100}));
		m.addSetToCover(1, 3.0, Arrays.asList(new Integer[] {1,3,5,7,9}));
		
		System.out.println("2. Testing toString()");
		System.out.println(m.toString()); */
/*		Weighted SCP model:
		---------------------
		Number of elements (n): 10
		Number of sets (m): 3

		Set details:
		----------------------------------------------------------
		Set ID:   1   Cost:   3.00   Element IDs: [1, 3, 5, 7, 9] 
		Set ID:   4   Cost:   5.00   Element IDs: [2, 4, 6, 8, 100]
		Set ID:   6   Cost:   2.00   Element IDs: [4, 8] */
		
/*		System.out.println("3. Testing getNumElements()");
		System.out.println(m.getNumElements()); // 10
		
		System.out.println("4. Testing getNumElementSets()");
		System.out.println(m.getNumElementSets()); // 3
		
		System.out.println("5. Testing getElementsCopy()");
		System.out.println(m.getElementsCopy()); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 100]
		
		System.out.println("6. Testing getElementSetIterable()");
		System.out.println(m.getElementSetIterable()); 
		// [Set ID:   1   Cost:   3.00   Element IDs: [1, 3, 5, 7, 9], Set ID:   4   Cost:   5.00   Element IDs: [2, 4, 6, 8, 100], Set ID:   6   Cost:   2.00   Element IDs: [4, 8]]	
	} */

}
