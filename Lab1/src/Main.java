import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        BestFirst s = new BestFirst();
        int[] jug1 = {sc.nextInt(), sc.nextInt(), sc.nextInt()};
        int[] jug2 = {sc.nextInt(), sc.nextInt(), sc.nextInt()};
        Iterator<BestFirst.State> it = s.solve(new Jug(jug1), new Jug(jug2));

        if(it == null)
            System.out.println("no solution was found");
            else {
                while(it.hasNext()) {
                    BestFirst.State i = it.next();
                    System.out.println(i);
                    if (!it.hasNext())
                        System.out.println(String.format("%.0f", i.getG()));
                }
            }
            sc.close();
    }
}
