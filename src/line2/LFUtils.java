package line2;

public class LFUtils {

	private static final int NUMBER_OF_SAMPLES = 20;

	public LFUtils() {
	}

	public static int getAvgLightValue() {

		int sum = 0;
		for (int i = 0; i < NUMBER_OF_SAMPLES; i++) {
			sum += LFJfedor.lightSensor.getLightValue();
		}
		return sum / NUMBER_OF_SAMPLES;
	}

}