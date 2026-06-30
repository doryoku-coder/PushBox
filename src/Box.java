public class Box {
    protected int x;
    protected int y;
    
    public Box(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    //设置箱子位置
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
