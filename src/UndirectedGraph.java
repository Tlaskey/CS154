import java.util.ArrayList;
import java.util.Random;

public class UndirectedGraph
{
    private static ArrayList<Node> nodes;
    private static int numNodes;

    public UndirectedGraph(int n)
    {
        nodes = new ArrayList<>();
        numNodes = n;
        createRandomGraph(n);
    }

    public void addNode(int newNode)
    {
        nodes.add(new Node(newNode));
    }

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

    public ArrayList<ArrayList<Node>> divideGraph(int groupSize)
    {
        ArrayList<ArrayList<Node>> dividedGraphs = new ArrayList<>();
        if (numNodes >= groupSize)
        {
            int numGroups = numNodes / groupSize;
            int initial = 1;
            for (int i = 1; i <= numGroups + 1; i++)
            {
                // UndirectedGraph g = new UndirectedGraph();
                for (int j = initial; j <= groupSize * i; j++)
                {
                    if (j <= numNodes)
                    {

                    }
                }
                // dividedGraphs.add(g);
                initial += groupSize;
            }
            return dividedGraphs;
        } else
        {
            return null;
        }
    }

    public static void main(String[] args)
    {
        UndirectedGraph g = new UndirectedGraph(5);
        g.printGraph();
    }
}

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
}
