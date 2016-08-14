package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 06.08.2016.
 * this class represents a variable
 */
public class Variable extends Function{
    /**
     * instantiates a new variable
     */
    public Variable(){
        super(null,null);
    }

    /**
     * returns what u put in
     * @param v the x value
     * @return the x value
     */
    public double compute(double v){
        return v;
    }

    /**
     * derivates this variable
     * @return a constant, representing 1
     */
    public Function derivative(){
        return new Constant(1);
    }


    /**
     * because this is a variable, this returns false
     * @return false
     */
    public boolean isConstant(){
        return false;
    }
    /**
     * if this function represents a constant value, this returns it.
     * before use and to prevent exceptions, test with isConstant(), if this function represents a constant value.
     * @return a constant value, if this function represents one, null otherwise
     */
    public Constant getConstant(){
        return null;
    }

    public String toString(){
        return "x";
    }

    /**
     * checks, if this instance is equal to f
     * @param f, the other function, to test against
     * @return true, if f is variable, false otherwise
     */
    @Override
    public boolean equals(Function f){
        if(f instanceof Variable){
            return true;
        }
        return false;
    }

    public Variable clone(){
        return new Variable();
    }
}
