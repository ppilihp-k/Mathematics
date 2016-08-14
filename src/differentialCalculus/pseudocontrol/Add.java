package differentialCalculus.pseudocontrol;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

/**
 * Created by PhilippKroll on 06.08.2016.
 */
public class Add extends Function{

    public Add(Function f1,Function f2){
        super(f1,f2);
        /*f1 should be the constant function and f2 the variable one*/
        if(!f1.isConstant() && f2.isConstant()){
            Function t = f1;
            f1 = f2;
            f2 = t;
        }
        if(f1 instanceof Constant && f2 instanceof Add){
            if(f2.f1.isConstant() && !f2.f2.isConstant()){
                super.f1 = new Constant(f1.getConstant().valueOf()+f2.f1.getConstant().valueOf());
                super.f2 = f2.f2;
            }
            if(!f2.f1.isConstant() && f2.f2.isConstant()){
                super.f1 = new Constant(f1.getConstant().valueOf()+f2.f2.getConstant().valueOf());
                super.f2 = f2.f1;
            }
        }
        if(f1 instanceof Constant && f2 instanceof Subtract){
            if(f2.f1.isConstant() && !f2.f2.isConstant()){
                super.f1 = new Constant(f1.getConstant().valueOf()+f2.f1.getConstant().valueOf());
                super.f2 = f2.f2;
            }
            if(!f2.f1.isConstant() && f2.f2.isConstant()){
                super.f1 = new Constant(f1.getConstant().valueOf()-f2.f2.getConstant().valueOf());
                super.f2 = f2.f1;
            }
        }
    }

    public double compute(double v){
        return super.f1.compute(v) + super.f2.compute(v);
    }

    public Function derivative(){
        if(super.f1 instanceof Variable && super.f2 instanceof Variable){
            Function f1s = super.f1.derivative();
            Function f2s = super.f2.derivative();
            boolean f1sC = f1s.isConstant();
            boolean f2sC = f2s.isConstant();
            double vf1 = f1s.getConstant().valueOf();
            double vf2 = f2s.getConstant().valueOf();
            if(f2sC && f1sC){
                return new Constant(vf1+vf2);
            }
            return new Add(f1s,f2s);
        }
        if(super.f2.isConstant()){
            return super.f1.derivative();
        }
        if(super.f1.isConstant()){
            return f2.derivative();
        }
        if(super.f1.isConstant() && super.f2.isConstant()){
            return new Constant(0);
        }
        return new Add(super.f1.derivative(),super.f2.derivative());
    }

    public Constant getConstant(){
        if(isConstant()){
            return new Constant(((Constant)super.f1.getConstant()).valueOf()+((Constant)super.f2.getConstant()).valueOf());
        }
        return null;
    }

    public Add clone(){
        return new Add(super.f1.clone(),super.f2.clone());
    }

    public String toString(){
        return "( "+super.f1.toString()+" + "+super.f2.toString()+" )";
    }
}
