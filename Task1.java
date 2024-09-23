import java.util.Scanner;

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
	public static void main(String[] args) {
		Hero hero = new Hero(new Walk());
        Scanner scanner = new Scanner(System.in);
		
        while (true)
        {
            System.out.println("\nChoose the strategy:");
            System.out.println("1 - Walk");
            System.out.println("2 - Fly");
            System.out.println("3 - Ride");
            System.out.println("0 - Exit");


            int choice;

            try
            {
                choice = scanner.nextInt();
            }
            catch(Exception e)
            {
                System.out.println("Wrong...");
                scanner.nextLine();
                continue;
            }

            if (choice == 0) {
                System.out.println("Exit...");
                break;
            }

            switch (choice) {
                case 1:
                    hero.setNewStrategy(new Walk());
                    break;
                case 2:
                    hero.setNewStrategy(new Fly());
                    break;
                case 3:
                    hero.setNewStrategy(new Ride());
                    break;
                default:
                    System.out.println("Wrong choice");
                    continue;
            }

            System.out.print("Input the start point: ");
            char from = scanner.next().charAt(0);

            System.out.print("Input the end point: ");
            char to = scanner.next().charAt(0);

            hero.move(from, to);
        }

        scanner.close();
	}
}



// Output:
// Choose the strategy:
// 1 - Walk
// 2 - Fly
// 3 - Ride
// 0 - Exit
// 2
// Input the start point: A
// Input the end point: B
// The hero is flying from A to B


