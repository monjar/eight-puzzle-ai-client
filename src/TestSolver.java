import java.util.Scanner;

public class TestSolver extends Solver {

    private Scanner in;

    public TestSolver(int size) {
        super(size);
        this.in = new Scanner(System.in);
    }

    @Override
    public String makeMove() {
        return in.nextLine();
    }
}
