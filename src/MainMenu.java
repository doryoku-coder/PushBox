import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
    private SwingGame game;

    public MainMenu() {
        setTitle("推箱子游戏 - 主菜单");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
        JLabel title = new JLabel("推箱子游戏", JLabel.CENTER);
        title.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(title);
        JButton btnStart = new JButton("开始游戏");
        JButton btnLevel = new JButton("选择关卡");
        JButton btnExit = new JButton("退出游戏");
        btnStart.addActionListener((ActionEvent e) -> {
            game = new SwingGame(this);
            game.startGame(1);
            setVisible(false);
        });
        btnLevel.addActionListener((ActionEvent e) -> {
            String input = JOptionPane.showInputDialog("请输入关卡（1-3）：");
            try {
                int lv = Integer.parseInt(input);
                if (lv >= 1 && lv <= 3) {
                    game = new SwingGame(this);
                    game.startGame(lv);
                    setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "只能输入 1-3！");
                }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "输入错误！请输入数字1-3");
            }
        });
        btnExit.addActionListener((ActionEvent e) -> System.exit(0));
        add(btnStart);
        add(btnLevel);
        add(btnExit);
    }

     public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
    }
}