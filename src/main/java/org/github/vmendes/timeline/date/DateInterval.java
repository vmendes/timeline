package org.github.vmendes.timeline.date;

import java.util.Date;

import org.github.vmendes.timeline.AbstractInterval;
import org.github.vmendes.timeline.Interval;

public class DateInterval<V> extends AbstractInterval<Date, V> {

	public static final Date MIN_DATE = new Date(Long.MIN_VALUE);
	public static final Date MAX_DATE = new Date(Long.MAX_VALUE);
	
	private Date start;

	private Date end;

	public DateInterval(Date start, Date end, V value) {
		super(value);
		initDates(start, end);
	}

	private void initDates(Date start, Date end) {
		start = start == null ? MIN_DATE : start;
		end = end == null ? MAX_DATE : end;
		
		if (start.getTime() > end.getTime()) {
			Date temp = start;
			start = end;
			end = temp;
		}
		
		this.start = start;
		this.end = end;
	}

	@Override
	public Date getStart() {
		return start;
	}

	@Override
	public Date getEnd() {
		return end;
	}

	@Override
	public boolean contains(Date date) {
		if (date == null) {
			date = new Date();
		}
		
		long millisInstant = date.getTime();
		long thisStart = getStart().getTime();
        long thisEnd = getEnd().getTime();
        return (millisInstant >= thisStart && millisInstant <= thisEnd);
	}

	/**
     * Does this time interval overlap the specified time interval.
     * 
     * @param interval  the time interval to compare to, null means a zero length interval now
     * @return true if the time intervals overlap
     */
	@Override
    public boolean overlaps(Interval<Date, V> other) {
        long thisStart = getStart().getTime();
        long thisEnd = getEnd().getTime();
        if (other == null) {
            long now = System.currentTimeMillis();
            return (thisStart < now && now < thisEnd);
        }  else {
            long otherStart = other.getStart().getTime();
            long otherEnd = other.getEnd().getTime();
            return (thisStart < otherEnd && otherStart < thisEnd);
        }
    }
}
