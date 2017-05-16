import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Tyler Laskey
 *
 */
public class UndirectedGraph
{
    private static ArrayList<Node> nodes;
    private static int numNodes;

    /**
     * 
     * @param n
     *            number of nodes in graph
     */
    public UndirectedGraph(int n)
    {
        nodes = new ArrayList<>();
        numNodes = n;
        createRandomGraph(n);
    }

    /**
     * Add node to graph.
     * 
     * @param newNode
     *            node to be added.
     */
    public void addNode(int newNode)
    {
        nodes.add(new Node(newNode));
    }

    /**
     * Creates a random graph of size n.
     * 
     * @param n
     *            number of nodes in graph.
     */
    public void createRandomGraph(int n)
    {
        // Populate graph with n nodes.
        for (int i = 1; i <= n; i++)
        {
            addNode(i);
        }
        // Randomize number of edges for each node.
        Random rand = new Random();
        int numEdges = rand.nextInt(n / 2) + 1;
        for (int i = 0; i < n; i++)
        {
            Node node = nodes.get(i);
            ArrayList<Node> nodeAdjList = node.getAdjList();
            for (int j = 0; j < numEdges; j++)
            {
                int randNum = rand.nextInt(n);
                Node randNode = nodes.get(randNum);
                ArrayList<Node> randNodeAdjList = randNode.getAdjList();
                if (!nodeAdjList.contains(randNode) && node.getID() != randNode.getID())
                {
                    nodeAdjList.add(randNode);
                    randNodeAdjList.add(node);
                }
            }
        }
    }

    /**
     * Prints the graph using adjacency list representation.
     */
    public void printGraph()
    {
        for (Node n : nodes)
        {
            System.out.println("Node: " + n.getID());
            System.out.println("Adjacent Nodes: ");
            for (Node x : n.getAdjList())
            {
                System.out.println(x.getID());
            }
        }
    }

    /**
     * Divides graph into subgraphs of size 'groupSize'
     * 
     * @param groupSize
     *            size of the subgraphs
     * @return ArrayList containing the subgraphs
     */
    public ArrayList<ArrayList<Node>> divideGraph(int groupSize)
    {
        ArrayList<ArrayList<Node>> dividedGraphs = new ArrayList<>();
        if (numNodes >= groupSize)
        {
            double numGroups = numNodes / groupSize + 1;
            int initial = 0;
            for (int i = 1; i <= numGroups; i++)
            {
                ArrayList<Node> subGraph = new ArrayList<>();
                for (int j = initial; j < groupSize * i; j++)
                {
                    if (j < numNodes)
                    {
                        subGraph.add(nodes.get(j));
                    }
                }
                if (!subGraph.isEmpty())
                {
                    dividedGraphs.add(subGraph);
                }
                initial += groupSize;
            }
            return dividedGraphs;
        } else
        {
            return null;
        }
    }

    /**
     * Prints the subgraphs.
     * 
     * @param subGraphs
     *            the subGraphs to be printed.
     */
    public static void printSubGraphs(ArrayList<ArrayList<Node>> subGraphs)
    {
        int i = 1;
        for (ArrayList<Node> list : subGraphs)
        {
            System.out.println("Subgraph: " + i);
            for (Node node : list)
            {
                System.out.println("Node: " + node.getID());
            }
            i++;
        }
    }

    public static void main(String[] args)
    {
        UndirectedGraph g = new UndirectedGraph(10);
        g.printGraph();
        ArrayList<ArrayList<Node>> divided = g.divideGraph(5);
        printSubGraphs(divided);
    }
}

/**
 * Node class
 * 
 * @author tylerlaskey
 *
 */
class Node
{
    private int ID;
    private ArrayList<Node> adjList;

    public Node(int x)
    {
        this.ID = x;
        adjList = new ArrayList<>();
    }

    public int getID()
    {
        return ID;
    }

    public ArrayList<Node> getAdjList()
    {
        return adjList;
    }

    public int nodeDegree()
    {
        return adjList.size();
    }
}
