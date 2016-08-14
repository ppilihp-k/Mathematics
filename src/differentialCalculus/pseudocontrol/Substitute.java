package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 10.08.2016.
 */
public class Substitute extends Function{

    public Substitute(Function f){
        super(null,f);
    }

    public void setSubstitute(Function f){
        super.f2 = f;
    }

    public Function getSubstitute(){
        return super.f2;
    }

    public boolean hasContent(){
        return super.f2 != null;
    }

    public Constant getConstant(){
        return null;
    }

    public double compute(double v){
        if(super.f2 != null){
            return super.f2.compute(v);
        }
        return Double.NaN;
    }

    public Function derivative(){
        Function fs = super.f2.derivative();
        return fs;
    }


    public Function clone(){
        return new Substitute(super.f2.clone());
    }
}
