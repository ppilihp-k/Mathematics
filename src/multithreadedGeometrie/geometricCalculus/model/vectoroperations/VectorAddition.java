package multithreadedGeometrie.geometricCalculus.model.vectoroperations;


import singlethreadedGeometrie.geometricCalc.model.Vector;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class VectorAddition {
    public Vector add(Vector v1, Vector v2){
        int n1 = v1.length();
        int n2 = v2.length();
        if (n1 != n2) {
            throw new IllegalArgumentException();
        }
        Vector v3 = new Vector(n1);
        for (int i = 0; i < n1; i++) {
            v3.set(i, v1.get(i) + v2.get(i));
        }
        return v3;
    }

    public Vector[] add(Vector ... v){
        if(v.length % 2 != 0){
            throw new IllegalArgumentException();
        }
        Vector[] verts = new Vector[v.length/2];
        int index = 0;
        for (int i = 0;i < v.length;i+=2){
            if(v[i].length() == v[i+1].length()){
                verts[index] = add(v[i],v[i+1]);
                index++;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return verts;
    }
}
