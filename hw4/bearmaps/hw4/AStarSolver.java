package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private ArrayHeapMinPQ<Vertex> minPQ = new ArrayHeapMinPQ<>();
    private List<WeightedEdge<Vertex>> shortestPath = new ArrayList<>();
    private HashMap<Vertex, Double> distTo = new HashMap<>();
    private HashMap<Vertex, Vertex> edgeTo = new HashMap<>();


    private AStarGraph<Vertex> graph;
    private Vertex start;
    private Vertex goal;
    private Stopwatch sw;
    private double timeout;
    private int exploredStates;
    private double timeTaken;
    private SolverOutcome outcome;


    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        sw = new Stopwatch();
        this.timeout = timeout;
        this.start = start;
        graph = input;
        goal = end;
        minPQ.add(start, 0 + heuristicEstimate(start));
        distTo.put(start, 0.0);
        Solver();


        timeTaken = sw.elapsedTime();

    }

    private void Solver() {
        while (minPQ.size() > 0) {
            if (minPQ.getSmallest().equals(goal)) {
                timeTaken = sw.elapsedTime();
                outcome = SolverOutcome.SOLVED;
                return;
            }
            timeTaken = sw.elapsedTime();
            if (timeTaken > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                return;
            }
            exploredStates++;
            Vertex curr = minPQ.removeSmallest();
            for (WeightedEdge<Vertex> v : graph.neighbors(curr)) {
                relax(v);
            }
        }
        timeTaken = sw.elapsedTime();
        outcome = SolverOutcome.UNSOLVABLE;

    }


    private double heuristicEstimate(Vertex v) {
        return graph.estimatedDistanceToGoal(v, goal);
    }

    private void relax(WeightedEdge<Vertex> e) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        double distToQ = distTo.containsKey(q) ? distTo.get(q) : Double.POSITIVE_INFINITY;
        if (distTo.get(p) + w < distToQ) {
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(q, p);
            if (minPQ.contains(q)) {
                minPQ.changePriority(q, distTo.get(q) + heuristicEstimate(q));
            } else {
                minPQ.add(q, distTo.get(q) + heuristicEstimate(q));

            }
        }
    }


    @Override
    public SolverOutcome outcome() {

        return outcome;
    }

    @Override
    public List<Vertex> solution() {

            Vertex v = goal;
            ArrayList<Vertex> path = new ArrayList<>();
            while (edgeTo.containsKey(v)) {
                path.add(v);
                v = edgeTo.get(v);
            }
            path.add(v);
            return path;
    }

    @Override
    public double solutionWeight() {
        double totalWeight = distTo.containsKey(goal) ? distTo.get(goal) : 0;
        return totalWeight;
    }

    @Override
    public int numStatesExplored() {
        return exploredStates;
    }

    @Override
    public double explorationTime() {
        return timeTaken;
    }
}
