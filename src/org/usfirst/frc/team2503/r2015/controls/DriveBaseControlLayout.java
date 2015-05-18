package org.usfirst.frc.team2503.r2015.controls;

public interface DriveBaseControlLayout extends ControlLayout {
	
	public enum ValueRegisters {
		LEFT(0),
		RIGHT(1),
		WINCH(2);
		
		private int index;
		
		public int toInt() {
			return this.index;
		}
		
		private ValueRegisters(int index) {
			this.index = index;
		}
	}
	
}
