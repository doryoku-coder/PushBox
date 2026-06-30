import java.util.ArrayList;
import java.util.List;

public class Level {
    private Map map;
    private Player player;
    private final List<Target> targetList;
    private final List<Box> boxList;
    
    public Level(int levelNum) {
        map = new Map();
        map.loadLevel(levelNum - 1);
        targetList = new ArrayList<>();
        boxList = new ArrayList<>();
        initLevel();
    }
    //初始化关卡
    private void initLevel() {
        char[][] data = map.getData();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                char c = data[i][j];
                if (c == Map.PLAYER) {
                    player = new Player(i, j);
                }
                if (c == Map.TARGET) {
                    targetList.add(new Target(i, j));
                }
                if (c == Map.BOX) {
                    boxList.add(new Box(i, j));
                }
                if (c == Map.STICKY_BOX) {
                    boxList.add(new StickyBox(i, j));
                }
            }
        }
    }
    //判断是否有箱子
    public boolean hasBox(int x, int y) {
        for (Box box : boxList) {
            if (box.getX() == x && box.getY() == y) {
                return true;
            }
        }
        return false;
    }
    //获取箱子坐标
    public Box getBoxAt(int x, int y) {
        for (Box box : boxList) {
            if (box.getX() == x && box.getY() == y) {
                return box;
            }
        }
        return null;
    }
    
    public boolean isStickyBox(Box box) {
        return box instanceof StickyBox;
    }
    
    public void moveBox(Box box, int dx, int dy) {
        if (box instanceof StickyBox) {
            StickyBox stickyBox = (StickyBox) box;
            if (stickyBox.canMoveGroup(dx, dy, map, boxList)) {
                stickyBox.moveGroup(dx, dy, boxList);
            }
        } else {
            int nx = box.getX() + dx;
            int ny = box.getY() + dy;
            if (!map.isWall(nx, ny) && !hasBox(nx, ny)) {
                box.setPosition(nx, ny);
            }
        }
    }
    
    public boolean isWin() {
        int count = 0;
        for (Box box : boxList) {
            for (Target target : targetList) {
                if (box.getX() == target.getX() && box.getY() == target.getY()) {
                    count++;
                    break;
                }
            }
        }
        return count == targetList.size();
    }
    
    public Map getMap() {
        return map;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public List<Box> getBoxList() {
        return boxList;
    }
}
