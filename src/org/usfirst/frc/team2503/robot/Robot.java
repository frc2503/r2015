package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2503.robot.driver.DriveBaseGamepadF310ControllerDriveBaseDriver;
import org.usfirst.frc.team2503.joystick.GamepadF310Controller;
import org.usfirst.frc.team2503.joystick.WarriorJoystick.WarriorJoystickSide;

public class Robot extends IterativeRobot {
	//public DriveBaseForwardBackSaitekAV8RDriveBaseDriver standardDriveBaseDriver;
	public DriveBaseGamepadF310ControllerDriveBaseDriver gamepadDriveBaseDriver;
	public DriverStation driverStation;
	
    public void robotInit() {
    }
    
    public void disabledInit() {
    }
    
    public void disabledPeriodic() {
    }

    public void autonomousInit() {
    }

    public void autonomousPeriodic() {
    }
    
    public void teleopInit() {
    }
    
    public void teleopPeriodic() {
    	//standardDriveBaseDriver.drive();
    	gamepadDriveBaseDriver.drive();
    }
    
    public void testInit() {
    }
    
    public void testPeriodic() {
    }
    
    public Robot() {
    	//standardDriveBaseDriver = new DriveBaseForwardBackSaitekAV8RDriveBaseDriver(new LogitechAttack3Joystick(WarriorJoystickSide.LEFT_PRIMARY), new SaitekAV8RJoystick(WarriorJoystickSide.RIGHT_PRIMARY));
    	gamepadDriveBaseDriver = new DriveBaseGamepadF310ControllerDriveBaseDriver(new GamepadF310Controller(WarriorJoystickSide.LEFT_PRIMARY));
    	driverStation = DriverStation.getInstance();
    }
}
