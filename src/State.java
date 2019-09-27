import java.util.ArrayList;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 *
 *
 *
 */
public class State {

  private Square square;
  private State parent;

  // Maintain the gValue (the distance from start)
  // You may not need it for the BFS but you will
  // definitely need it for AStar
  private int gValue;

  // States are nodes in the search tree, therefore each has a depth.
  private int depth;

  /**
   * @param square
   *            current square
   * @param parent
   *            parent state
   * @param gValue
   *            total distance from start
   */
  public State(Square square, State parent, int gValue, int depth) {
    this.square = square;
    this.parent = parent;
    this.gValue = gValue;
    this.depth = depth;
  }

  /**
   * @param visited
   *            explored[i][j] is true if (i,j) is already explored
   * @param maze
   *            initial maze to get find the neighbors
   * @return all the successors of the current state
   */
  public ArrayList<State> getSuccessors(boolean[][] explored, Maze maze) {

    State state = new State(square, parent, gValue, depth);
    ArrayList<State> array = new ArrayList<>();
    if(!explored[state.getX()][state.getY()-1]) { //Check left
      if(maze.getSquareValue(state.getX(), state.getY()-1) != '%') {
        Square subSquare = new Square(state.getX(), state.getY()-1);
        State leftState = new State(subSquare, state, gValue+1, depth+1);
        array.add(leftState);
        //state = leftState;
      }
    }

    if(!explored[state.getX()+1][state.getY()]) { //Check bottom
      if(maze.getSquareValue(state.getX()+1, state.getY()) != '%') {
        Square subSquare = new Square(state.getX()+1, state.getY());
        State bottomState = new State(subSquare, state, gValue+1, depth+1);
        array.add(bottomState);
        //state = bottomState;
      }
    }

    if(!explored[state.getX()][state.getY()+1]) { //Check right
      if(maze.getSquareValue(state.getX(), state.getY()+1) != '%') {
        Square subSquare = new Square(state.getX(), state.getY()+1);
        State rightState = new State(subSquare, state, gValue+1, depth+1);
        array.add(rightState);
        //state = rightState;
      }
    }

    if(!explored[state.getX()][state.getY()-1]) { //Check left
      if(maze.getSquareValue(state.getX(), state.getY()-1) != '%') {
        Square subSquare = new Square(state.getX(), state.getY()-1);
        State leftState = new State(subSquare, state, gValue+1, depth+1);
        array.add(leftState);
        //state = leftState;
      }
    }

    if(!explored[state.getX()-1][state.getY()]) { //Check top
      if(maze.getSquareValue(state.getX()-1, state.getY()) != '%') {
        Square subSquare = new Square(state.getX()-1, state.getY());
        State topState = new State(subSquare, state, gValue+1, depth+1);
        array.add(topState);
        //state = topState;
      }
    }


    return array;

    // check all four neighbors in left, down, right, up order
    //  remember that each successor's depth and gValue are
    // +1 of this object.
  }

  /**
   * @return x coordinate of the current state
   */
  public int getX() {
    return square.X;
  }

  /**
   * @return y coordinate of the current state
   */
  public int getY() {
    return square.Y;
  }

  /**
   * @param maze initial maze
   * @return true is the current state is a goal state
   */
  public boolean isGoal(Maze maze) {
    if (square.X == maze.getGoalSquare().X
        && square.Y == maze.getGoalSquare().Y)
      return true;

    return false;
  }

  /**
   * @return the current state's square representation
   */
  public Square getSquare() {
    return square;
  }

  /**
   * @return parent of the current state
   */
  public State getParent() {
    return parent;
  }

  /**
   * You may not need g() value in the BFS but you will need it in A-star
   * search.
   *
   * @return g() value of the current state
   */
  public int getGValue() {
    return gValue;
  }

  /**
   * @return depth of the state (node)
   */
  public int getDepth() {
    return depth;
  }
}
