import exceptions.InternalErrorException;

import java.util.*;

/**
 * Created by PhilippKroll on 03.08.2016.
 * this class represents either a directed or undirected graph. it provides basic methods to manipulate the graph itselve
 * and contains variables to save information, which may be computed while graph traversing algorithms
 */
public class Graph extends Observable {

    /**
     * shows, wether the graph is directed ur undirected
     */
    private boolean isDirected;
    /**
     * this table represents a adjazenzlist, where the edges and nodes are stored in
     */
    private boolean[][] nodeAndEdgeTable;
    /**
     * if the nodes have names, the information, witch nodes has witch number is stored here
     */
    private HashMap<Integer,String> nameMap;
    /**
     * colormap, in case there is a depth- oder breadth-first search
     */
    private HashMap<Integer,Character> colorMap;
    /**
     *predecessormap, helps out in case of a bsf or dsf
     */
    private HashMap<Integer,Integer> predecessorMap;
    /**
     *distancemap, helps out in case of a bsf or dsf
     */
    private HashMap<Integer,Integer> distanceMap;
    /**
     *variable to determine the node, where the traverse started, helps out in case of a bsf or dsf
     */
    private Integer startNode;
    /**
     * indicates the end of the nodeandedge-table
     */
    private int nextFreeNode;

    /**
     * counter for the amount of edges
     */
    private int edges;

    /**
     * represents a graph, either directed or undirected
     * @param isDirected - if u want to instantiate an undirectedgraph pass false, else pass true
     */
    public Graph(boolean isDirected){
        nodeAndEdgeTable = new boolean[10][10];
        for (int i = 0;i < nodeAndEdgeTable.length; i++){
            for (int j = 0;j < nodeAndEdgeTable[i].length;j++){
                nodeAndEdgeTable[i][j] = false;
            }
        }
        nextFreeNode = 0;
        nameMap = new HashMap<>();
        colorMap = new HashMap<>();
        predecessorMap = new HashMap<>();
        distanceMap = new HashMap<>();
        this.isDirected = isDirected;
        startNode = null;
        edges = 0;
    }

    /**
     * inits a depth- oder breath-first-search
     * should be called everytime, before a search-algorithm is computed with this graph
     */
    public void initSearch() throws InternalErrorException{
        startNode = null;
        colorMap.clear();
        predecessorMap.clear();
        distanceMap.clear();
        for(int i = 0;i < nextFreeNode; i++){
            colorMap.put(i,'w');
            predecessorMap.put(i,null);
            distanceMap.put(i,null);
        }
    }

    /**
     * getter for a "helping-variable" for bfs/dfs
     * @return null or a number / name, which the graph contains
     */
    public Integer getStartNode(){
        return startNode;
    }

    /**
     * sets this variable
     * @param s, a number this graph contains
     */
    public void setStartNode(Object s){
        if(s instanceof Integer){
            if((Integer)s >= 0 && (Integer)s < nextFreeNode)
                startNode = (Integer)s;
        }
        if(s instanceof String){
            for (int i = 0;i < nextFreeNode;i++){
                if(nameMap.get(i).equals((String)s)){
                    startNode = i;
                }
            }
        }
    }

    /**
     * auxiliary variable setter, sets the color of a specific node.
     * this method is used for bfs/dfs algorithms
     * @param i
     * @param c
     */
    public void setColor(int i, char c){
        colorMap.put(i,c);
    }

    /**
     * auxiliary variable getter, returns the color of a node
     * this method is used for bfs/dfs algorithms
     * @param i
     * @return
     */
    public char getColor(int i){
        return colorMap.get(i);
    }

    /**
     * auxiliary variable setter, sets the predecessor of a specific node
     * this method is used for bfs/dfs algorithms
     * @param i
     * @param j
     */
    public void setPredecessor(int i,int j){
        predecessorMap.put(i,j);
    }

    /**
     * auxiliary variable setter, represents a timestamp, which is used to determine, at witch point of time a node was found in a dfs-algorithm
     * this method is used for dfs algorithms
     * @param i
     * @param t
     */
    public void setTime(int i,int t){
        distanceMap.put(i,t);
    }

