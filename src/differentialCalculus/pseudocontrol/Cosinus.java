package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 07.08.2016.
 */
public class Cosinus extends Function{

    public Cosinus(Function f){
        super(null,f);
    }

    public double compute(double v){
        return Math.cos(super.f2.compute(v));
    }

    public Function derivative(){
        if(super.f2 instanceof Variable){
            return new Subtract(new Constant(0),new Sinus(new Variable()));
        }
        if(super.f2.isConstant()){
            return new Constant(0);
        }
        return new Multiply(new Subtract(new Constant(0),new Sinus(super.f2)),super.f2.derivative());
    }

    public Function integrate(){
        return new Multiply(new Divide(new Constant(1),super.f2.derivative()),new Subtract(new Constant(0),new Cosinus(super.f2.clone())));
    }

    public Constant getConstant(){
        if(isConstant()){
            return new Constant(super.f2.getConstant().valueOf());
        }
        return null;
    }

    @Override
    public boolean isConstant(){
        return super.f2.isConstant();
    }

    public Cosinus clone(){
        return new Cosinus(super.f2.clone());
    }

    public String toString(){
        return "cos( "+super.f2.toString()+" )";
    }
}
