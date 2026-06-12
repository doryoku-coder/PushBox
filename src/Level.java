import java.util.ArrayList;
import java.util.List;

public class Level {
    private Map map;
    private Player player;
    private final List<Target> targetList;
    private final List<int[]> boxList;

    public Level(int levelNum) {
        map = new Map();
        map.loadLevel(levelNum - 1);
        targetList = new ArrayList<>();
        boxList = new ArrayList<>();
        initLevel();
    }

    private void initLevel() {
        char[][] data = map.getData();
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data[i].length; j++){
                char ch = data[i][j];
                if(ch == Map.PLAYER){
                    player = new Player(i, j);
                }
                if(ch == Map.TARGET){
                    targetList.add(new Target(i, j));
                }
                if(ch == Map.BOX){
                    boxList.add(new int[]{i, j});
                }
            }
        }
    }

    public boolean hasBox(int x, int y) {
        for(int[] box : boxList){
            if(box[0] == x && box[1] == y){
                return true;
            }
        }
        return false;
    }

    public void moveBox(int oldX, int oldY, int newX, int newY) {
        for(int[] box : boxList){
            if(box[0] == oldX && box[1] == oldY){
                box[0] = newX;
                box[1] = newY;
                break;
            }
        }
    }

    public boolean isWin() {
        int okCount = 0;
        for(int[] box : boxList){
            for(Target t : targetList){
                if(box[0] == t.getX() && box[1] == t.getY()){
                    okCount++;
                    break;
                }
            }
        }
        return okCount == targetList.size();
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public List<int[]> getBoxList() {
        return boxList;
    }
}