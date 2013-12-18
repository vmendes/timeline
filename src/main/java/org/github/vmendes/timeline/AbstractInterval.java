package org.github.vmendes.timeline;



public abstract class AbstractInterval<T, V> implements Interval<T, V>{

	private V value;

	public AbstractInterval(V value) {
		super();
		this.value = value;
	}

	@Override
	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
