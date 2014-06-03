package line;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import test.Pilot;

public class Linienfolger_alt {

	Pilot p;
	LightSensor ls;
	double radius;
	int average, min = Integer.MAX_VALUE, max = 0;

	public Linienfolger_alt() {
		p = new Pilot();
		p.setRotateSpeed(60);
		p.setTravelSpeed(60);
		radius = 30;
		average = 400;
		ls = new LightSensor(SensorPort.S1);
		ls.setFloodlight(true);
	}

	public static void main(String args[]) throws Exception {
		Linienfolger_alt la = new Linienfolger_alt();

		
		int turn;
		int cTurn;
		int bTurn;
		int color;
		int power = 22;
		int threshold;

		while (!Button.ESCAPE.isDown()) {
			int value = la.ls.readNormalizedValue();
			if (value < la.min)
				la.min = value;
			if (value > la.max)
				la.max = value;
			if(la.max==la.min)
				la.max++;
			la.average = (la.min + la.max) / 2;
			threshold = (la.max + la.min) / 2;
			color = la.average;
			cTurn = power - 50 * (threshold - color) / (la.max - la.min);
			la.p.arcForward(cTurn);
			Thread.sleep(10);
		}

	}

}
