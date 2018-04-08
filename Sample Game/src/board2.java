import javax.swing.*;
import java.awt.*;

public class board2 {

JFrame frame;
JPanel squares[][] = new JPanel[8][8];

public board2() {
    frame = new JFrame("Simplified Chess");
    frame.setSize(500, 500);
    frame.setLayout(new GridLayout(8, 8));

    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            squares[i][j] = new JPanel();

            if ((i + j) % 2 == 0) {
                squares[i][j].setBackground(Color.black);
            } else {
                squares[i][j].setBackground(Color.white);
            }   
            frame.add(squares[i][j]);
        }
    }

    squares[0][0].add(new JLabel(new ImageIcon("rookgreen.png")));
    squares[0][2].add(new JLabel(new ImageIcon("bishopgreen.png")));
    squares[0][4].add(new JLabel(new ImageIcon("kinggreen.png")));
    squares[0][5].add(new JLabel(new ImageIcon("bishopgreen.png")));
    squares[0][7].add(new JLabel(new ImageIcon("rookgreen.png")));

    squares[7][0].add(new JLabel(new ImageIcon("rookred.png")));
    squares[7][2].add(new JLabel(new ImageIcon("bishopred.png")));
    squares[7][4].add(new JLabel(new ImageIcon("kingred.png")));
    squares[7][5].add(new JLabel(new ImageIcon("bishopred.png")));
    squares[7][7].add(new JLabel(new ImageIcon("rookred.png")));

    for (int i = 0; i < 8; i++) {
        squares[1][i].add(new JLabel(new ImageIcon("pawngreen.png")));
        squares[6][i].add(new JLabel(new ImageIcon("pawnred.png")));
    }

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}

public static void main(String[] args) {
    new board2();
}
}