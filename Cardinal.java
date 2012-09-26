public enum Cardinal{
    NORTH(0,-1),WEST(-1,0), SOUTH(0,1),  EAST(1,0);
    private int dx, dy;

    private Cardinal(int dx, int dy){
	this.dx = dx;
	this.dy = dy;
    }

    public int[] getValue(){
	int [] val = {dx, dy};
	return val;
    }

    public Cardinal turnRight(){
	return this.ordinal() < 3 ? Cardinal.values()[this.ordinal() +1] : Cardinal.values()[0];
    }

    public Cardinal turnLeft(){
	return this.ordinal() > 0 ? Cardinal.values()[this.ordinal() -1] : Cardinal.values()[3];
    }



}