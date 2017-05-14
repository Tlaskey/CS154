import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UndirectedGraph
{
    private static Map<Integer, ArrayList<Integer>> nodes;
    private int numNodes;

    public UndirectedGraph(int n)
    {
        nodes = new HashMap<Integer, ArrayList<Integer>>();
        createRandomGraph(n);
        this.numNodes = n;
    }

    public UndirectedGraph()
    {
        nodes = new HashMap<Integer, ArrayList<Integer>>();
    }

    public static void addNode(int x)
    {
        nodes.put(x, new ArrayList<Integer>());
    }

    public void addEdge(int from, int to)
    {
        ArrayList<Integer> listFrom = nodes.get(from);
        ArrayList<Integer> listTo = nodes.get(to);
        listFrom.add(to);
        listTo.add(from);
    }

    public void createRandomGraph(int n)
    {
        for (int i = 1; i <= n; i++)
        {
            addNode(i);
        }
        Random rand = new Random();
        for (int i = 1; i <= n; i++)
        {
            int numEdges = rand.nextInt(n / 2) + 1;
            ArrayList<Integer> adjList = nodes.get(i);
            for (int j = 1; j <= numEdges; j++)
            {
                int random = rand.nextInt(n) + 1;
                if (!adjList.contains(random) && random != i)
                {
                    adjList.add(random);
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
        UndirectedGraph g = new UndirectedGraph(10);
        g.printGraph();
    }
}
