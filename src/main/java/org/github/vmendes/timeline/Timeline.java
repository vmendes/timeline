package org.github.vmendes.timeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Timeline<T, V> {

	private final List<Interval<T, V>> intervals;

	@SuppressWarnings("unchecked")
	public Timeline(List<? extends Interval<T, V>> intervals) {
		super();
		this.intervals = (List<Interval<T, V>>) intervals;
	}

	@SafeVarargs
	public Timeline(final Interval<T, V>... intervals) {
		this(Arrays.asList(intervals));
	}

	public List<Interval<T, V>> getIntervals() {
		return intervals;
	}
	
	public void addInterval(final Interval<T, V> interval) {
		getIntervals().add(interval);
	}

	public List<V> valueOn(T type) {
		List<V> values = new ArrayList<V>();
		List<? extends Interval<T, V>> intervals = getIntervals();
		for (Interval<T, V> intervalValue : intervals) {
			if(intervalValue.contains(type)) {
				values.add(intervalValue.getValue());
			}
		}
		return values;
	}
}
