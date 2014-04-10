package test;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class USListener implements SensorPortListener{

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		// TODO Auto-generated method stub
		if ( aNewValue>20&&aOldValue > aNewValue)
			Sound.playTone (dist*10+100,300,100);
	}



}
