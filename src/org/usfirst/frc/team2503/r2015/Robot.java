package org.usfirst.frc.team2503.r2015;

import java.lang.reflect.Array;
import java.util.HashMap;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team2503.r2015.controls.ControlLayout;
import org.usfirst.frc.team2503.r2015.controls.ControlPort;
import org.usfirst.frc.team2503.r2015.controls.DriveBaseControlLayout;
import org.usfirst.frc.team2503.r2015.controls.DriveBaseLogitechF310GamepadControlLayout;
import org.usfirst.frc.team2503.r2015.controls.DriveBaseMadCatzV1JoystickMadCatzV1JoystickControlLayout;
import org.usfirst.frc.team2503.r2015.controls.gamepads.LogitechF310Gamepad;
import org.usfirst.frc.team2503.r2015.controls.joysticks.MadCatzV1Joystick;
import org.usfirst.frc.team2503.r2015.drivers.BlindAutonomousDriveBaseDriver;
import org.usfirst.frc.team2503.r2015.drivers.BlindAutonomousScheduleItem;
import org.usfirst.frc.team2503.r2015.drivers.Driver;
import org.usfirst.frc.team2503.r2015.drivers.HumanDriveBaseDriver;
import org.usfirst.frc.team2503.r2015.robot.ClampStatus;
import org.usfirst.frc.team2503.r2015.robot.DriveBase;
import org.usfirst.frc.team2503.r2015.robot.lights.MasterLightsController.MasterLightsControllerStatus;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * @author Kristofer Rye
 */
public class Robot extends IterativeRobot {

	public DriveBase driveBase;
	public DriverStation driverStation;

	public BlindAutonomousDriveBaseDriver autonomousDriver;
	public HumanDriveBaseDriver teleopDriver;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any post-construction initialization code.
	 *
	 * @author Kristofer Rye
	 */
	public void robotInit() {
	}

	/**
	 * This function is called when the robot is entering disabled mode.
	 *
	 * @author Kristofer Rye
	 */
	public void disabledInit() {
	}

	/**
	 * This function is called periodically during disabled mode.
	 *
	 * Note that any motor or relay control done while the robot is disabled
	 * will not result in any output by the RoboRIO; the RoboRIO itself will
	 * not send any output during this time period.
	 *
	 * @author Kristofer Rye
	 */
	public void disabledPeriodic() {
	}

	/**
	 * This function is called when the robot is entering autonomous mode.
	 *
	 * @author Kristofer Rye
	 */
	public void autonomousInit() {
		/**
		 * The BlindAutonomous schedule.
		 */
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

		/**
		 * Construct the schedule.
		 *
		 * This code should result in the autonomous that we ran at LSR.
		 *
		 * @author Kristofer Rye
		 */
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

	/**
	 * This function is called periodically during autonomous mode.
	 *
	 * @author Kristofer Rye
	 */
	public void autonomousPeriodic() {
		autonomousDriver.tick();
	}

	/**
	 * This function is called when the robot is entering operator control ("teleop") mode.
	 *
	 * @author Kristofer Rye
	 */
	public void teleopInit() {
		teleopDriver.start();
	}

	/**
	 * This function is called periodically during operator control ("teleop") mode.
	 *
	 * @author Kristofer Rye
	 */
	public void teleopPeriodic() {
		teleopDriver.tick();
	}

	/**
	 * This function is called when the robot is entering test mode.
	 *
	 * @author Kristofer Rye
	 */
	public void testInit() {
	}

	/**
	 * This function is called periodically during test mode.
	 *
	 * @author Kristofer Rye
	 */
	public void testPeriodic() {
		driveBase.masterLightsController.set(MasterLightsControllerStatus.ALL_SLOW_CYCLE);
	}

	/**
	 * This is the constructor for the Robot class, which is called when the robot program starts up.
	 *
	 * Use this method to instantiate any variables.
	 *
	 * @author Kristofer Rye
	 */
	public Robot() {
		driveBase = new DriveBase();

		autonomousDriver = new BlindAutonomousDriveBaseDriver(driveBase);

		switch(Constants.teleopControlLayout) {
		case DRIVE_BASE_MADCATZ_V1_JOYSTICK_MADCATZ_V1_JOYSTICK_CONTROL_LAYOUT:
			teleopDriver = new HumanDriveBaseDriver(driveBase, new DriveBaseMadCatzV1JoystickMadCatzV1JoystickControlLayout(new MadCatzV1Joystick(ControlPort.LEFT_PRIMARY), new MadCatzV1Joystick(ControlPort.RIGHT_PRIMARY)));

			System.out.println("controlLayout == " + teleopDriver.controlLayout.getName());

			break;

		case DRIVE_BASE_LOGITECH_F310_GAMEPAD_CONTROL_LAYOUT:
			teleopDriver = new HumanDriveBaseDriver(driveBase, new DriveBaseLogitechF310GamepadControlLayout(new LogitechF310Gamepad(ControlPort.LEFT_SECONDARY)));

			System.out.println("controlLayout == " + teleopDriver.controlLayout.getName());

			break;

		default:
			teleopDriver = new HumanDriveBaseDriver(driveBase, new DriveBaseMadCatzV1JoystickMadCatzV1JoystickControlLayout(new MadCatzV1Joystick(ControlPort.LEFT_PRIMARY), new MadCatzV1Joystick(ControlPort.RIGHT_PRIMARY)));

			System.out.println("defaulting; controlLayout == " + teleopDriver.controlLayout.getName());

			break;
		}

		driverStation = DriverStation.getInstance();

		System.out.println("autonomousDriver" + autonomousDriver);
		System.out.println("teleopDriver" + teleopDriver);
		System.out.println("driverStation" + driverStation);

	}
}