    /**
     * auxiliary variable getter, timestamp
     * this method is used for dfs algorithms
     * @param i
     * @return
     */
    public int getTime(int i){
        return distanceMap.get(i);
    }

    /**
     * auxiliary variable getter, returns a,in a dfs/bfs search computed, predecessor of the node i
     * this method is used for bfs/dfs algorithms
     * @param i
     * @return
     */
    public int getPredecessor(int i){
        return predecessorMap.get(i);
    }

    /**
     * auxiliary variable setter
     * this method is used for bfs algorithms
     * @param i
     * @param d
     */
    public void setDistance(int i, int d){
        distanceMap.put(i,d);
    }

    /**
     * auxiliary variable getter
     * this method is used for bfs algorithms
     * @param i
     * @return
     */
    public int getDistance(int i){
        return distanceMap.get(i);
    }

    public String showBreathFirstSearchInfo(){
        String o = "+------------------------------------------------------------------------+\n";
        o +=       "|Information: breath-first-search                                        |\n";
        o +=       "+--------------------+--------------------+----------------+-------------+\n";
        o +=       "|        node        |     predecessor    |    distance    |    color    |\n";
        o +=       "+--------------------+--------------------+----------------+-------------+\n";
        /*75 chars*, 20 node, 20 predecessor, 16 distance, 13 color*/
        for (int i = 0;i < nextFreeNode; i++){
            String name = nameMap.get(i);
            if(name.length() >= 19){
                if(i == startNode){
                    name = name.substring(0,18);
                    name += ".*";
                } else {
                    name = name.substring(0,19);
                    name += ".";
                }
            }
            if(i == startNode){
                name += "*";
            }
            o +=   "|"+name;
            for (int j = 0;j < 20-name.length();j++){
                o += " ";
            }
            o += "|";
            String pred = nameMap.get(predecessorMap.get(i));
            if(pred == null){
                pred = "null";
            } else {
                if(pred.length() >= 20){
                    pred = pred.substring(0,20);
                }
            }
            o +=   pred;
            for (int j = 0;j < 20-pred.length();j++){
                o += " ";
            }
            o += "|";
            o +=   distanceMap.get(i);
            for (int j = 0;j < 16-(distanceMap.get(i)+"").length();j++){
                o += " ";
            }
            o += "|";
            o +=   colorMap.get(i);
            for (int j = 0;j < 13-(colorMap.get(i)+"").length();j++){
                o += " ";
            }
            o += "|\n";
            o += "+--------------------+--------------------+----------------+-------------+\n";
        }
        return o;
    }

    public String showDepthFirstSearchInfo(){
        String o = "+------------------------------------------------------------------------+\n";
        o +=       "|Information: depth-first-search                                         |\n";
        o +=       "+--------------------+--------------------+----------------+-------------+\n";
        o +=       "|        node        |     predecessor    |      time      |    color    |\n";
        o +=       "+--------------------+--------------------+----------------+-------------+\n";
        /*75 chars*, 20 node, 20 predecessor, 16 distance, 13 color*/
        for (int i = 0;i < nextFreeNode; i++){
            String name = nameMap.get(i);
            if(name.length() >= 19){
                if(i == startNode){
                    name = name.substring(0,18);
                    name += ".*";
                } else {
                    name = name.substring(0,19);
                    name += ".";
                }
            }
            o +=   "|"+name;
            for (int j = 0;j < 20-name.length();j++){
                o += " ";
            }
            o += "|";
            String pred = nameMap.get(predecessorMap.get(i));
            if(pred == null){
                pred = "null";
            } else {
                if(pred.length() >= 20){
                    pred = pred.substring(0,20);
                }
            }
            o +=   pred;
            for (int j = 0;j < 20-pred.length();j++){
                o += " ";
            }
            o += "|";
            o +=  getTime(i);
            for (int j = 0;j < 16-(getTime(i)+"").length();j++){
                o += " ";
            }
            o += "|";
            o +=   colorMap.get(i);
            for (int j = 0;j < 13-(colorMap.get(i)+"").length();j++){
                o += " ";
            }
            o += "|\n";
            o += "+--------------------+--------------------+----------------+-------------+\n";
        }
        return o;
    }

