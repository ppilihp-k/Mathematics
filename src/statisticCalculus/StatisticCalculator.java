package statisticCalculus;

public class StatisticCalculator {

	public double variance(int[] a){
		int n = a.length;
		double m = median(a);
		double s = 0;
		for (int i = 0; i < n; i++) {
			s += (Math.pow((a[i] - m), 2));
		}
		if(s != 0){
			return s / (n - 1);
		}
		return 0;
	}
	
	public double meanQuadraticDeviation(int[] a){
		int n = a.length;
		double m = median(a);
		double s = 0;
		for (int i = 0; i < n; i++) {
			s += (Math.pow((a[i] - m), 2));
		}
		if(s != 0){
			return s / n;
		}
		return 0;
	}
	
	public int absolutFrequency(int[] a,int f){
		int c = 0;
		for (int i = 0; i < a.length; i++) {
			if(a[i] == f){
				c++;
			}
		}
		return c;
	}
	
	public float relativeFrequency(int[] a, int f){
		int n = a.length;
		int c = 0;
		for (int i = 0; i < n; i++) {
			if(a[i] == f){
				c++;
			}
		}
		return c / n;
	}
	
	public double median(int[] a){
		double sum = 0;
		int n = a.length;
		for (int i = 0; i < a.length; i++) {
			int ai = a[i];
			if(ai != 0){
				sum += (a[i]/n);
			}
		}
		return sum;
	}
}
