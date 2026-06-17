import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {
    private Image wallImg;
    private Image targetImg;
    private Image boxImg;
    private Image stickyBoxImg;
    private Image playerImg;
    private Image emptyImg;
    private Level level;
    public static final int CELL_SIZE = 99;
    
    public GamePanel(Level level) {
        this.level = level;
        setBackground(Color.WHITE);
        wallImg = new ImageIcon("wall.png").getImage();
        targetImg = new ImageIcon("target.png").getImage();
        boxImg = new ImageIcon("box.jpg").getImage();
        stickyBoxImg = new ImageIcon("box.jpg").getImage();
        playerImg = new ImageIcon("player.png").getImage();
        emptyImg = new ImageIcon("empty.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Map m = level.getMap();
        char[][] mapData = m.getData();
        Player player = level.getPlayer();
        List<Box> boxList = level.getBoxList();
        
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                char c = mapData[i][j];
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                if (c == '#') {
                    g.drawImage(wallImg, x, y, CELL_SIZE, CELL_SIZE, this);
                } else if (c == 'O') {
                    g.drawImage(targetImg, x, y, CELL_SIZE, CELL_SIZE, this);
                } else {
                    g.drawImage(emptyImg, x, y, CELL_SIZE, CELL_SIZE, this);
                }
            }
        }
        
        for (Box box : boxList) {
            int x = box.getY() * CELL_SIZE;
            int y = box.getX() * CELL_SIZE;
            if (box instanceof StickyBox) {
                g.drawImage(stickyBoxImg, x, y, CELL_SIZE, CELL_SIZE, this);
            } else {
                g.drawImage(boxImg, x, y, CELL_SIZE, CELL_SIZE, this);
            }
        }
        
        int px = player.getY() * CELL_SIZE;
        int py = player.getX() * CELL_SIZE;
        g.drawImage(playerImg, px, py, CELL_SIZE, CELL_SIZE, this);
    }

    public void updateLevel(Level level) {
        this.level = level;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        char[][] map = level.getMap().getData();
        int width = map[0].length * CELL_SIZE;
        int height = map.length * CELL_SIZE;
        return new Dimension(width, height);
    }
}