    /**
     * removes a edge from this graph
     * @param from, source of the edge
     * @param to, destination of the edge
     * @return true, if the edge was removed, false otherwise
     */
    private boolean removeEdgePriv(int from,int to){
        if(from < 0 || to >= nextFreeNode ||to < 0 || to >= nextFreeNode){
            return false;
        }
        if(nodeAndEdgeTable[from][to]){
            nodeAndEdgeTable[from][to] = false;
            if(!isDirected){
                nodeAndEdgeTable[to][from] = false;
            }
        }
        return true;
    }

    /**
     * removes a edge from this graph, if from or to are no instances of integer or string, the method has no effect on this graph
     * @param from, source of the edge
     * @param to, destination of the edge
     * @return true, if the edge was removed, false otherwise
     */
    public boolean removeEdge(Object from, Object to){
        if(from instanceof Integer && to instanceof Integer){
            return removeEdgePriv((Integer)from,(Integer)to);
        }
        if(from instanceof String && to instanceof  String){
            int f = -1;
            int t = -1;
            for (int i = 0;i < nextFreeNode; i++){
                String name = nameMap.get(i);
                if(f != -1 && t != -1){
                    break;
                }
                if(name != null){
                    if(name.equals((String)from)){
                        f = i;
                    }
                    if(name.equals((String)to)){
                        t = i;
                    }
                }
            }
            if(f < 0 || t < 0){
                return false;
            }
            return removeEdgePriv(f,t);
        }
        if(from instanceof String && to instanceof Integer){
            for (int i = 0;i <nextFreeNode;i++){
                String name = nameMap.get(i);
                if(name != null){
                    if(name.equals(from)){
                        return removeEdgePriv(i,((Integer) to).intValue());
                    }
                }
            }
        }
        if(from instanceof Integer && to instanceof String){
            for (int i = 0;i <nextFreeNode;i++){
                String name = nameMap.get(i);
                if(name != null){
                    if(name.equals(from)){
                        return removeEdgePriv(((Integer) from).intValue(),i);
                    }
                }
            }
        }
        return false;
    }

    /**
     * returns a number, equal to the amount of nodes, which this graph contains
     * @return
     */
    public int size(){
        return nextFreeNode;
    }

    /**
     * getter for the node and edge-table
     * @return, the nodeandedge-table from this graph
     */
    public boolean[][] getNodeAndEdgeTable(){
        return nodeAndEdgeTable;
    }

    /**
     * getter for the "mode" of this graph
     * @return true, if the graph is directed, false otherwise
     */
    public boolean isDirectedGrpah(){
        return isDirected;
    }

    /**
     * computes the grade of a single node
     * @param n - the nodes number
     * @return - the grade of the node with number n
     */
    public int grade(int n){
        if(contains(n)){
            int c = 0;
            for (int i = 0;i < nextFreeNode;i++){
                if(nodeAndEdgeTable[n][i]){
                    c++;
                }
            }
            return c;
        }
        return -1;
    }

    /**
     * adds a node to this graph
     * @param name - if the node should get a name pass it here
     */
    public void addNode(String name){
        if(nextFreeNode >= nodeAndEdgeTable.length){
            resize();
        }
        if(name == null || name.length() == 0){
            addNode();
            return;
        }
        nameMap.put(nextFreeNode,name);
        nextFreeNode++;
        if(super.countObservers() > 0){
            super.notifyObservers("new node");
        }
    }

    /**
     * adds a node to this graph
     * @param name, the number of this node
     */
    public void addNode(int name){
        if(nextFreeNode >= nodeAndEdgeTable.length){
            resize();
        }
        nameMap.put(nextFreeNode,name+"");
        nextFreeNode++;
        if(super.countObservers() > 0){
            super.notifyObservers("new node");
        }
    }

    /**
     * adds a node to this graph
     * @return the number ,the note gets a associated with
     */
    public int addNode(){
        if(nextFreeNode >= nodeAndEdgeTable.length){
            resize();
        }
        nameMap.put(nextFreeNode,nextFreeNode+"");
        nextFreeNode++;
        if(super.countObservers() > 0){
            super.notifyObservers("new node");
        }
        return nextFreeNode-1;
    }

