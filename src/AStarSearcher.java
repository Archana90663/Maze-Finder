import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 *
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

  /**
   * Calls the parent class constructor.
   *
   * @see Searcher
   * @param maze initial maze.
   */
  public AStarSearcher(Maze maze) {
    super(maze);
  }

  /**
   * Main a-star search algorithm.
   *
   * @return true if the search finds a solution, false otherwise.
   */
  @Override
  public boolean search() {

    // explored list is a Boolean array that indicates if a state associated with a given position in the maze has already been explored.
    boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
    // ...
    State state = new State(maze.getPlayerSquare(), null, 0,0);

    PriorityQueue<StateFValuePair> frontier = new PriorityQueue<StateFValuePair>();
    double fValue = Math.sqrt(Math.pow((state.getX()-maze.getGoalSquare().X),2) + Math.pow((state.getY() - maze.getGoalSquare().Y),2))+state.getGValue();
    StateFValuePair fValuePair = new StateFValuePair(state, fValue);
    frontier.add(fValuePair);

    // initialize the root state and add
    // to frontier list
    // ...

    while (!frontier.isEmpty()) {
      state = frontier.poll().getState();
      this.noOfNodesExpanded++;
      explored[state.getX()][state.getY()] = true;
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
      else {
        boolean same = false;
        Iterator<State> i = state.getSuccessors(explored, maze).iterator();
        while(i.hasNext()) {

          State n = i.next();
          double nFValue = Math.sqrt(Math.pow((n.getX()-maze.getGoalSquare().X),2) + Math.pow((n.getY() - maze.getGoalSquare().Y),2))+n.getGValue();
          StateFValuePair nFValuePair = new StateFValuePair(n, nFValue);
          if(!explored[n.getX()][n.getY()]) {
            explored[n.getX()][n.getY()] = true;
            for(StateFValuePair f: frontier) {
              if(f.getState().equals(n)) {
                same = true;

              }
              if(f.getState().equals(n) && f.getState().getGValue() > n.getGValue()) {
                frontier.add(nFValuePair);
                frontier.remove(f);
              }
            }

            if(same==false) {
              frontier.add(nFValuePair);
            }
            if(frontier.size() > this.maxSizeOfFrontier) {
              this.maxSizeOfFrontier = frontier.size();
            }

          }
        }
      }

    }
    return false;

  }


  // return true if a solution has been found
  // maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
  // maxDepthSearched, maxSizeOfFrontier during
  // the search
  // update the maze if a solution found

  // use frontier.poll() to extract the minimum stateFValuePair.
  // use frontier.add(...) to add stateFValue pairs

  // return false if no solution


}
