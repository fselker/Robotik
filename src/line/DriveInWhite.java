package line;


public class DriveInWhite extends MyBehavior {
	int dreh=2;
	public DriveInWhite(Linienfolger lf) {
		super(lf);
	}

	@Override
	public boolean takeControl() {
		return lf.found &&lf.getColour()==Linienfolger.area.white;
	}

	@Override
	public void action() {
		System.out.println(lf.ls.getNormalizedLightValue());
		lf.p.rotate(dreh*lf.dir);

		dreh*=2;
		lf.dir*=-1;
	}

	@Override
	public void suppress() {
		//lf.p.stop();
		dreh=2;
		lf.dir*=-1;
//		System.out.println("supress white");
	}

}
