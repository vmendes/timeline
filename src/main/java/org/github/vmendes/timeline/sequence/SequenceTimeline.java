package org.github.vmendes.timeline.sequence;

import java.util.List;

import org.github.vmendes.timeline.Interval;
import org.github.vmendes.timeline.Timeline;

public class SequenceTimeline<V> extends Timeline<Integer, V> {

	@SafeVarargs
	public SequenceTimeline(Interval<Integer, V>... intervals) {
		super(intervals);
	}

	public SequenceTimeline(List<? extends Interval<Integer, V>> intervals) {
		super(intervals);
	}
	
}
