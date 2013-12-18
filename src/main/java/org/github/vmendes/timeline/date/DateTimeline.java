package org.github.vmendes.timeline.date;

import java.util.Date;
import java.util.List;

import org.github.vmendes.timeline.Interval;
import org.github.vmendes.timeline.Timeline;

public class DateTimeline<V> extends Timeline<Date, V> {

	@SafeVarargs
	public DateTimeline(Interval<Date, V>... intervals) {
		super(intervals);
	}

	public DateTimeline(List<? extends Interval<Date, V>> intervals) {
		super(intervals);
	}
	
}
