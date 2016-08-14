package differentialCalculus.pseudocontrol;

import java.util.Random;

/**
 * Created by PhilippKroll on 06.08.2016.
 * this class represents a simple multiply of two subfunctions,constants or variables
 */
public class Multiply extends Function{
    /**
     * instantiates a new multiply
     * @param f1 the function on the lefthand-side of the operator
     * @param f2 the function on the righthand-side of the operator
     */
    public Multiply(Function f1,Function f2){
        super(f1,f2);
        if((!(f1 instanceof Constant) || !(f1 instanceof Variable) || !(f2 instanceof Constant) || !(f2 instanceof Variable))) {
            if(f1.isConstant() && !f2.isConstant()){
                if(f2.f1 != null && f2.f1.isConstant()){
                    super.f1 = new Constant(f1.getConstant().valueOf()*f2.f1.getConstant().valueOf());
                    super.f2 = f2.f2;
                } else if (f2.f2 != null && f2.f1.isConstant()){
                    super.f1 = new Constant(f1.getConstant().valueOf()*f2.f2.getConstant().valueOf());
                    super.f2 = f2.f1;
                }
            } else if(!f1.isConstant() && f2.isConstant()) {
                if(f1.f1 != null && f1.f1.isConstant()){
                    super.f1 = new Constant(f2.getConstant().valueOf()*f1.f1.getConstant().valueOf());
                    super.f2 = f1.f2;
                } else if(f1.f2 != null && f1.f2.isConstant()){
                    super.f1 = new Constant(f2.getConstant().valueOf()*f1.f2.getConstant().valueOf());
                    super.f2 = f1.f1;
                }
            }
        }
    }

    /**
     * computes the value multiply(v) = f1(v)*f2(v)
     * @param v the x value
     * @return f1(v)*f2(v)
     */
    public double compute(double v){
        return super.f1.compute(v) * super.f2.compute(v);
    }

    /**
     * derivates this function
     * @return a derivative for this function
     */
    public Function derivative(){
        Function f1s = super.f1.derivative();
        Function f2s = super.f2.derivative();
        if(f1s.isConstant() && f1s.getConstant().valueOf() == 0){
            return new Multiply(super.f1,f2s);
        } else if(f2s.isConstant() && f2s.getConstant().valueOf() == 0){
            return new Multiply(f1s,super.f2);
        }
        if(f1s.isConstant() && f1s.getConstant().valueOf() == 1){
            return new Add(super.f2.clone(),new Multiply(super.f1.clone(),f2s));
        } else if(f2s.isConstant() && f2s.getConstant().valueOf() == 1){
            return new Add(new Multiply(f1s,super.f2.clone()),super.f1.clone());
        }
        Function f2clone = super.f2.clone();
        Function f1clone = super.f1.clone();
        Function fsg;
        Function fgs;
        if(f1s.isConstant() && f2clone.f1.isConstant()){
            fsg = new Multiply(new Constant(f1s.getConstant().valueOf()*f2clone.f1.getConstant().valueOf()),f2clone.f2);
        } else {
            fsg = new Multiply(f1s,f2clone);
        }
        if(f2s.isConstant() && f1clone.f1.isConstant()){
            fgs = new Multiply(new Constant(f2s.getConstant().valueOf()*f1clone.f1.getConstant().valueOf()),f1clone.f2);
        } else {
            fgs = new Multiply(f2s,f1clone);
        }
        Function f = new Add(fsg,fgs);
        return f;
    }


    /**
     * if this function represents a constant value, this returns it.
     * before use and to prevent exceptions, test with isConstant(), if this function represents a constant value.
     * @return a constant value, if this function represents one, null otherwise
     */
    public Constant getConstant(){
        if(isConstant()){
            return new Constant(((Constant)super.f1.getConstant()).valueOf()*((Constant)super.f2.getConstant()).valueOf());
        }
        return null;
    }

    public Multiply clone(){
        return new Multiply(super.f1.clone(),super.f2.clone());
    }

    public String toString(){
        if(super.f1 != null && super.f2 != null){
            return "( "+super.f1.toString()+" * "+super.f2.toString()+" )";
        } else {
            return "Fehler";
        }
    }
}
