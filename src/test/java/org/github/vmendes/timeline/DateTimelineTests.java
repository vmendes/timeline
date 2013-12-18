package org.github.vmendes.timeline;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.github.vmendes.timeline.date.DateInterval;

public class DateTimelineTests extends TestCase {
	
	private static Date date0 = new Date(0L);
	private static Date date1 = new Date(1000L);
	private static Date date2 = new Date(2000L);
	private static Date date3 = new Date(3000L);
	private static Date date4 = new Date(4000L);
	private static Date date5 = new Date(5000L);

	@Override
	protected void setUp() throws Exception {
		//
	}
	
	public void testNullDates() throws Exception {
		DateInterval<String> interval1 = new DateInterval<String>(null, null, "123");
		Timeline<Date, String> timeline = new Timeline<Date, String>(interval1);
		Assert.assertFalse(timeline.valueOn(date0).isEmpty());
		Assert.assertFalse(timeline.valueOn(date1).isEmpty());
		Assert.assertFalse(timeline.valueOn(date2).isEmpty());
		Assert.assertFalse(timeline.valueOn(date3).isEmpty());
		Assert.assertFalse(timeline.valueOn(date4).isEmpty());
		Assert.assertFalse(timeline.valueOn(date5).isEmpty());
	}
	
	public void testStartOnly() throws Exception {
		DateInterval<String> interval1 = new DateInterval<String>(date1, null, "123");
		Timeline<Date, String> timeline = new Timeline<Date, String>(interval1);
		Assert.assertTrue(timeline.valueOn(date0).isEmpty());
		Assert.assertFalse(timeline.valueOn(date1).isEmpty());
		Assert.assertFalse(timeline.valueOn(date2).isEmpty());
		Assert.assertFalse(timeline.valueOn(date3).isEmpty());
		Assert.assertFalse(timeline.valueOn(date4).isEmpty());
		Assert.assertFalse(timeline.valueOn(date5).isEmpty());
	}
	
	public void testEndOnly() throws Exception {
		DateInterval<String> interval1 = new DateInterval<String>(null, date4, "123");
		Timeline<Date, String> timeline = new Timeline<Date, String>(interval1);
		Assert.assertFalse(timeline.valueOn(date0).isEmpty());
		Assert.assertFalse(timeline.valueOn(date1).isEmpty());
		Assert.assertFalse(timeline.valueOn(date2).isEmpty());
		Assert.assertFalse(timeline.valueOn(date3).isEmpty());
		Assert.assertFalse(timeline.valueOn(date4).isEmpty());
		Assert.assertTrue(timeline.valueOn(date5).isEmpty());
	}
	
	public void testSimplePeriod() throws Exception {
		DateInterval<String> interval1 = new DateInterval<String>(date2, date4, "123");
		Timeline<Date, String> timeline = new Timeline<Date, String>(interval1);
		Assert.assertTrue(timeline.valueOn(date0).isEmpty());
		Assert.assertTrue(timeline.valueOn(date1).isEmpty());
		Assert.assertFalse(timeline.valueOn(date2).isEmpty());
		Assert.assertFalse(timeline.valueOn(date3).isEmpty());
		Assert.assertFalse(timeline.valueOn(date4).isEmpty());
		Assert.assertTrue(timeline.valueOn(date5).isEmpty());
		
		assertTimelineValues(timeline, date0);
		assertTimelineValues(timeline, date1);
		assertTimelineValues(timeline, date2, "123");
		assertTimelineValues(timeline, date3, "123");
		assertTimelineValues(timeline, date4, "123");
		assertTimelineValues(timeline, date5);
	}
	
	public void testOverlapPeriods() throws Exception {
		DateInterval<String> interval1 = new DateInterval<String>(date1, date3, "123");
		DateInterval<String> interval2 = new DateInterval<String>(date2, date4, "456");
				
		Timeline<Date, String> timeline = new Timeline<Date, String>(interval1, interval2);
		Assert.assertEquals(0, timeline.valueOn(date0).size());
		Assert.assertEquals(1, timeline.valueOn(date1).size());
		Assert.assertEquals(2, timeline.valueOn(date2).size());
		Assert.assertEquals(2, timeline.valueOn(date3).size());
		Assert.assertEquals(1, timeline.valueOn(date4).size());
		Assert.assertEquals(0, timeline.valueOn(date5).size());
		
		assertTimelineValues(timeline, date0);
		assertTimelineValues(timeline, date1, "123");
		assertTimelineValues(timeline, date2, "123", "456");
		assertTimelineValues(timeline, date3, "123", "456");
		assertTimelineValues(timeline, date4, "456");
		assertTimelineValues(timeline, date5);
		
	}
	
	public void testDistinctPeriods() throws Exception {
		DateInterval<String> interval1 = new DateInterval<String>(date0, date1, "123");
		DateInterval<String> interval2 = new DateInterval<String>(date4, date5, "456");
				
		Timeline<Date, String> timeline = new Timeline<Date, String>(interval1, interval2);
		Assert.assertEquals(1, timeline.valueOn(date0).size());
		Assert.assertEquals(1, timeline.valueOn(date1).size());
		Assert.assertEquals(0, timeline.valueOn(date2).size());
		Assert.assertEquals(0, timeline.valueOn(date3).size());
		Assert.assertEquals(1, timeline.valueOn(date4).size());
		Assert.assertEquals(1, timeline.valueOn(date5).size());
		
		assertTimelineValues(timeline, date0, "123");
		assertTimelineValues(timeline, date1, "123");
		assertTimelineValues(timeline, date2);
		assertTimelineValues(timeline, date3);
		assertTimelineValues(timeline, date4, "456");
		assertTimelineValues(timeline, date5, "456");
	}
	
	private void assertTimelineValues(Timeline<Date, String> timeline, Date date, String...strings) {
		List<String> valueOn = timeline.valueOn(date);
		Assert.assertEquals(strings.length, valueOn.size());
		for (String string : strings) {
			Assert.assertTrue(valueOn.contains(string));
		}
	}
}
