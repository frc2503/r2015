package org.usfirst.frc.team2503.vision;

import org.usfirst.frc.team2503.network.clients.VisionClient;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

public class VisionController implements Runnable {
	public void run() {
		while(true) {
			HSLImage image = VisionClient.hslImage;
			
			try {
				System.out.println("Image [w, h] = [" + image.getWidth() + ", " + image.getHeight() + "]");
			} catch (NIVisionException e) {
				e.printStackTrace();
			}
			
			Timer.delay(0.25);
		}
	}
}