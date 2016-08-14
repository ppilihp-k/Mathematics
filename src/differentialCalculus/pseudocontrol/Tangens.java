package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 07.08.2016.
 */
public class Tangens extends Function{

    public Tangens(Function f){
        super(null,f);
    }

    public double compute(double v){
        return Math.tan(super.f2.compute(v));
    }

    public Function derivative(){
        if(super.f2 instanceof Variable){
            return new Divide(new Constant(1),new Exponentiate(new Cosinus(new Variable()),new Constant(2)));
        }
        if(super.f2.isConstant()){
            return new Constant(0);
        }
        return new Multiply(new Divide(new Constant(1),new Exponentiate(new Cosinus(new Variable()),new Constant(2))),super.f2.derivative());
    }

    public Function integrate(){
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

    public Tangens clone(){
        return new Tangens(super.f2.clone());
    }

    public String toString(){
        return "sin( "+super.f2.toString()+" )";
    }
}
