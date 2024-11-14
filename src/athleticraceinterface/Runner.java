package athleticraceinterface;

public class Runner {
    private String name;
    private int speed;

    public Runner(String name) {
        this.name = name;
        this.speed = (int) (Math.random()*30)+1;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }
}
