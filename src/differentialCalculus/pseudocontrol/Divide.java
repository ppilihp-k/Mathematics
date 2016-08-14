package differentialCalculus.pseudocontrol;

/**
 * Created by PhilippKroll on 06.08.2016.
 * this class represents a division of its subfunctions,constants or variables
 */
public class Divide extends Function{
    /**
     * instantiates a new divide
     * @param f1 the lefthand-side subfunction of the operator
     * @param f2 the righthand-side subfunction of the operator
     */
    public Divide(Function f1,Function f2){
        super(f1,f2);
    }

    /**
     * computes the value of f1(v)/f2(v);
     * @param v the x value
     * @return f1(v)/f2(v)
     */
    public double compute(double v){
        double value1 = super.f1.compute(v);
        double value2 = super.f2.compute(v);
        if(value2 == 0){
            return Double.NaN;
        }
        return value1 / value2;
    }

    /**
     * derivates this instance
     * @return a derivative ot a division
     */
    public Function derivative(){
        if(super.f1 instanceof Variable && super.f2 instanceof Variable){
            return new Constant(0);
        }
        if(super.f1 instanceof Variable && super.f2 instanceof Constant){
            double fvs = super.f2.getConstant().valueOf();
            if(fvs == 0){
                return new Constant(Double.NaN);
            }
            return new Constant(1/((Constant) super.f2).valueOf());
        }
        if(super.f1.isConstant() && super.f2 instanceof Variable){
            return (new Multiply(new Constant(super.f1.getConstant().valueOf()),new Exponentiate(super.f2,new Constant(-1)))).derivative();
        }
        Function fs = super.f1.derivative();
        Function gs = super.f2.derivative();
        Exponentiate fExpTwo = new Exponentiate(this.clone(),new Constant(2));
        return new Divide(new Subtract(new Multiply(gs,super.f1),new Multiply(fs,super.f2)),fExpTwo);
    }
    public Function integrate(){
        if(super.f1.derivative().equals(super.f2)){
            return new Logarithmus(super.f2.clone());
        }
        return (new Multiply(super.f1.clone(),new Divide(new Constant(1),super.f2.clone()))).integrate();
    }
    /**
     * if this function represents a constant value, this returns it.
     * before use and to prevent exceptions, test with isConstant(), if this function represents a constant value.
     * @return a constant value, if this function represents one, null otherwise
     */
    public Constant getConstant(){
        if(isConstant()){
            return new Constant(((Constant)super.f1.getConstant()).valueOf()/((Constant)super.f2.getConstant()).valueOf());
        }
        return null;
    }

    public Divide clone(){
        return new Divide(super.f1.clone(),super.f2.clone());
    }

    public String toString(){
        return "( "+super.f1.toString()+" / "+super.f2.toString()+" )";
    }
}
