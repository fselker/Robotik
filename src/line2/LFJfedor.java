package line2;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;

public class LFJfedor {

	static LineValueHolder lvh = new LineValueHolder();
	static LightSensor lightSensor = new LightSensor(SensorPort.S1);

	public LFJfedor() throws InterruptedException {

		waitForUser("white");
		lvh.setWhite(getThreshold());
		waitForUser(null);

		waitForUser("black");
		lvh.setBlack(getThreshold());
		waitForUser(null);

	}

	private synchronized void waitForUser(String message) throws InterruptedException {
		if (message != null) {
			LCD.drawString(message, 0, 2, false);
		}
		Sound.twoBeeps();
		Button.ESCAPE.waitForPressAndRelease();
	}

	private int getThreshold() {
		LFUtils calib = new LFUtils();
		int value = calib.getAvgLightValue();
		LCD.drawInt(value, 4, 0, 3);
		return value;
	}

	private void initialize() {
		Thread cruiser = new Thread(new Cruiser());
		cruiser.start();
	}

	public static void main(String[] args) throws InterruptedException {

		LFJfedor lfJfedor = new LFJfedor();
		lfJfedor.initialize();
	}
}