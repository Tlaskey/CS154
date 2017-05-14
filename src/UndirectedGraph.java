import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UndirectedGraph
{
    private static Map<Integer, ArrayList<Integer>> nodes;
    private final int DEFAULT_SIZE = 10;
    private int numNodes;

    public UndirectedGraph(int n)
    {
        nodes = new HashMap<Integer, ArrayList<Integer>>();
        this.numNodes = n;
        createRandomGraph(n);
    }

    public UndirectedGraph()
    {
        nodes = new HashMap<Integer, ArrayList<Integer>>();
        this.numNodes = DEFAULT_SIZE;
        createRandomGraph(DEFAULT_SIZE);
    }

    public static void addNode(int x)
    {
        nodes.put(x, new ArrayList<Integer>());
    }

    // Creates a random graph.
    public void createRandomGraph(int n)
    {
        // Create and add the number of nodes to graph.
        for (int i = 1; i <= n; i++)
        {
            addNode(i);
        }
        // Randomly fill in the adjacency lists of each node.
        Random rand = new Random();
        for (int i = 1; i <= n; i++)
        {
            // Random number of edges where max edges is n / 2
            int numEdges = rand.nextInt(n / 2) + 1;
            ArrayList<Integer> adjList = nodes.get(i);
            for (int j = 1; j <= numEdges; j++)
            {
                // Choose random node
                int random = rand.nextInt(n) + 1;
                if (!adjList.contains(random) && random != i)
                {
                    ArrayList<Integer> randomAdjList = nodes.get(random);
                    // Add to the adjacency lists of both nodes.
                    adjList.add(random);
                    randomAdjList.add(i);
                }
            }
        }
    }

    public void printGraph()
    {
        for (int i = 1; i <= numNodes; i++)
        {
            ArrayList<Integer> adjList = nodes.get(i);
            System.out.println("Node: " + i);
            System.out.println("Adjacent Nodes:");
            for (Integer x : adjList)
            {
                System.out.println(x);
            }
        }
    }

    public int findMaxClique()
    {
        return 0;
    }

    public static void main(String[] args)
    {
        UndirectedGraph g = new UndirectedGraph(5);
        g.printGraph();
    }
}
