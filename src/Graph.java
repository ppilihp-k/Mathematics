import exceptions.InternalErrorException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by PhilippKroll on 03.08.2016.
 */
public class Graph {

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
    private HashMap<String,Integer> nameMap;
    /**
     * colormap, in case there is a deepth- oder breadth-first search
     */
    private HashMap<Integer,Character> colorMap;
    private HashMap<Integer,Integer> predecessorMap;
    private HashMap<Integer,Integer> distanceMap;
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
        nodeAndEdgeTable = new boolean[10][11];
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
        edges = 0;
    }

    /**
     * inits a deepth- oder breath-first-search
     * should be called everytime, before a search-algorithm is computed with this graph
     */
    public void initSearch() throws InternalErrorException{
        colorMap.clear();
        predecessorMap.clear();
        distanceMap.clear();
        for(int i = 0;i < nextFreeNode; i++){
            colorMap.put(i,'w');
            predecessorMap.put(i,null);
            distanceMap.put(i,null);
        }
    }

    public void setColor(int i, char c){
        colorMap.put(i,c);
    }

    public char getColor(int i){
        return colorMap.get(i);
    }

    public void setPredecessor(int i,int j){
        predecessorMap.put(i,j);
    }

    public void setTime(int i,int t){
        distanceMap.put(i,t);
    }

    public int getTime(int i){
        return distanceMap.get(i);
    }

    public int getPredecessor(int i){
        return predecessorMap.get(i);
    }

    public void setDistance(int i, int d){
        distanceMap.put(i,d);
    }

    public int getDistance(int i){
        return distanceMap.get(i);
    }

    /**
     * returns a number, equal to the amount of nodes, which this graph contains
     * @return
     */
    public int size(){
        return nextFreeNode - 1;
    }

    /**
     * getter for the node and edge-table
     * @return, the nodeandedge-table from this graph
     */
    public boolean[][] getNodeAndEdgeTable(){
        return nodeAndEdgeTable;
    }

    /**
     * returns the number of the node, named with "name"
     * @param name, the name of the node
     * @return the number of the node with the name "name"
     */
    public int getName(String name){
        return nameMap.get(name);
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
     * @param name - the nodes name
     * @return - the grade of the node with name "name"
     */
    public int grade(String name){
        int n = nameMap.get(name);
        return grade(n);
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
        nameMap.put(name,nextFreeNode);
        nextFreeNode++;
    }

    /**
     * adds a node to this graph
     * @param name, the number of this node
     */
    public void addNode(int name){
        if(nextFreeNode >= nodeAndEdgeTable.length){
            resize();
        }
        nameMap.put(name+"",nextFreeNode);
        nextFreeNode++;
    }

    /**
     * adds a node to this graph
     * @return the number ,the note gets a associated with
     */
    public int addNode(){
        if(nextFreeNode >= nodeAndEdgeTable.length){
            resize();
        }
        nextFreeNode++;
        return nextFreeNode-1;
    }

    /**
     * adds a edge to this graph
     * @param nameFrom, the name of the node, where the edge should start
     * @param nameTo, the destination of the edge
     * @return true, if the edge was inserted correctly, false otherwise
     */
    public boolean addEdge(String nameFrom,String nameTo){

        if(contains(nameFrom) && contains(nameTo)){
            int i = nameMap.get(nameFrom);
            int j = nameMap.get(nameTo);
            if(!isDirected){
                nodeAndEdgeTable[j][i] = true;
            }
            nodeAndEdgeTable[i][j] = true;
            edges++;
            return true;
        }
        return false;
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

    public String toString(){
        String o = "Graph=(\nV={0,...,"+nextFreeNode+"},\nE={";
        for (int i = 0; i < nextFreeNode;i++){
            o += "Node_"+i+"\n{";
            for (int j = 0;j < nodeAndEdgeTable[i].length;j++){
                if(nodeAndEdgeTable[i][j]){
                    o += "("+i+","+j+")\n";
                }
            }
            o += "}\n";
        }
        o += ")";
        return o;
    }

    /**
     * resizes the nodeAndEdge-table
     */
    private void resize(){
        boolean[][] newArr = new boolean[nodeAndEdgeTable.length+10][nodeAndEdgeTable.length+1+10];
        for (int i = 0; i < nodeAndEdgeTable.length;i++){
            newArr[i] = nodeAndEdgeTable[i];
        }
        nodeAndEdgeTable = newArr;
    }
}
