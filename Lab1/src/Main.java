import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        BestFirst s = new BestFirst();

        String limit = sc.nextLine();
        int[] l = Stream.of(limit.split(" "))
                    .mapToInt(lim -> Integer.parseInt(lim))
                    .toArray();
        
        String jugs1 = sc.nextLine();
        int[] j1 = Stream.of(jugs1.split(" "))
                    .mapToInt(water1 -> Integer.parseInt(water1))
                    .toArray();

        String jugs2 = sc.nextLine();
        int[] j2 = Stream.of(jugs2.split(" "))
                    .mapToInt(water2 -> Integer.parseInt(water2))
                    .toArray();

        int dim = l.length;
        Iterator<BestFirst.State> it = s.solve(new Jug(j1, l, dim), new Jug(j2, l, dim));

        if(it == null)
            System.out.println("no solution was found");
            else {
                while(it.hasNext()) {
                    BestFirst.State i = it.next();
                    // System.out.println(i); This prints each step
                    if (!it.hasNext())
                        System.out.println(String.format("%.0f", i.getG()));
                }
            }

        sc.close();
    }
}
