import differentialCalculus.control.DifferentialCalculator;
import graph.model.Graph;
import graph.GraphCalculator;

import java.util.LinkedList;

/**
 * Created by PhilippKroll on 05.08.2016.
 */
public class test {
    public static void main(String[] args){
        Graph g = new Graph(false);
        g.addNode();
        g.addNode();
        g.addNode();
        g.addEdge(1,2);
        System.out.println(g);
        System.out.println("hasedge:"+g.hasEdge(1,2));
        System.out.println("hasedge:"+g.hasEdge(2,1));
        System.out.println("hasnoedge:"+g.hasEdge(1,0));
        g.addNode("test");
        g.addEdge(2,3);
        g.addNode();
        g.addEdge(3,4);
        g.addEdge(0,1);
        System.out.println(g);
        GraphCalculator gc = new GraphCalculator();
        System.out.println(g);
        System.out.println("is connected: "+gc.isConnected(g));
        gc.breathFirstSearch(g,1);
        System.out.println(g.showBreathFirstSearchInfo());
        gc.depthFirstSearch(g);
        System.out.println(g.showDepthFirstSearchInfo());
        g.removeEdge(3,4);
       // g.removeEdge(2,3);
        System.out.println(g);
        System.out.println("is connected: "+gc.isConnected(g));
        gc.breathFirstSearch(g,1);
        System.out.println(g.showBreathFirstSearchInfo());
        int[] a = gc.topologicalSort(g);
        System.out.println(g);
        System.out.println(g.showDepthFirstSearchInfo());
        for (int i:a) {
            System.out.print(i+",");
        }
        System.out.println("\nClique:");
        g.addEdge("test",1);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(gc.clique(list,g));
        list.clear();
        list.add(0);
        list.add(4);
        System.out.println(gc.clique(list,g));
        list.clear();
        System.out.println(g);
        boolean b = gc.kClique(g,3);
        System.out.println(b);

        DifferentialCalculator dc = new DifferentialCalculator();
        System.out.println("cos("+50+") = "+dc.cos(50));

        System.out.println("asin: "+dc.asin(1));
        System.out.println("asinMath: "+Math.toDegrees(Math.asin(1)));
        System.out.println("acos: "+dc.acos(1));
    }
}
