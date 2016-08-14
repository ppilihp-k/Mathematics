package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 06.08.2016.
 * this class represents a exp-function
 */
public class Exponentiate extends Function{
    /**
     * instantiates a new exp-function f1^f2
     * @param f1 the "base"
     * @param f2 the exponent
     */
    public Exponentiate(Function f1,Function f2){
        super(f1,f2);
    }

    /**
     * computes the function value f1^f2
     * @param v the x value
     * @return f1(v)^f2(v)
     */
    public double compute(double v){
        return Math.pow(super.f1.compute(v),super.f2.compute(v));
    }
    public Function derivative(){
        if(super.f1 instanceof Variable && super.f2 instanceof Variable){
            return null;
        }
        if(super.f1 instanceof Constant && super.f2 instanceof Variable){
            if(super.f1.getConstant().valueOf() == 0){
                return new Constant(0);
            }
            if(super.f1.getConstant().valueOf() == 1){
                return new Constant(1);
            }
            return new Multiply(new Euler(new Multiply(new Logarithmus(new Constant(super.f1.getConstant().valueOf())),super.f2)),new Logarithmus(new Constant(super.f1.getConstant().valueOf())));
        }
        if(super.f1 instanceof Variable && super.f2.isConstant()){
            Function newCons = new Constant(super.f2.compute(0)-1);
            Function cons = super.f2;
            if(((Constant)super.f2).valueOf() == 0){
                return new Constant(0);
            }
            if(((Constant)newCons).valueOf() == 0){
                return new Constant(1);
            }
            return new Multiply(cons,new Exponentiate(super.f1,newCons));
        }
        if(super.f2.isConstant() && super.f1 instanceof Function && !(super.f1 instanceof Constant) && !(super.f1 instanceof Variable)){
            Constant f = super.f2.getConstant();
            if(f.valueOf() == 0){
                return new Constant(0);
            }
            return new Multiply(new Multiply(f,new Exponentiate(super.f1,new Constant(f.valueOf()-1))),super.f1.derivative());
        }
        return null;
    }

    /**
     * if this function represents a constant value, this returns it.
     * before use and to prevent exceptions, test with isConstant(), if this function represents a constant value.
     * @return a constant value, if this function represents one, null otherwise
     */
    public Constant getConstant(){
        if(isConstant()){
            return new Constant(Math.pow(((Constant)super.f1.getConstant()).valueOf(),((Constant)super.f2.getConstant()).valueOf()));
        }
        return null;
    }

    public Exponentiate clone(){
        return new Exponentiate(super.f1.clone(),super.f2.clone());
    }

    public String toString(){
        return "( "+super.f1.toString()+" ^ "+super.f2.toString()+" )";
    }
}
