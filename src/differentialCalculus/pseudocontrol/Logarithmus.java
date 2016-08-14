package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 07.08.2016.
 * this class implements a wrapper for the logarithm-function
 */
public class Logarithmus extends Function{
    /**
     * instantiate a new logarithm
     * @param f the inner functions, variable or constant
     */
    public Logarithmus(Function f){
        super(new Variable(),f);
    }

    /**
     * computes the value of a standard java Math.log(v)
     * @param v the x value
     * @return Math.log(v)
     */
    public double compute(double v){
        return Math.log(v);
    }

    /**
     * derivates the log and its inner functions
     * @return a derivative of log(x)
     */
    public Function derivative(){
        Function f2s = super.f2.derivative();
        double vf2s = f2s.getConstant().valueOf();
        if(f2s.isConstant()){
            if(vf2s == 0){
                return new Constant(0);
            }
            if(vf2s == 1){
                return new Divide(new Constant(1),super.f2);
            }
        }
        return new Multiply(new Divide(new Constant(1),super.f2),super.f2.derivative());
    }

    /**
     * if this log represents a constant value, this is returned with this getter.
     * to prevent exceptions, test if this log is constant with its method isConstant()
     * @return a constant, if this log is constant, null otherwise
     */
    public Constant getConstant(){
        if(isConstant()){
            return new Constant(Math.log(super.f2.getConstant().valueOf()));
        }
        return null;
    }

    @Override
    public boolean isConstant(){
        if(super.f2.isConstant()){
            return true;
        }
        return false;
    }

    public Logarithmus clone(){
        return new Logarithmus(super.f2.clone());
    }

    public String toString(){
        return "logE( "+super.f2.toString()+" )";
    }
}
