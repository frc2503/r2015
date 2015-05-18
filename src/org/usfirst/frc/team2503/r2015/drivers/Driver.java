package org.usfirst.frc.team2503.r2015.drivers;

/**
 * An interface to represent all Drivers; each Driver should get its own
 * values and handle its own math; the tick() function should basically just be
 * a function that is designed to be called regularly and handle all movement
 * of a DriveBase.
 * 
 * @author Kristofer Rye
 */
public interface Driver {
	
	public void tick();
	
}
