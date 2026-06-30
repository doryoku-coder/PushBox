import java.util.ArrayList;
import java.util.List;

public class StickyBox extends Box {
    public StickyBox(int x, int y) {
        super(x, y);
    }
    //判断是否箱子相邻
    public boolean isNextTo(Box other) {
        int dx = Math.abs(x - other.x);
        int dy = Math.abs(y - other.y);
        return dx == 1 && dy == 0 || dx == 0 && dy == 1;
    }
    
    //找到和粘贴箱相邻的箱子形成的组
    public void findConnectedGroup(List<StickyBox> group, List<StickyBox> visited, List<Box> allBoxes) {
        if (visited.contains(this)) {
            return;
        }
        visited.add(this);
        group.add(this);
        for (Box box : allBoxes) {
            if (box instanceof StickyBox && box != this) {
                StickyBox other = (StickyBox) box;
                if (isNextTo(other)) {
                    other.findConnectedGroup(group, visited, allBoxes);
                }
            }
        }
    }

    //返回findConnectedGroup找到的箱子组
    public List<StickyBox> getConnectedGroup(List<Box> allBoxes) {
        List<StickyBox> group = new ArrayList<>();
        List<StickyBox> visited = new ArrayList<>();
        findConnectedGroup(group, visited, allBoxes);
        return group;
    }
    
    //判断是否可以移动箱子组
    public boolean canMoveGroup(int dx, int dy, Map map, List<Box> allBoxes) {
        List<StickyBox> group = getConnectedGroup(allBoxes);
        for (StickyBox box : group) {
            int nx = box.x + dx;
            int ny = box.y + dy;
            if (map.isWall(nx, ny)) {
                return false;
            }
            boolean blocked = false;
            for (Box other : allBoxes) {
                if (other != this) {
                    if (!(other instanceof StickyBox) && other.getX() == nx && other.getY() == ny) {
                        blocked = true;
                        break;
                    }
                    if (other instanceof StickyBox && other.getX() == nx && other.getY() == ny) {
                        boolean isInGroup = false;
                        for (StickyBox b : group) {
                            if (b == other) {
                                isInGroup = true;
                                break;
                            }
                        }
                        if (!isInGroup) {
                            blocked = true;
                            break;
                        }
                    }
                }
            }
            if (blocked) {
                return false;
            }
        }
        return true;
    }
    //移动箱子组
    public void moveGroup(int dx, int dy, List<Box> allBoxes) {
        List<StickyBox> group = getConnectedGroup(allBoxes);
        for (StickyBox box : group) {
            box.x += dx;
            box.y += dy;
        }
    }
}
