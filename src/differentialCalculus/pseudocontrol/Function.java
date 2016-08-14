package differentialCalculus.pseudocontrol;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import utils.Tupel;

/**
 * Created by PhilippKroll on 06.08.2016.
 * this class represents a superclass for any function, one want to extends.
 * it provides methods to derivate, test subfunctions to equality, test, if the values stored in this class are constant
 * and a method to compute a value f(x)
 */
public abstract class Function {

    /**
     * the left hand side of the operator
     */
    protected Function f1;
    /**
     * the right hand side of the operator
     */
    protected Function f2;

    public Function(Function f1,Function f2){
        this.f1 = f1;
        this.f2 = f2;
    }

    /**
     * getter for the stored functions
     * @return
     */
    public Tupel<Function,Function> getFunctions(){
        Tupel<Function,Function> t = new Tupel<>();
        t.first = this.f1;
        t.second = this.f2;
        return t;
    }

    /**
     * setter for the stored functions
     * @param f1
     * @param f2
     */
    public void setFunctions(Function f1, Function f2){
        if(f1 != null){
            this.f1 = f1;
        }
        if(f2 != null){
            this.f2 = f2;
        }
    }

    /**
     * a method for computing the derivative of this function, each subclass schould implement it by itselve
     * @return
     */
    public abstract Function derivative();

    /**
     * computes the value of f(x)
     * @param v
     * @return
     */
    public abstract double compute(double v);

    /**
     * checks wether this function is constant or not
     * @return true, if the function is constant, false otherwise
     */
    public boolean isConstant(){
        return  f1.isConstant() && f2.isConstant();
    }

    /**
     * if the function was constant, this should implement the getter for the constant value
     * @return
     */
    public abstract Constant getConstant();

    /**
     * test, if two functions compute the same value
     * @param f, the other function, to test against
     * @return true, if the functions equal, false otherwise
     */
    public boolean equals(Function f){
        return f1.equals(f.f1) && f2.equals(f.f2);
    }

    @Override
    public abstract Function clone();

    /**
     * this method is not implemented yet...
     * @return
     */
    public Function integrate(){
        /*will be implemented, if needed*/
        return null;
    }

    public boolean isFunction(){
        return !(f1 instanceof Constant && f2 instanceof Constant);
    }
    public void substitute(Function f){
        if(f1 instanceof Variable){
            f1 = f;
        }
        if(f2 instanceof Variable){
            f2 = f;
        }
        if(!(f1 instanceof Constant)){
            f1.substitute(f);
        }
        if(!(f2 instanceof Constant)){
            f2.substitute(f);
        }
    }
}
