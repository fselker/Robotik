package regler;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.comm.NXTConnection;
import test.Pilot;

public class LinienFolger implements SensorPortListener {

	private int weiss, schwarz, sollwert;
	private LightSensor ls;
	double speed;
	private Pilot p;
	private PRegler regler;
	private double baseSpeed = 100, breakFactor = 1, kp = 10;
	UltrasonicSensor us;

	NXTConnection nxt;

	public void run() {
		int aPower = 0, bPower = 0;
		double x = 0, w = 0, y = 0;
		p.rotate(360); // bestimmung der miniMotor.Al und Motor.AxiMotor.Al
						// werte
		w = sollwert;
		regler.setSollWert(w);
		while (!Button.ESCAPE.isDown()) {

			x = ls.getLightValue();

			y = regler.getValue(x);

			aPower = ((int) (speed*(baseSpeed - breakFactor * Math.abs(w - x) + y)));
			bPower = ((int) (speed*(baseSpeed - breakFactor * Math.abs(w - x) - y)));
			Motor.A.setSpeed(aPower);
			Motor.B.setSpeed(bPower);

			Motor.A.forward();
			Motor.B.forward();
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {

			}
		}

	}

	public static void main(String args[]) {
		PRegler pr = new PRegler();
		LinienFolger lf = new LinienFolger(pr);
		lf.run();

	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {

		if (SensorPort.S1.equals(aSource)) {// Signal kommt vom Lichtsensor
			aNewValue = ls.getLightValue();
			if (aNewValue < schwarz)
				schwarz = aNewValue;
			else if (aNewValue > weiss)
				weiss = aNewValue;
			sollwert = (schwarz + weiss) / 2;
			regler.setSollWert(sollwert);
		}
		if(us.getDistance()<15)
			speed=0.1;
		else
			speed=1;//=us.getDistance()/255+0.1;
System.out.println(speed);
	}

	public LinienFolger(PRegler r) {
		ls = new LightSensor(SensorPort.S1);
		weiss = 0;
		schwarz = Integer.MAX_VALUE;
		p = new Pilot();
		ls.setFloodlight(true);
		p.setRotateSpeed(80);
		p.setTravelSpeed(80);
		us = new UltrasonicSensor(SensorPort.S2);
		SensorPort.S1.addSensorPortListener(this);
		SensorPort.S2.addSensorPortListener(this);
		regler = r;
		double par[] = { kp };
		r.setParameter(par);
		System.out.println("Warte auf Verbindung");
		//nxt = USB.waitForConnection(0, NXTConnection.PACKET);
		//ConnectionListener cl = new ConnectionListener(nxt, this);
		//Thread t = new Thread(cl);
		//t.start();
		System.out.println("Verbindung hergestellt");

	}

	public void setParameter(int pos, double value) {
		switch (pos) {
		case 0:
			kp = value;
			regler.k_p = kp;
			System.out.println("Kp gesetzt auf: "+value);
			break;
		case 1:
			baseSpeed = value;
			System.out.println("Speed gesetzt auf: "+value);
			break;
		case 2:
			breakFactor=value;
			System.out.println("BF gesetzt auf: "+value);
			break;
		default:
			System.out.println("Ungueltige Position empfangen: "+pos);
		}
	}
}
