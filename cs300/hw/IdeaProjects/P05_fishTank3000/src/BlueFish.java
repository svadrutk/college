public class BlueFish extends Fish{
    public BlueFish(){
        super(2, "blue.png");
    }

    @Override
    public void swim() {

        if(super.getX() - super.speed() < 0 ) {
            super.setX((super.getX() - super.speed()) + TankObject.tank.width);
        }
        else {
            super.setX(super.getX() - super.speed());
        }

    }
}
