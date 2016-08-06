import exceptions.InternalErrorException;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by PhilippKroll on 04.08.2016.
 */
public class GraphCalculator {

    /**
     * this method creates a minimal spanningtree of the given graph.
     * this method uses the kruskal-algorithm
     * @param g, the graph
     * @param w, a function, which contians a value (cost) for each edge of the graph g
     * @return a spanningtree, with minimal costvalue
     */
    public Graph minSpanningTree(Graph g, HashMap<Tupel<Integer,Integer>,Float> w){

        if(g.isDirectedGrpah()){
            return null;
        }
        if(!isConnected(g)){
            return null;
        }
        try {
            g.initSearch();
        } catch(InternalErrorException e){
            return null;
        }
        int k = g.size();
        Graph sp = new Graph(false);
        /*make sets*/
        LinkedList<LinkedList<Integer>> trees = new LinkedList<>();
        for (int i = 0;i < g.size(); i++){
            LinkedList<Integer> l = new LinkedList<>();
            l.add(i);
            trees.add(l);
        }
        /*sort edges descending*/
        Tupel<Integer,Integer>[] edges = g.edgesToArray();
        sortEdges(edges,w);
        /*build the spanningtree*/
        for (Tupel<Integer,Integer> t : edges) {
            LinkedList<Integer> u = findSet(t.first,trees);
            LinkedList<Integer> v = findSet(t.first,trees);
            if(u == null ||v == null){
                return null;
            }
            if(!u.equals(v)){
                g.addNode(t.first);
                g.addNode(t.second);
                g.addEdge(t.first,t.second);
                trees.remove(u);
                trees.remove(v);
                trees.add(union(u,v));
            }
        }
        return sp;
    }

    /**
     * checks wether one of the lists contains u
     * @param u, the integer to look for
     * @param trees, the lists, where to look
     * @return the list, which contains u
     */
    private LinkedList<Integer> findSet(int u,LinkedList<LinkedList<Integer>> trees){
        for (LinkedList<Integer> l: trees) {
            for (int i:l) {
                if(i == u){
                    return l;
                }
            }
        }
        return null;
    }

