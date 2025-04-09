package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class MyPanel extends JPanel implements ActionListener, KeyListener {
    private static final String IMAGES_PATH = "src/4gameImages";
    private final Field gameField = new Field();
    private boolean isPlayerOneTurn = true;
    private int gameStatus = 2;

    public MyPanel() {
        Timer repaintTimer = new Timer(5, this);
        repaintTimer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                if (gameStatus == 2) {
                    gameField.mouseClick(x);
                    gameStatus = gameField.getWinner();
                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGameElements(g);
    }

    public void drawGameElements(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Bitstream Charter", Font.BOLD, 20);
        g2.setFont(font);

        for (int i = 80; i < 641; i += 80) {
            g2.draw(new Line2D.Double(new Point2D.Double(i, 240), new Point2D.Double(i, 721)));
        }
        for (int i = 80; i < 561; i += 80) {
            g2.draw(new Line2D.Double(new Point2D.Double(80, i + 160), new Point2D.Double(641, i + 160)));
        }


        for (int i = 0; i < 7; i++) {
            Image img = new ImageIcon(IMAGES_PATH + "/" + (i + 1) + ".png").getImage();
            g2.drawImage(img, 80 + i * 80, 80, null);
            img = new ImageIcon(IMAGES_PATH + "/arrow.png").getImage();
            g2.drawImage(img, 80 + i * 80, 160, null);
        }

        int[][] gameBoard = gameField.getGameBoard();
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                if (gameBoard[row][col] != 0) {
                    Image img = new ImageIcon(IMAGES_PATH + "/" + gameBoard[row][col] + "coin.png").getImage();
                    g2.drawImage(img, 80 + col * 80, 240 + row * 80, null);
                }
            }
        }

        font = new Font("Bitstream Charter", Font.BOLD, 40);
        g2.setFont(font);
        g2.drawString("Move:", 720, 120);
        Image img = new ImageIcon(IMAGES_PATH + (isPlayerOneTurn ? "/1coin.png" : "/5coin.png")).getImage();
        g2.drawImage(img, 850, 70, null);

        if (gameStatus != 2) {
            g2.drawString("Game over", 700, 380);
            g2.drawString("Winner:", 690, 450);
            img = new ImageIcon(IMAGES_PATH + "/" + gameStatus + "coin.png").getImage();
            g2.drawImage(img, 850, 400, null);
        }

        // Display reset and exit instructions
        font = new Font("Bitstream Charter", Font.BOLD, 20);
        g2.setFont(font);
        g2.drawString("Press R to reset", 725, 510);
        g2.drawString("Esc to exit", 725, 535);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameStatus == 2) {
            int column = e.getKeyChar() - '0';
            if (column > 0 && column < 8) {
                if (gameField.makeMove(column - 1)) {
                    gameStatus = gameField.getWinner();
                }
            }
        }

        if (e.getKeyChar() == 'r') {
            gameField.resetGame();
            gameStatus = 2;
            repaint();

        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }
}