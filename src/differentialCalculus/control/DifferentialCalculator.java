package differentialCalculus.control;
import differentialCalculus.pseudocontrol.*;
/**
 * Created by PhilippKroll on 13.08.2016.
 */
public class DifferentialCalculator {

    /**
     * constant for computing the equivalent for a grad value in radiant
     */
    public static final double GRADTORAD = Math.PI/180;
    /**
     * constant for computing the equivalent for a radiant value in a grad value
     */
    public static final double RADTOGRAD = 180/Math.PI;

    public static final double PI = Math.PI;

    public DifferentialCalculator(){

    }

    /**
     * this method computes the sin value for grad-values, therefore calculates the equivalent in
     * rad and then starts the computation.
     * the computation runs through 5 iteration of the taylor-series:
     * sum from n = 0 to iteration of: (-1)^n * (x^2n+1)/(2n+1)!
     * @param x the value
     * @return the result of sin(x) in degrees
     */
    public float sin(float x){
        x = x % 360;
        if(x == 0 || x == 180){
            return 0f;
        }
        if(x == 90){
            return 1;
        }
        if(x == 270){
            return -1;
        }
        x = (toRad(x));
        double s = x;
        for (int i = 1;i < 10;i++){
            int n = (2*i)+1;
            double nominator = Math.pow(x,n);
            double denominator = fac(n);
            if(i % 2 == 0){
                s += (nominator/denominator);
            } else {
                s -= (nominator/denominator);
            }
        }
        return (float)s;
    }

    /**
     * converts radiant into grad, with x*(180/Pi)
     * @param x the ratiant
     * @return the equivalent in grad
     */
    public float toGrad(float x){
        return (float)(x*RADTOGRAD);
    }

    /**
     * converts grad into radiant, with x*(Pi/180)
     * @param x the grade
     * @return the equivalent in radiant
     */
    public float toRad(float x){
        return (float)(x*GRADTORAD);
    }

    /**
     * this method computes the cos value for grad-values, therefore calculates the equivalent in
     * rad and then starts the computation.
     * the computation runs through 5 iteration of the taylor-series:
     * sum from n = 0 to iteration of: (-1)^n * (x^2n)/2n!
     * @param x the value
     * @return the result of cos(x) in degrees
     */
    public float cos(float x){
        x = x % 360;
        if(x == 0 || x == 270){
            return 1;
        }
        if(x == 90){
            return 0;
        }
        if(x == 180){
            return -1;
        }
        x = toRad(x);
        float s = 1;
        for (int i = 1;i <= 10; i++){
            int n = 2*i;
            float nominator = (float)Math.pow(x,n);
            float denominator = fac(n);
            if(s % 2 == 0){
                s += nominator/denominator;
            } else {
                s -= nominator/denominator;
            }
        }
        return s;
    }

    /**
     * computes the faculty of n like n * (n-1) * ... * 1
     * @param n the value
     * @return the faculty of n
     */
    public int fac(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        return n * fac(n-1);
    }

    /**
     * this method computes the tangens for a grad value
     * computes sin(x)/cos(x)
     * @param x the value
     * @return tan(x)
     */
    public double tan(int x){
        return sin(x)/cos(x);
    }

    /**
     * computes the arccos in degrees, this method isn't much precise... there is a error of ~9°
     * for precise computation use the arcsin of the java Math class
     * this method is computed with:
     * 90 - asin(x)
     * @param x
     * @return the arccos in degrees
     */
    public double acos(int x){
        double asin = asin(x);
        return (90 - asin);
    }

    /**
     * computes the arcsin in degrees, this method isn't much precise... there is a error of ~9°
     * for precise computation use the arcsin of the java Math class
     * this method uses the binominal taylor-series:
     * sum from k=0 to "infinity" of (2k over k) * (x^(2k+1))/(4^k * (2k+1))
     * @param x
     * @return the arcsin in degrees
     */
    public double asin(float x){
        if(x < -1 || x > 1){
            throw new IllegalArgumentException();
        }
        double sin = 0f;
        for (int i = 0;i < 1000;i++){
            int n = (2*i)+1;
            int biNoCo = binominalCoefficient(2*i,i);
            double nominator = Math.pow(x,n);
            double denominator = (float)(Math.pow(4,i)*n);
            sin += (biNoCo * (nominator/denominator));
        }
        System.out.println(sin);
        return toGrad((float)sin);
    }
    public float atan(float x){
        float atan = 0f;
        for (int i = 0;i < 100;i++){
            int n = (2*i)+1;
            float nominator = (float)Math.pow(x,n);
            if(i % 2 == 0){
                atan += (nominator/n);
            } else {
                atan -= (nominator/n);
            }
        }
        return toGrad(atan);
    }

    /**
     * the method computes the binominal coefficient for n over k
     * @param n
     * @param k
     * @return the amount of k sized subsets of a n sized set
     */
    public int binominalCoefficient(int n,int k){
        if(n < 0)
            throw new IllegalArgumentException();
        if(n == 0 && k > 0){
            throw new IllegalArgumentException();
        }
        if(n == k){
            return 1;
        }
        if(k <= 0) {
            return 1;
        } else if(2*k > n){
            return binominalCoefficient(n,n-k);
        }
        int res = n - k + 1;
        for (int i = 2;i <= k;i++){
            res = (res * (n - k + i));
            res = (res / i);
        }
        return res;
    }

    /**
     * computes the zeros of f with newtons method x(n+1) = x(n) - f(x)/f'(x)
     * this method finds one zero of f
     * for better results, but slower computation,
     * @param f the function to analyse choose e as little as possioble, on the other hand for fast computation and little precision
     *          choose e as next to 1 as possible
     * @param e a parameter to determine the precision e should be element of [0,1]
     * @return the zero of f with precision <= e
     */
    public float newtonsMethod(Function f,float e){
        if(e < 0 ||e > 1){
            return Float.NaN;
        }
        return pNewtonsMethod(f,0,e,30);
    }

    /**
     * this is a method, to help prevent oszillations and endless loops
     * @param f the function
     * @param x the startvaule
     * @param e a factor to determine precision
     * @param iterations the amount of iterations to find a result
     * @return the zero of f
     */
    private float pNewtonsMethod(Function f,float x,float e,int iterations){
        if(iterations == 0){
            return Float.NaN;
        }
        float xn = (float)(f.compute(x)/f.derivative().compute(x));
        if(x - xn < e){
            return x;
        }
        return x - (pNewtonsMethod(f,(float)xn,e,iterations--));
    }
}
