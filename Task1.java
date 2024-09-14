interface Strategy {
    void move(char p1, char p2);
}

class Walk implements Strategy {
    @Override
    public void move(char p1, char p2) {
        System.out.println("The hero is walking from " + p1 + " to " + p2);
    }
}

class Fly implements Strategy {
    @Override
    public void move(char p1, char p2) {
        System.out.println("The hero is flying from " + p1 +" to " + p2);
    }
}

class Ride implements Strategy {
    @Override
    public void move(char p1, char p2) {
        System.out.println("The hero is riding from " + p1 + " to " + p2);
    }
}

class Hero {
    private Strategy strategy;
    
    public Hero(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public void move(char p1, char p2) {
        strategy.move(p1, p2);
    }
    
    public void setNewStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    
}



public class Task1
{
	public static void main(String[] args) throws Exception {
		Hero hero = new Hero(new Walk());
		hero.move('A', 'B');
		
		hero.setNewStrategy(new Fly());
		hero.move('B', 'C');
		
		hero.setNewStrategy(new Ride());
		hero.move('C', 'A');
	}
}



// Output:
// The hero is walking from A to B
// The hero is flying from B to C
// The hero is riding from C to A


