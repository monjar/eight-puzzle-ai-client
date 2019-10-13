public abstract class Solver {
    String[][] grid;
    int size;
    Solver(int size){
        this.size = size;
        this.grid = new String[size][size];
    }

    public void fillGrid(String gridString) {
        String[] lines = gridString.split("\n");
        for (int i = 0; i < size; i++) {
            grid[i] = lines[i].split("\t");
        }
    }

    public abstract String makeMove();


}
