package org.usfirst.frc.team2503.r2015.network.clients;

import edu.wpi.first.wpilibj.image.HSLImage;

public class VisionClient implements SuccessMeasuringNetworkClient {
	public static HSLImage hslImage;

	public static boolean previousRequestWasSuccessful = false;
}