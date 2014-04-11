package test;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.Sound;

public class USListener implements SensorPortListener{

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		// TODO Auto-generated method stub
		if ( aNewValue>20)
			Sound.playTone (100,300,100);
	}



}
