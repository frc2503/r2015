package org.usfirst.frc.team2503.robot;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2503.robot.driver.DriveBaseForwardBackSaitekAV8RDriveBaseDriver;
import org.usfirst.frc.team2503.joystick.LogitechAttack3Joystick;
import org.usfirst.frc.team2503.joystick.SaitekAV8RJoystick;
import org.usfirst.frc.team2503.joystick.WarriorJoystick.WarriorJoystickSide;

public class Robot extends IterativeRobot {
	public DriveBaseForwardBackSaitekAV8RDriveBaseDriver driveBaseDriver;
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
    	driveBaseDriver.drive();
    }
    
    public void testInit() {
    }
    
    public void testPeriodic() {
    }
    
    public Robot() {
    	driveBaseDriver = new DriveBaseForwardBackSaitekAV8RDriveBaseDriver(new LogitechAttack3Joystick(WarriorJoystickSide.LEFT_PRIMARY), new SaitekAV8RJoystick(WarriorJoystickSide.RIGHT_PRIMARY));
    	driverStation = DriverStation.getInstance();
    }
}
