package differentialCalculus.control;
import differentialCalculus.pseudocontrol.*;
/**
 * Created by PhilippKroll on 13.08.2016.
 */
public class DifferentialCalculator {

    /**
     * computes a fast sinus, but exchanges precision for speed
     * @param x the value
     * @return a value, which is approximately sin(x)
     */
    public float fSin(float x){
        return (float)(x - (Math.pow(x,3)*0.1667d) + (Math.pow(x,5)*0.008334d) - (Math.pow(x,7)*0.0001984127d));
    }

    /**
     * sum from n = 0 to iteration of: (-1)^n * (x^2n+1)/(2n+1)!
     * @param x the value
     * @param iterations the amount of iterations to compute sin(x)
     * @return the result of sin(x) after "iterations" iterations
     */
    public float sin(float x,int iterations){
        float s = 0f;
        for (int i = 0;i < iterations+1;i++){
            int n = (2*i)+1;
            s += ((Math.pow(-1,i))*(((Double)Math.pow(x,n))/fak(n)));
        }
        return s;
    }

    /**
     * converts radiant into grad, with x*(180/Pi)
     * @param x the ratiant
     * @return the equivalent in grad
     */
    public float toGrad(float x){
        return (float)(x*57.29577951d);
    }

    /**
     * converts grad into radiant, with x*(Pi/180)
     * @param x the grade
     * @return the equivalent in radiant
     */
    public float toRad(float x){
        return (float)(x*0.01745329252d);
    }

    /**
     * computes a fast cosinus, but exchanges precision for speed
     * @param x the value
     * @return a value, which is approximately cos(x)
     */
    public float fcos(float x){
        return (float)(1 - (Math.pow(x,2)*0.5d) + (Math.pow(x,4)*0.041667d) - (Math.pow(x,6)*0.0013889d));
    }

    /**
     * sum from n = 0 to iteration of: (-1)^n * (x^2n)/2n!
     * @param x the value
     * @param iterations the amount of iterations to compute cos(x)
     * @return the result of cos(x) after "iterations" iterations
     */
    public float cos(float x,int iterations){
        float s = 0f;
        for (int i = 0;i< iterations; i++){
            int n = 2*i;
            s += ((Math.pow(-1,i))*(((Double)Math.pow(x,n))/fak(n)));
        }
        return s;
    }

    /**
     * computes the faculty of n like n * (n-1) * ... * 1
     * @param n the value
     * @return the faculty of n
     */
    public float fak(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        return n * fak(n-1);
    }

    /**
     * computes a fast tangens, but exchanges precision for speed
     * @param x the value
     * @return a value, which is approximately tan(x)
     */
    public float ftan(float x,int iterations){
        return fSin(x)/fcos(x);
    }

    /**
     * computes sin(x)/cos(x)
     * @param x the value
     * @param iterations the amount of iterations for sin and cos
     * @return tan(x)
     */
    public float tan(float x,int iterations){
        return sin(x,iterations)/cos(x,iterations);
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
