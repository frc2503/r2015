package org.usfirst.frc.team2503.r2015;

import java.lang.reflect.Array;
import java.util.HashMap;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2503.r2015.controls.ControlLayout;
import org.usfirst.frc.team2503.r2015.controls.ControlPort;
import org.usfirst.frc.team2503.r2015.controls.DriveBaseMadCatzV1MadCatzV1ControlLayout;
import org.usfirst.frc.team2503.r2015.controls.joysticks.MadCatzV1Joystick;
import org.usfirst.frc.team2503.r2015.drivers.BlindAutonomousDriveBaseDriver;
import org.usfirst.frc.team2503.r2015.drivers.BlindAutonomousScheduleItem;
import org.usfirst.frc.team2503.r2015.drivers.Driver;
import org.usfirst.frc.team2503.r2015.drivers.HumanDriveBaseDriver;
import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.DriveBase;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

public class Robot extends IterativeRobot {
	public DriverStation driverStation;
	public DriveBase driveBase;
	public BlindAutonomousDriveBaseDriver autonomousDriver;
	public HumanDriveBaseDriver teleopDriver;
	
	public void robotInit() {
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
		
	}

	public void autonomousInit() {
		HashMap<double[], BlindAutonomousScheduleItem> schedule = new HashMap<double[], BlindAutonomousScheduleItem>();
		
		BlindAutonomousScheduleItem driveBaseTurnRightHalfItem = (double time) -> { driveBase.drive(0.5, 0.0); };
		BlindAutonomousScheduleItem driveBaseTurnLeftHalfItem = (double time) -> { driveBase.drive(0.0, -0.5); };
		
		BlindAutonomousScheduleItem driveBaseStopItem = (double time) -> { driveBase.drive(0.0, 0.0); };
		BlindAutonomousScheduleItem driveBaseDriveForwardHalfItem = (double time) -> { driveBase.drive(0.5, -0.5); };
		BlindAutonomousScheduleItem driveBaseDriveBackwardHalfItem = (double time) -> { driveBase.drive(0.5, -0.5); };
		
		BlindAutonomousScheduleItem clampOpenItem = (double time) -> { driveBase.clamp.set(ClampStatus.OPEN); };
		BlindAutonomousScheduleItem clampCloseItem = (double time) -> { driveBase.clamp.set(ClampStatus.CLOSE); };
		
		BlindAutonomousScheduleItem winchUpFourFifthsItem = (double time) -> { driveBase.winch(0.8); };
		BlindAutonomousScheduleItem winchStopItem = (double time) -> { driveBase.winch(0.0); };
		BlindAutonomousScheduleItem winchDownFourFifthsItem = (double time) -> { driveBase.winch(-0.8); };
		
		BlindAutonomousScheduleItem lightsAllOnItem = (double time) -> { driveBase.masterLightsController.set(MasterLightsControllerStatus.ALL_ON); };
		BlindAutonomousScheduleItem lightsAllSlowStrobeItem = (double time) -> { driveBase.masterLightsController.set(MasterLightsControllerStatus.ALL_SLOW_STROBE); };
		
		schedule.put(new double[] {0.0, Double.POSITIVE_INFINITY}, clampCloseItem);
		schedule.put(new double[] {0.0, 12.25}, lightsAllOnItem);
		schedule.put(new double[] {0.0, 6.5}, driveBaseStopItem);
		schedule.put(new double[] {0.0, 6.5}, winchStopItem);
		schedule.put(new double[] {6.5, 10.25}, winchUpFourFifthsItem);
		schedule.put(new double[] {8.5, 10.25}, driveBaseTurnRightHalfItem);
		schedule.put(new double[] {10.25, 10.35}, driveBaseStopItem);
		schedule.put(new double[] {10.25, 10.35}, winchStopItem);
		schedule.put(new double[] {10.35, 12.25}, driveBaseDriveForwardHalfItem);
		schedule.put(new double[] {12.25, Double.POSITIVE_INFINITY}, driveBaseStopItem);
		schedule.put(new double[] {12.25, Double.POSITIVE_INFINITY}, winchStopItem);
		schedule.put(new double[] {12.25, Double.POSITIVE_INFINITY}, lightsAllSlowStrobeItem);

		autonomousDriver.setSchedule(schedule);
		autonomousDriver.start();
	}
	
	public void autonomousPeriodic() {
		autonomousDriver.tick();
	}
	
	public void teleopInit() {
		teleopDriver.start();
	}
	
	public void teleopPeriodic() {
		teleopDriver.tick();
	}
	
	public void testInit() {
	}
	
	public void testPeriodic() {
		driveBase.masterLightsController.set(MasterLightsControllerStatus.ALL_SLOW_CYCLE);
	}
	
	public Robot() {
		driveBase = new DriveBase();

		autonomousDriver = new BlindAutonomousDriveBaseDriver(driveBase);
		teleopDriver = new HumanDriveBaseDriver(driveBase, new DriveBaseMadCatzV1MadCatzV1ControlLayout(new MadCatzV1Joystick(ControlPort.LEFT_PRIMARY), new MadCatzV1Joystick(ControlPort.RIGHT_PRIMARY)));
		driverStation = DriverStation.getInstance();
		
		System.out.println("autonomousDriver" + autonomousDriver);
		System.out.println("teleopDriver" + teleopDriver);
		System.out.println("driverStation" + driverStation);
		
		
	}
}
