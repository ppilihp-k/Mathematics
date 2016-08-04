import exceptions.InternalErrorException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by PhilippKroll on 04.08.2016.
 */
public class GraphCalculator {

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
        Integer[][] nodeAndEdgeTable = g.getNodeAndEdgeTable();
        try {
            g.initSearch();
        } catch (InternalErrorException e){
            return false;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(s);
        while(queue.size() != 0){
            int u = queue.poll();
            if(u < g.size()){
                for (int i = 0;i < nodeAndEdgeTable[u].length;i++){
                    g.setColor(nodeAndEdgeTable[u][i],'w');
                    g.setPredecessor(nodeAndEdgeTable[u][i],u);
                    g.setDistance(nodeAndEdgeTable[u][i],g.getDistance(u)+1);
                    queue.add(new Integer(nodeAndEdgeTable[u][i]));
                }
                g.setColor(u,'b');
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * starts a deepth-first-search in the given graph, the generated values for time, predecessor and color are accessable throught the getters in the given graph g, after the computation terminates
     * @param g, the graph
     * @return true, if the search terminates correctly, false otherwise
     */
    public boolean deepthFirstSearch(Graph g){
        try {
            g.initSearch();
        } catch (InternalErrorException e){
            return false;
        }
        int k = g.size();
        TimeStamp time = new TimeStamp();
        for (int i = 0; i < k; i++){
            if(g.getColor(i) == 'w'){
                deepthFirstSearchVisit(g,i,time);
            }
        }
        return true;
    }

    /**
     * private method for deepth-first-search. the method visits a node and traverses further on in this graph
     * @param g, the graph
     * @param n, the node, which is visited
     */
    private void deepthFirstSearchVisit(Graph g,int n, TimeStamp time){
        time.incr();
        g.setTime(n,time.time);
        g.setColor(n,'g');
        for (int i = 0;i < g.getNodeAndEdgeTable()[n].length;i++) {
            if (g.getNodeAndEdgeTable()[n][i] != null && g.getColor(g.getNodeAndEdgeTable()[n][i]) == 'w') {
                g.setPredecessor(g.getNodeAndEdgeTable()[n][i], n);
                deepthFirstSearchVisit(g, g.getNodeAndEdgeTable()[n][i], time);
            }
        }
        g.setColor(n,'b');
        time.incr();
        g.setTime(n,time.time);
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
        List<Integer> list = new LinkedList<>();
        for (int i = 0;i < size - k; i++){
            list.clear();
            for (int j = i;j < k;j++){
                list.add(new Integer(j));
            }
            if(clique(list,g)){
                return true;
            }
        }
        return false;
    }

    /**
     * returns the maximum sized clique from this graph
     * @param g, the graph
     * @return, the clique with maximal size, contains by graph g
     */
    public List<Integer> cliqueO(Graph g){
        List<Integer> list = new LinkedList<>();
        int i = g.size();
        while(i > 0){
            if(kClique(g,i)){
                for(int j = 0;j < g.size() - i;j++){
                    list.clear();
                    for (int k = 0;k < i;k++){
                        list.add(new Integer(k));
                    }
                    if(clique(list,g)){
                        return list;
                    }
                }
            }
            i--;
        }
        list.clear();
        return list;
    }

    /**
     * helps to pass a "time" as apointer to a deepth-first-search
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