    /**
     * method checks, if the two "sets" equal
     * @param U
     * @param V
     * @return true, if U and V contain the same elements
     */
    private boolean equals(LinkedList<Integer> U, LinkedList<Integer> V){
        for (int u:U) {
            for (int v:V) {
                if(u != v){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * unites the two linkedlists to one new list, the two arguments are not changed. a new linkedlist, containing all the elements from both lists, is returned
     * @param set_1
     * @param set_2
     * @return a new list, which contains all the elements of set_1 and set_2
     */
    private LinkedList<Integer> union(LinkedList<Integer> set_1,LinkedList<Integer> set_2){
        LinkedList<Integer> list = new LinkedList<>();
        for (Integer t: set_1) {
            if(!list.contains(t)){
                list.add(t);
            }
        }
        for (Integer t: set_2) {
            if(!list.contains(t)){
                list.add(t);
            }
        }
        return list;
    }

    /**
     * this method sorts the edges descending by their value, the edges is associated with in the hashmap
     * @param array, the edges
     * @param w, the cost-function, containing a cost for every edge
     * @return true, if the array was sorted, false, if there is any edge, without a float-value from w
     */
    private boolean sortEdges(Tupel<Integer,Integer>[] array, HashMap<Tupel<Integer,Integer>,Float> w){
        int i = 1;
        int j = 0;
        while(i < array.length){
            Tupel<Integer,Integer> key = array[i];
            if(key == null){
                return false;
            }
            j = i - 1;
            while(j > 0 && w.get(key) < w.get(array[j])){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = key;
            i++;
        }
        return true;
    }

    /**
     * a spanningtree is set of edges, where the set is a subset of the edgeset of a graph, in this case the spanningtrees edgeset is a subset of the edges of the graph g
     * @param g, the graph
     * @param spanningTree, a spanningtree
     * @return true, if the spanningtree is a spanningtree of the graph g, false otherwise
     */
    private boolean isSpanningTree(Graph g, Graph spanningTree){
        if(!isConnected(spanningTree)){
            return false;
        }
        if(spanningTree.size() > g.size()){
            return false;
        }
        for (int i = 0;i < spanningTree.size();i++){
            for (int j = 0;j < spanningTree.getNodeAndEdgeTable()[i].length;j++){
                if(spanningTree.hasEdge(i,j) && !g.hasEdge(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * checks, whether there is no unreachable node in this graph,
     * first executes a breath-first-search on the graph and then checks, if there are any nodes left, which where not visited by the BFS
     * @param g, the graph
     * @return true, if the graph doesnt contains unreachable nodes, false otherwise
     */
    public boolean isConnected(Graph g){
        try {
            g.initSearch();
        } catch (InternalErrorException e){
            return false;
        }
        Random r = new Random();
        breathFirstSearch(g,r.nextInt(g.size()-1));
        for (int i = 0;i < g.size();i++){
            if(g.getColor(i) == 'w'){
                return false;
            }
        }
        return true;
    }

    /**
     * sorts the nodes by their timestamps
     * @param g, the graph, which should be sorted topologically. the graph itselfe is not changed
     * @return a new integer array, which contains the nodes, sorted ascending by their timestamps
     */
    public int[] topologicalSort(Graph g){
        depthFirstSearch(g);
        int[] nodes = new int[g.size()];
        int[] times = new int[g.size()];
        for (int i = 0; i < g.size();i++){
            nodes[i] = i;
            times[i] = g.getTime(i);
        }
        mergeSort(nodes,times,0,nodes.length);
        return nodes;
    }

    /**
     * implements merge-sort, the initial call to this method is lo = 0, hi = array.length !!
     * @param n
     * @param t
     */
    private void mergeSort(int[] n, int[] t,int lo, int hi){
        if(lo < hi){
            int mi = (lo+hi)/2;
            mergeSort(n,t,lo,mi);
            mergeSort(n,t,mi+1,hi);
            merge(n,t,lo,mi,hi);
        }
    }

    /**
     * merges the arrays in the right order
     * @param n
     * @param t
     * @param lo, lowest index of the unsorted subarray
     * @param mi, mid index of the unsorted subarray
     * @param hi, highest index of the unsorted subarray
     */
    private void merge(int[]n,int[]t,int lo,int mi,int hi){
        int n1 = mi - lo;
        int n2 = hi - mi;
        int[] leN = new int[n1];
        int[] leT = new int[n1];
        int[] riN = new int[n2];
        int[] riT = new int[n2];
        int i = 0,j = 0;
        while(i < n1){
            leN[i] = n[lo+i];
            leT[i] = t[lo+i];
            i++;
        }
        while(j < n2){
            riN[j] = n[mi+j];
            riT[j] = t[mi+j];
            j++;
        }
        i = 0;
        j = 0;
        int k = lo;
        while(k <= hi && i < n1 && j < n2){
            if(leT[i] <= riT[j]){
                n[k] = leN[i];
                t[k] = leT[i];
                i++;
            } else {
                n[k] = riN[j];
                t[k] = riT[j];
                j++;
            }
            k++;
        }
        while(i < n1){
            n[k] = leN[i];
            t[k] = leT[i];
            i++;
            k++;
        }
        while(j < n2){
            n[k] = riN[j];
            t[k] = riT[j];
            j++;
            k++;
        }
    }

    /**
     * start a breadth-first search in the given graph, after this algorithm, the graph contains all information, the breath-first-search computed in the colorMap,predecessorMap and distanceMap.
     * Which is accessible by calling g.getColor(), g.getDistance(), g.getPredecessor().
     * thus unreachable nodes have distance null
     * @param g, the graph
     * @param s, the starting node
     * @return true, if the search terminates correctly, false, if g dont contains s or a other error occured
     */
    public boolean breathFirstSearch(Graph g,int s){
        if(!g.contains(s)){
            return false;
        }
        boolean[][] nodeAndEdgeTable = g.getNodeAndEdgeTable();
        try {
            g.initSearch();
        } catch (InternalErrorException e){
            return false;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        g.setStartNode(s);
        g.setDistance(s,0);
        g.setColor(s,'g');
        queue.add(s);
        while(queue.size() != 0){
            int u = queue.poll();
            for (int i = 0;i < nodeAndEdgeTable[u].length;i++){
                if(g.getNodeAndEdgeTable()[u][i] && g.getColor(i) == 'w') {
                    g.setColor(i,'g');
                    g.setPredecessor(i,u);
                    g.setDistance(i,g.getDistance(u)+1);
                    queue.add(i);
                }
            }
            g.setColor(u,'b');
        }
        return true;
    }

    /**
     * starts a depth-first-search in the given graph, the generated values for time, predecessor and color are accessable throught the getters in the given graph g, after the computation terminates
     * @param g, the graph
     * @return true, if the search terminates correctly, false otherwise
     */
    public boolean depthFirstSearch(Graph g){
        try {
            g.initSearch();
        } catch (InternalErrorException e){
            return false;
        }
        int k = g.size();
        TimeStamp time = new TimeStamp();
        for (int i = 0; i < k; i++){
            if(g.getColor(i) == 'w'){
                depthFirstSearchVisit(g,i,time);
            }
        }
        return true;
    }

    /**
     * private method for depth-first-search. the method visits a node and traverses further on in this graph
     * @param g, the graph
     * @param node, the node, which is visited
     */
    private void depthFirstSearchVisit(Graph g,int node, TimeStamp time){
        time.incr();
        g.setTime(node,time.time);
        g.setColor(node,'g');
        for (int i = 0;i < g.getNodeAndEdgeTable()[node].length;i++) {
            if (g.getNodeAndEdgeTable()[node][i] && g.getColor(i) == 'w') {
                g.setPredecessor(i, node);
                depthFirstSearchVisit(g, i, time);
            }
        }
        g.setColor(node,'b');
        time.incr();
        g.setTime(node,time.time);
    }

    /**
     * is c a subset of the nodeset of g? and are the elements of c pairwise adjacent?
     * @param c, the subset of nodes of g
     * @param g, the graph
     * @return true, if g contains all elements of c and the elements of c are pairwise adjacent in g
     */
    public boolean clique(List<Integer> c,Graph g){
        for (Integer node_1: c) {
            for (Integer node_2: c) {
                if(node_1 != node_2 && !g.hasEdge(node_1,node_2)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * looks for a clique of the size k, in the graph g
     * @param g, the graph, where to search the clique
     * @param k, the size of the clique
     * @return true, if the graph g contains a clique of the size k
     */
    public boolean kClique(Graph g,int k){
        int size = g.size();
        if(k > size){
            return false;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0;i < size; i++){
            list.clear();
            list.add(i);
            int m = 0;
            int n = 0;
            while(m < size){
                while(list.size() != k){
                    if(n != i){
                        list.add((m+n)%size);
                        n++;
                    } else {
                        n++;
                    }
                }
                if(clique(list,g)){
                    return true;
                } else {
                    m++;
                }
            }
        }
        list.clear();
        return false;
    }

    /**
     * returns the maximum sized clique from this graph, this method dont changes any of the graphs attributes and returns a new integer list, which contains the numbers of the nodes
     * this method runs through nearly all permutations of nodes! this is very slow.
     * @param g, the graph
     * @return, the cliques with maximal size, which g contains
     */
    public LinkedList<LinkedList<Integer>> cliqueO(Graph g){
        /*find out the size of the maximum sized clique with kclique, then look for a clique with size k - problem: i want all kcliques, otherwise there is the posibility, that the graph contians more then one
        * kcliques -> a single use of cliqueO returns the first k-clique it finds */
        int k = 1;
        /*look for the maximum size a clique has in g*/
        int i = g.size();
        while(i > 0){
            if(kClique(g,i)){
                k = i;
                break;
            }
        }
        /*now test all possible candidates to find all cliques of size k*/
        LinkedList<LinkedList<Integer>> c = new LinkedList<>();
        i = 0;
        while(i < g.size()){
            LinkedList<Integer> list = new LinkedList<>();
            int m = 0;
            int n = 0;
            while(m < g.size()){
                while(list.size() != k){
                    if(n != i){
                        list.add((m+n)%g.size());
                        n++;
                    } else {
                        n++;
                    }
                }
                if(clique(list,g)){
                    for (LinkedList<Integer> l: c) {
                        if(!equals(l,list)){
                            c.add(list);
                        }
                    }
                } else {
                    m++;
                }
            }
        }
        return c;
    }

    /**
     * helps to pass a "time" as apointer to a depth-first-search
     */
    private class TimeStamp{
        public int time = 0;
        public void incr(){
            time++;
        }
        public void decr(){
            time--;
        }
    }
}
