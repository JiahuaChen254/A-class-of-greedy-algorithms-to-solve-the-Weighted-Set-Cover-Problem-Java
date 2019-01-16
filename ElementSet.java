package util;

//import java.util.Arrays; // for testing
import java.util.Collection;
import java.util.HashSet; // for testing
import java.util.Set;
import java.util.TreeSet;

public class ElementSet implements Comparable{	
	private int _id;
	private double _cost;
	private int _elementstocover;
	private Collection<Integer> _elements;  
	
	public ElementSet(int id, double cost, Collection<Integer> elements) {
		_id = id;
		_cost  = cost;
		_elements = new HashSet<Integer>(elements);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Set<Integer> ts = new TreeSet<Integer>();
		ts.addAll(_elements);
		sb.append(String.format("Set ID:%4d   Cost:%7.2f   Element IDs: %s", _id, _cost, ts));
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ElementSet)) {
			return false;
		}
		ElementSet es = (ElementSet)o; // if o is not ElementSet, ClassCastException
		if (es._id != _id || es._cost != _cost || !es._elements.equals(_elements)) {
			return false;
		}
		return true;
	}
		
	@Override
	public int compareTo(Object o) {
		if (!(o instanceof ElementSet)) {
			return 0; 
		}		
		ElementSet es = (ElementSet)o;
		if (_id != es._id || _cost != es._cost) {
			return (_id - es._id); 
		}
		else if (!_elements.equals(es._elements)) {
			// if _id, _cost, or _elements is not equal, return the difference of _id
			return (_id - es._id);
		}			
		return 0; // Must be equal
	}
	
	public boolean containsElement(int id) {
		return _elements.contains(id);
	}
	
	public int countElementsCovered(Set<Integer> elements_to_cover) {
		_elementstocover = 0;
		for (int i: elements_to_cover) {
			if (_elements.contains(i)) {
				_elementstocover += 1;
			}
		}
		return _elementstocover;
	}
	
	
	public double getCost() {
		return _cost;
	}
	
	public int getId() {
		return _id;
	}
	
	public double getNumElements() {
		return _elements.size();
		
	}
	
	public Iterable<Integer> getElementIterable(){
		Set<Integer> ts = new TreeSet<Integer>();
		ts.addAll(_elements);
		Iterable<Integer> iterable = ts;
		return iterable;
	}
	
	// Added code
	public Collection<Integer> getElements() {
		return _elements;
	}
	
/*	
	// My test
	public static void main(String[] args) throws Exception {		
		// My test for ElementSet
		System.out.println("1. Testing toString()");
		ElementSet es = new ElementSet(6, 2.0, Arrays.asList(new Integer[] {4,8}));
		ElementSet es0 = new ElementSet(6, 2.0, Arrays.asList(new Integer[] {4,8}));
		ElementSet es1 = new ElementSet(5, 2.0, Arrays.asList(new Integer[] {2,6,100}));
		ElementSet es2 = new ElementSet(3, 2.0, Arrays.asList(new Integer[] {4,8}));
		System.out.println(es.toString()); // Set ID:   6   Cost:   2.00   Element IDs: [4, 8]
		System.out.println(es1.toString()); // Set ID:   5   Cost:   2.00   Element IDs: [2, 6, 100]
		
		System.out.println("2. Testing equals(Object o)");
		System.out.println(es.equals(es0)); // true
		System.out.println(es.equals(es1)); // false
		
		System.out.println("3. Testing compareTo()");
		System.out.println(es.compareTo(es0)); // 0
		System.out.println(es.compareTo(es1)); // 1
		System.out.println(es.compareTo(es2)); // 3
		String s = "100";
		System.out.println(es.compareTo(s)); // 0 
		
		System.out.println("4. Testing containsElement(int id)");
		System.out.println(es.containsElement(4)); // true
		System.out.println(es.containsElement(9)); // false 
		
		System.out.println("5. Testing countElementsCovered(Set<Integer> elements_to_cover)");
		Set<Integer> set1 = new HashSet<>();
		set1.add(3);
		set1.add(9);
		Set<Integer> set2 = new HashSet<>();
		set2.add(4);
		set2.add(6);
		System.out.println(es.countElementsCovered(set1)); // 0
		System.out.println(es.countElementsCovered(set2)); // 1
		
		System.out.println("6. Testing getCost()");
		System.out.println(es.getCost()); // 2.0
		System.out.println(es1.getCost()); // 2.0
		
		System.out.println("7. Testing getId()");
		System.out.println(es.getId()); // 6
		System.out.println(es1.getId()); // 5
		
		System.out.println("8. Testing getNumElements()");
		System.out.println(es.getNumElements()); // 2.0
		System.out.println(es1.getNumElements()); // 3.0 
		
		System.out.println("9. Testing getElementIterable()");
		System.out.println(es.getElementIterable()); // [4, 8]
		System.out.println(es1.getElementIterable()); // [2, 6, 100] 
		
		System.out.println("10. Testing getElement(int i)");
		System.out.println(es.getElement(0));
		System.out.println(es.getElement(1));
	} */

}
