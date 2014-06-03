package regler;

public class PRegler extends Regler{
	
	public double k_p=1;

	@Override
	public double getValue(double x) {
		double diff =(x-sollwert);
		return diff*k_p;
	}

	@Override
	public void setParameter(double[] par) {
		if(par.length!=1)
			return;
		k_p= par[0];
			
		
	}

}
