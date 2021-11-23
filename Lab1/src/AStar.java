import java.util.*;

class AStar {
    static class State {
        private Ilayout layout;
        private State father;
        private double g;
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if(father != null)
                g = father.g + l.getG();
            else g = 0.0;
        }
        public String toString() {
            return layout.toString();
        }
        public double getG() {
            return g;
        }
        public double getH(Ilayout goal) {
            return layout.getH(goal);
        }
        @Override
        public boolean equals(Object obj) {
            if(this == obj) {
                return true;
            }
            if(obj == null || getClass() != obj.getClass()) {
                return false;
            }
            State s = (State) obj;
            return layout.equals(s.layout);
        }

        @Override
        public int hashCode() {
                return super.hashCode();
        }
    }
    
    protected Queue<State> abertos;
    private State actual;

    final private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for(Ilayout e : children) {
            if(n.father == null || !e.equals(n.father.layout)) {
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        Queue<State> abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum((s1.getG() + s1.getH(goal)) - (s2.getG() + s2.getH(goal))));
        List<State> fechados = new ArrayList<>();
        abertos.add(new State(s, null));
        List<State> sucs;

        while (true) {
            if(abertos.isEmpty()) {
                System.exit(0);
            }
            actual = abertos.poll();
            if (actual.layout.isGoal(goal)) {
                List<State> solutions = new ArrayList<>();
                State temp = actual;
                for(; temp.father != null; temp = temp.father)
                    solutions.add(0, temp);
                solutions.add(0, temp);
                return solutions.iterator(); // print on client 
            } else {
                sucs = sucessores(actual);
                fechados.add(actual);
                for (State sucessor : sucs) {
                    if (!fechados.contains(sucessor))
                        abertos.add(sucessor);
                }
            }
        }
    }
}