    /**
     * adds a edge to this graph
     * @param from, the number of the node, where the edge should start
     * @param to, the destination of the edge
     * @return true, if the edge was inserted correctly, false otherwise
     */
    public boolean addEdge(int from,int to){
        if(from < nextFreeNode && to < nextFreeNode && from >= 0 && to >= 0){
            if(!isDirected){
                nodeAndEdgeTable[to][from] = true;
            }
            nodeAndEdgeTable[from][to] = true;
            edges++;
            if(super.countObservers() > 0){
                super.notifyObservers("new edge");
            }
            return true;
        }
        return false;
    }

    /**
     * checks, wether this graph contains the edge (i,j)
     * @param i, first node of this edge
     * @param j, second node of this edge
     * @return true, if the graph contains this edge, false otherwise
     */
    public boolean hasEdge(int i, int j){
        if(i >= 0 && i < nextFreeNode && j >= 0 && j < nextFreeNode){
            if(nodeAndEdgeTable[i][j]){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the graph contains a node
     * @param name, the name of the node
     * @return true, if the graph contians this node, false otherweise
     */
    public boolean contains(String name) {
        if (nameMap.get(name) != null) {
            return true;
        }
        return false;
    }

    /**
     * checks if the graph contains a node
     * @param n, the number of the node
     * @return true, if the graph contians this node, false otherweise
     */
    public boolean contains(int n){
        if(n >= 0 && n < nextFreeNode)
            return true;
        return false;
    }

    /**
     * this method creates an array, which contains all edges, which this graph is containing
     * @return an array with all edges from this graph
     */
    public Tupel<Integer,Integer>[] edgesToArray(){
        Tupel<Integer,Integer>[] array = new Tupel[edges];
        int k = 0;
        for (int i = 0;i < nextFreeNode;i++){
            for (int j = 0;j < nextFreeNode;j++){
                if(nodeAndEdgeTable[i][j]){
                    array[k] = new Tupel<Integer,Integer>(i,j);
                }
            }
        }
        return array;
    }

    /**
     * this method creates a string, representing this graph
     * @return a string with all information, this graph contains
     */
    public String toString(){
        String o = "";
        if(isDirectedGrpah()){
            o += "Directed ";
        } else {
            o += "Undirected ";
        }
        o += "Graph=(";
        if(nameMap.size() == 0){
            o += "V={0,...,"+nextFreeNode+"},\nE={";
        } else {
            o += "V={";
            for (int i = 0;i < nextFreeNode; i++){
                String name = nameMap.get(i);
                if(name != null){
                    o += "\""+name+"\""+"/"+i;
                } else {
                    o += i;
                }
                if(i < nextFreeNode-1){
                    o += ",";
                }
            }
            o += "},\nE={";
        }
        for (int i = 0; i < nextFreeNode;i++){
            if(nameMap.get(i) != null){
                o += "\t\""+nameMap.get(i)+"\"/"+i+"{";
            } else {
                o += "\tNode_"+i+"{";
            }
            for (int j = 0;j < nodeAndEdgeTable[i].length;j++){
                if(nodeAndEdgeTable[i][j]){
                    o += "(";
                    if(nameMap.get(i) != null){
                        o += nameMap.get(i);
                    } else {
                        o += i;
                    }
                    o += ",";
                    if(nameMap.get(j) != null) {
                        o += nameMap.get(j);
                    } else {
                        o += j;
                    }
                    o += ")";
                    if(j < nextFreeNode){
                        o += ",";
                    }
                }
            }
            o = o.substring(0,o.length()-1);
            if(i >= nextFreeNode-1){
                o += "}";
            } else {
                o += "},\n";
            }
        }
        o += ")\n";
        return o;
    }

    /**
     * resizes the nodeAndEdge-table
     */
    private void resize(){
        boolean[][] newArr = new boolean[nodeAndEdgeTable.length+10][nodeAndEdgeTable.length+10];
        for (int i = 0; i < nodeAndEdgeTable.length;i++){
            newArr[i] = nodeAndEdgeTable[i];
        }
        nodeAndEdgeTable = newArr;
    }
}
