package test;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;

public class KroetenWanderung implements SensorPortListener {

	public static void main(String args[]) {
		new KroetenWanderung();
		while (!Button.ESCAPE.isDown())
			;
	}

	TouchSensor touchLinks;
	TouchSensor touchRechts;
	UltrasonicSensorExtended us;
	Pilot p;
	boolean right = false, left = false;

	public KroetenWanderung() {
		touchLinks = new TouchSensor(SensorPort.S1);
		touchRechts = new TouchSensor(SensorPort.S4);
		 us = new UltrasonicSensorExtended(SensorPort.S2);
		 us.addSensorPortListener(new USListener());
		 

		p = new Pilot();
		p.setTravelSpeed(80);
		p.setRotateSpeed(20);
		SensorPort.S1.addSensorPortListener(this);
		SensorPort.S4.addSensorPortListener(this);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

		}
		p.stop();
		left=false;
		right=false;
		p.forward();

	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {
		if (Math.abs(aOldValue - aNewValue) < 500)
			return;
		boolean pressed = aOldValue > aNewValue;
		boolean dir = !aSource.equals(SensorPort.S1);
		if (dir && !left)
			right = true;
		if (!dir && !right)
			left = true;
		if (pressed){
			if((dir && left) || (!dir && right)){
				p.travel(-100);
				dir=!dir;
			}
			if (dir){
				p.rotateLeft();
			}
			else{
				p.rotateRight();
			}
		}
		else if (dir){
			p.arcForward(-200);
		}
		else{
			p.arcForward(200);
		}
	}

	public static void Kroete(Pilot p, TouchSensor touchLinks,
			TouchSensor touchRechts) {

		int dir = 0;
		TouchSensor touch;
		p.forward();

		while (!touchLinks.isPressed() && !touchRechts.isPressed())
			p.forward();
		dir = touchLinks.isPressed() ? -1 : 1;
		touch = (dir == -1) ? touchLinks : touchRechts;

		while (!Button.ESCAPE.isDown()) {
			boolean wall = touch.isPressed();
			while (!Button.ESCAPE.isDown() && wall == touch.isPressed()) {
				p.rotate(dir * 10 * (wall ? 1 : -1));
				if (!wall)
					p.travel(15);
			}
			if (wall)
				p.travel(60);
		}

	}

}
