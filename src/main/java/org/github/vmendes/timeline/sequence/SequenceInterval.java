package org.github.vmendes.timeline.sequence;

import org.github.vmendes.timeline.AbstractInterval;
import org.github.vmendes.timeline.Interval;


public class SequenceInterval<V> extends AbstractInterval<Integer, V> {

	private Integer start;

	private Integer end;

	public SequenceInterval(Integer start, Integer end, V value) {
		super(value);
		initIntegers(start, end);
	}
	private void initIntegers(Integer start, Integer end) {
		start = start == null ? Integer.MIN_VALUE : start;
		end = end == null ? Integer.MAX_VALUE : end;
		
		if (start > end) {
			Integer temp = start;
			start = end;
			end = temp;
		}
		
		this.start = start;
		this.end = end;
	}

		
	@Override
	public boolean contains(Integer index) {
		if (index == null) {
			index = 0;
		}
        return (index >= getStart() && index < getEnd());
	}

	/**
     * Does this time interval overlap the specified time interval.
     * 
     * @param interval  the time interval to compare to, null means a zero length interval now
     * @return true if the time intervals overlap
     */
	@Override
    public boolean overlaps(Interval<Integer, V> other) {
        if (other == null) {
            int base = 0;
            return (getStart() < base && base < getEnd());
        }  else {
            int otherStart = other.getStart();
            int otherEnd = other.getEnd();
            return (getStart() < otherEnd && otherStart < getEnd());
        }
    }

	@Override
	public Integer getStart() {
		return this.start;
	}

	@Override
	public Integer getEnd() {
		return this.end;
	}
	
	

}
