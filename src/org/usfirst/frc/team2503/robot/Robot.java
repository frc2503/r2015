package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2503.robot.driver.DriveBaseMadCatzV1MadCatzV1DriveBaseDriver;
import org.usfirst.frc.team2503.joystick.MadCatzV1Joystick;
import org.usfirst.frc.team2503.joystick.WarriorJoystick.WarriorJoystickSide;

public class Robot extends IterativeRobot {
	//public DriveBaseForwardBackSaitekAV8RDriveBaseDriver standardDriveBaseDriver;
	//public DriveBaseGamepadF310ControllerDriveBaseDriver gamepadDriveBaseDriver;
	public DriveBaseMadCatzV1MadCatzV1DriveBaseDriver madCatzDriveBaseDriver;
	public DriverStation driverStation;
	
	private boolean lights = false;
	private long counter = 0;
	
    public void robotInit() {}
    
    public void disabledInit() {
    }
    
    public void disabledPeriodic() {
    }

    public void autonomousInit() {
    	/* Asynchronous autonomous stuff goes here. */
    	
    	counter = 0;
    }
    
    public void autonomousPeriodic() {
    	counter += 1;
    	
    	if(counter >= 5) {
    		lights = !lights;
    		madCatzDriveBaseDriver.lights(lights);

    		counter = 0;
    	}
    }
    
    public void teleopInit() {}
    public void teleopPeriodic() {
    	//standardDriveBaseDriver.drive();
    	//gamepadDriveBaseDriver.drive();
    	madCatzDriveBaseDriver.drive();
    }
    
    public void testInit() {
    	counter = 0;
    }
    
    public void testPeriodic() {
    	counter += 1;
    	    	
    	if(counter >= 5) {
    		lights = !lights;
    		madCatzDriveBaseDriver.lights(lights);

    		counter = 0;
    	}
    }
    
    public Robot() {
    	//standardDriveBaseDriver = new DriveBaseForwardBackSaitekAV8RDriveBaseDriver(new LogitechAttack3Joystick(WarriorJoystickSide.LEFT_PRIMARY), new SaitekAV8RJoystick(WarriorJoystickSide.RIGHT_PRIMARY));
    	//gamepadDriveBaseDriver = new DriveBaseGamepadF310ControllerDriveBaseDriver(new GamepadF310Controller(WarriorJoystickSide.LEFT_PRIMARY));
    	madCatzDriveBaseDriver = new DriveBaseMadCatzV1MadCatzV1DriveBaseDriver(new MadCatzV1Joystick(WarriorJoystickSide.LEFT_PRIMARY), new MadCatzV1Joystick(WarriorJoystickSide.RIGHT_PRIMARY));
    	driverStation = DriverStation.getInstance();
    }
}
