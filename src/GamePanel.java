import javax.swing.*;
import java.awt.*;
import java.util.List;
//导入自定义异常包
import exceptions.ResourceLoadException;

public class GamePanel extends JPanel {
    private final Image wallImg;
    private final  Image targetImg;
    private final Image boxImg;
    private final Image stickyBoxImg;
    private final Image playerImg;
    private final Image emptyImg;
    private Level level;
    public static final int CELL_SIZE = 99;
    

    public GamePanel(Level level) {
        this.level = level;
        setBackground(Color.WHITE);
        
        ImageIcon wallIcon = new ImageIcon("resources/Wall.png");
        if (wallIcon.getIconWidth() == -1) {
            throw new ResourceLoadException("图片资源加载失败：resources/Wall.png");
        }
        wallImg = wallIcon.getImage();
        
        ImageIcon targetIcon = new ImageIcon("resources/target.png");
        if (targetIcon.getIconWidth() == -1) {
            throw new ResourceLoadException("图片资源加载失败：resources/target.png");
        }
        targetImg = targetIcon.getImage();
        
        ImageIcon boxIcon = new ImageIcon("resources/box.jpg");
        if (boxIcon.getIconWidth() == -1) {
            throw new ResourceLoadException("图片资源加载失败：resources/box.jpg");
        }
        boxImg = boxIcon.getImage();
        
        ImageIcon stickyBoxIcon = new ImageIcon("resources/StickyBox.png");
        if (stickyBoxIcon.getIconWidth() == -1) {
            throw new ResourceLoadException("图片资源加载失败：resources/StickyBox.png");
        }
        stickyBoxImg = stickyBoxIcon.getImage();
        
        ImageIcon playerIcon = new ImageIcon("resources/Player.png");
        if (playerIcon.getIconWidth() == -1) {
            throw new ResourceLoadException("图片资源加载失败：resources/Player.png");
        }
        playerImg = playerIcon.getImage();
        
        ImageIcon emptyIcon = new ImageIcon("resources/empty.png");
        if (emptyIcon.getIconWidth() == -1) {
            throw new ResourceLoadException("图片资源加载失败：resources/empty.png");
        }
        emptyImg = emptyIcon.getImage();
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

    //调节面板大小
    @Override
    public Dimension getPreferredSize() {
        char[][] map = level.getMap().getData();
        int width = map[0].length * CELL_SIZE;
        int height = map.length * CELL_SIZE;
        return new Dimension(width, height);
    }
}
