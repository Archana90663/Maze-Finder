import java.util.Iterator;
import java.util.LinkedList;

/**
 * Breadth-First Search (BFS)
 *
 * You should fill the search() method of this class.
 */
public class BreadthFirstSearcher extends Searcher {

  /**
   * Calls the parent class constructor.
   *
   * @see Searcher
   * @param maze initial maze.
   */
  public BreadthFirstSearcher(Maze maze) {
    super(maze);
  }

  /**
   * Main breadth first search algorithm.
   *
   * @return true if the search finds a solution, false otherwise.
   */
  @Override
  public boolean search() {
    // explored list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been explored.
    boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
    State state = new State(maze.getPlayerSquare(), null, 0, 0);
    // ...

    // Queue implementing the Frontier list
    LinkedList<State> queue = new LinkedList<State>();
    queue.add(state);
    explored[state.getX()][state.getY()]=true;
    while (!queue.isEmpty()) {
      state = queue.poll();
      this.noOfNodesExpanded++; //Gets incremented after polling
      if(state.isGoal(maze)) {
        this.cost = state.getGValue();
        this.maxDepthSearched = state.getDepth();
        state = state.getParent();
        while(state.getParent()!=null) {
          maze.setOneSquare(state.getSquare(), '.');

          state = state.getParent();
        }
        return true;
      }
      Iterator<State> i = state.getSuccessors(explored, maze).iterator();

      while(i.hasNext()) {
        State n = i.next();
        if(!explored[n.getX()][n.getY()]) {
          explored[n.getX()][n.getY()] = true;
          queue.add(n);

          if(queue.size() > this.maxSizeOfFrontier) {
            this.maxSizeOfFrontier = queue.size();

          }


        }
      }
      // return true if find a solution
      // maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
      // maxDepthSearched, maxSizeOfFrontier during
      // the search
      // update the maze if a solution found

      // use queue.pop() to pop the queue.
      // use queue.add(...) to add elements to queue
    }


    return false;

    // return false if no solution
  }
}
