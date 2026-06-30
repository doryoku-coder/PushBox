import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SwingGame extends JFrame {
    private GamePanel panel;
    private Level level;
    private int currentLevel;
    private final MainMenu mainMenu;

    public SwingGame(MainMenu menu) {
        this.mainMenu = menu;
        setTitle("推箱子");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_W) {
                    doPlayerMove(-1, 0);
                }

                if (key == KeyEvent.VK_S) {
                    doPlayerMove(1, 0);
                }

                if (key == KeyEvent.VK_A) {
                    doPlayerMove(0, -1);
                }

                if (key == KeyEvent.VK_D) {
                    doPlayerMove(0, 1);
                }

                if (key == KeyEvent.VK_R) {
                    restartLevel();
                }

                if (key == KeyEvent.VK_M) {
                    backToMenu();
                }
            }
        };
        addKeyListener(keyAdapter);
    }

    public void startGame(int levelNum) {
        currentLevel = levelNum;
        level = new Level(currentLevel);
        panel = new GamePanel(level);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();
    }

    private void doPlayerMove(int dx, int dy) {
        Player p = level.getPlayer();
        int nx = p.getX() + dx;
        int ny = p.getY() + dy;
        
        if (level.getMap().isWall(nx, ny)) return;
        
        if (level.hasBox(nx, ny)) {
            Box box = level.getBoxAt(nx, ny);
            if (level.isStickyBox(box)) {
                StickyBox stickyBox = (StickyBox) box;
                if (stickyBox.canMoveGroup(dx, dy, level.getMap(), level.getBoxList())) {
                    stickyBox.moveGroup(dx, dy, level.getBoxList());
                    p.move(dx, dy);
                }
            } else {
                int nnx = nx + dx;
                int nny = ny + dy;
                if (!level.getMap().isWall(nnx, nny) && !level.hasBox(nnx, nny)) {
                    box.setPosition(nnx, nny);
                    p.move(dx, dy);
                }
            }
        } else {
            p.move(dx, dy);
        }
        
        panel.repaint();
        
        if (level.isWin()) {
            JOptionPane.showMessageDialog(this, "恭喜通关！");
            backToMenu();
        }
    }

    private void restartLevel() {
        level = new Level(currentLevel);
        panel.updateLevel(level);
    }

    private void backToMenu() {
        mainMenu.setVisible(true);
        dispose();
    }
}
