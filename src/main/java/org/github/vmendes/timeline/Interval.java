package org.github.vmendes.timeline;

public interface Interval<T, V> {

	boolean contains(T type);
	
	boolean overlaps(Interval<T, V> other);
	
	T getStart();
	
	T getEnd();
	
	V getValue();
}
