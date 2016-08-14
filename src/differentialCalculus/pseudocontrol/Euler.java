package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 07.08.2016.
 * this class represents a exp-function in case the base is the euler-number
 */
public class Euler extends Exponentiate {
    /**
     * instantiates a new euler-exp-function
     * @param f the inner function e^f
     */
    public Euler(Function f){
        super(new Constant(Math.E),f);
    }

    /**
     * computes the y value with Math.pow(Math.E,f(v))
     * @param v the x value
     * @return the e^f(v) value
     */
    public double compute(double v){
        return Math.pow(Math.E,super.f2.compute(v));
    }

    /**
     * derivates this function
     * @return a derivative for this function
     */
    public Function derivative(){
        return new Multiply(new Exponentiate(new Constant(Math.E),super.f2),super.f2.derivative());
    }

    /**
     * in case this function represents a constant number, this method returns it, to prevent exceptions, test with isConstant(),
     * if this function is constant
     * @return a constant, if this function represents a constant, null otherwise
     */
    public Constant getConstant(){
        if(isConstant()){
            return new Constant(((Constant)super.f1.getConstant()).valueOf()-((Constant)super.f2.getConstant()).valueOf());
        }
        return null;
    }

    public Euler clone(){
        return new Euler(super.f2.clone());
    }

    public Function integrate(){
        return null;
    }

    public String toString(){
        return "e^( "+super.f2.toString()+" )";
    }
}
