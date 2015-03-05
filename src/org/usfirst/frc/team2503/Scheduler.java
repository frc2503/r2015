package org.usfirst.frc.team2503;

import java.util.HashMap;
import java.util.Set;

public class Scheduler {
	private HashMap<double[], Object> schedule = new HashMap<double[], Object>();

	public Object getForTimeSpan(double[] timeSpan) {
		Set<double[]> keySet = schedule.keySet();
		
		double[] firstMatchingKey = null;
		
		while(keySet.iterator().hasNext()) {
			double[] key = keySet.iterator().next();
			
			if(timeSpan[0] == key[0] && timeSpan[1] == key[1]) {
				firstMatchingKey = key;
				break;
			}
		}

		if(firstMatchingKey != null) {
			return schedule.get(firstMatchingKey);
		} else {
			/**
			 * Uh oh! No matching key, nothing to do.
			 */
			
			return null;
		}
	}
	
	public Object getForTime(double time) {
		Set<double[]> keySet = schedule.keySet();
		
		double[] firstMatchingKey = null;
		
		while(keySet.iterator().hasNext()) {
			double[] key = keySet.iterator().next();
			
			if(time >= key[0] && time < key[1]) {
				firstMatchingKey = key;
				break;
			}
		}
		
		if(firstMatchingKey != null) {
			return schedule.get(firstMatchingKey);
		} else {
			/**
			 * Uh oh! No matching key, nothing to do.
			 */
			
			return null;
		}
	}
	
	public void pushScheduleItem(double[] array, Object object) {
		schedule.put(array, object);
	}
	
	public Scheduler() {
	}
}
