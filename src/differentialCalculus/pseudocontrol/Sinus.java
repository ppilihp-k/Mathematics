package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 07.08.2016.
 */
public class Sinus extends Function {

    public Sinus(Function f){
        super(null,f);
    }

    public double compute(double v){
        return Math.sin(super.f2.compute(v));
    }

    public Function derivative(){
        if(super.f2 instanceof Variable){
            return new Cosinus(new Variable());
        }
        if(super.f2.isConstant()){
            return new Constant(0);
        }
        return new Multiply(new Cosinus(super.f2),super.f2.derivative());
    }

    public Function integrate(){
        if(super.f2 instanceof Variable){
            return new Subtract(new Constant(0),new Cosinus(new Variable()));
        }
        if(super.f2.isConstant()){
            return new Multiply(new Constant(Math.sin(super.f2.getConstant().valueOf())),new Variable());
        }
        return null;
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

    public Sinus clone(){
        return new Sinus(super.f2.clone());
    }

    public String toString(){
        return "sin( "+super.f2.toString()+" )";
    }
}
