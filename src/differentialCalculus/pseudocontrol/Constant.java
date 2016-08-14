package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 06.08.2016.
 * this class represents a simple constant
 */
public class Constant extends Function{
    /**
     * the constant
     */
    private double constant;
    /**
     * instantiates a new constant
     * @param c the constant which is represented by this instance
     */
    public Constant(double c){
        super(null,null);
        constant = c;
    }

    /**
     * returns its attributes value
     * @return its attributes value
     */
    public double valueOf(){
        return constant;
    }

    /**
     * returns its attributes value
     * @param v
     * @return its attributes value
     */
    public double compute(double v){
        return constant;
    }

    /**
     * returns a 0-constant
     * @return a derivative for this constant
     */
    public Constant derivative(){
        return new Constant(0);
    }
    public String toString(){
        return constant+"";
    }

    /**
     * returns the value of the attribute "constant"
     * @return the attributes constants value
     */
    public Constant getConstant(){
        return this;
    }

    /**
     * returns true
     * @return true
     */
    @Override
    public boolean isConstant(){
        return true;
    }

    /**
     * checks, if f is a constant and equal to its attribute
     * @param f, the other function, to test against
     * @return true, if f is a constant and f.constant == "constant"
     */
    @Override
    public boolean equals(Function f){
        if(f instanceof Constant){
            return constant == ((Constant) f).valueOf();
        }
        return false;
    }

    public Function integrate(){
        return new Multiply(new Constant(constant),new Variable());
    }

    public Constant clone(){
        return new Constant(constant);
    }
}
