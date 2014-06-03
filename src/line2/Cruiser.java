package line2;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;

public class Cruiser extends Thread {

	NXTMotor mB = new NXTMotor(MotorPort.B);
	NXTMotor mC = new NXTMotor(MotorPort.C);
	int color;
	int turn;
	int cTurn;
	int bTurn;
	int power = 22;
	int threshold;

	public Cruiser() {
	}

	public void run() {

		LCD.clear();
		LCD.drawString("Started Cruiser", 0, 2);

		while (!Button.ESCAPE.isPressed()) {
			threshold = (LFJfedor.lvh.getBlack() + LFJfedor.lvh.getWhite()) / 2;
			color = LFUtils.getAvgLightValue();
			cTurn = power - 50 * (threshold - color) / (LFJfedor.lvh.getWhite() - LFJfedor.lvh.getBlack());
			mC.setPower(cTurn);
			mC.forward();

			bTurn = power + 50 * (threshold - color) / (LFJfedor.lvh.getWhite() - LFJfedor.lvh.getBlack());
			mB.setPower(bTurn);
			mB.forward();

		}
	}

}
