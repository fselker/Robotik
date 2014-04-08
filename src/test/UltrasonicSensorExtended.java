package test;

import java.util.ArrayList;

import lejos.nxt.I2CPort;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class UltrasonicSensorExtended extends UltrasonicSensor implements Runnable {
	ArrayList<USListener> Array;

	public UltrasonicSensorExtended(I2CPort port) {
		super(port);

		// TODO Auto-generated constructor stub
	}

	public void addSensorPortListener(USListener usl) {
		if (Array.size() < 8) {
			Array.add(usl);
			
		}
	}

	public void removeSensorPortListener(USListener usl) {
		if (Array.contains(usl)) {
			
			Array.remove(usl);
			
		}
		
		
	}

	@Override
	public void run() {
		int alt=0,neu=0;
		
		while(true){
			neu=getDistance();
			if(neu!=alt)
				for(USListener ul : Array){
					ul.stateChanged((SensorPort)port, alt, neu);
			}
			alt= getDistance();
		}
		
	}
}
