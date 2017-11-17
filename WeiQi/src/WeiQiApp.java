import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class WeiQiApp extends JFrame {
    private Board board;
    public WeiQiApp() {
    		Container c = getContentPane();
		App();
		handleEvents();
		setSize(650,650);
		setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void App() {
        Dimension expectedDimension = new Dimension(541, 541);
        board = new Board();
        board.setPreferredSize(expectedDimension);
        board.setMaximumSize(expectedDimension);
        board.setMinimumSize(expectedDimension);

        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(board);
        box.add(Box.createVerticalGlue());
        add(box, BorderLayout.CENTER);

 }
    
    private void handleEvents() {
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				int result = JOptionPane.showConfirmDialog(WeiQiApp.this, "Quit?",
				        "alert", JOptionPane.OK_CANCEL_OPTION);
				System.out.println(result);
				if (result == 0)
					System.exit(0);
				setVisible(true);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub	
			}	
		});
	}
}

class Board extends JPanel {
    private ArrayList<Piece> pieces = new ArrayList<>(1024);
    private ArrayList<int[]> locations = new ArrayList<>(1024);
    private String preColor = "white";

    private boolean state(int x , int y){
        for (int[] loc : locations) {
            if( loc[0] == x && loc[1] == y) {
                return true;
            }
        }
        return false;
    }

    Board() {
        addMouseListener(new MouseAdapter() {
            private Piece current;
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x_adjusted = (e.getX() + 15) / 30 * 30;
                int y_adjusted = (e.getY() + 15) / 30 * 30;
                
//                current = mode.create(x, y, e.getX(), e.getY());
                
                if (state(x_adjusted, y_adjusted)) {
                		message();
                } else {
                    Piece piece = new Piece(x_adjusted, y_adjusted, preColor);
                    pieces.add(piece);
                    int[] loc = new int[2];
                    loc[0] = x_adjusted; loc[1] = y_adjusted;
                    locations.add(loc);
                    preColor = piece.getColor();
                    repaint();
                }


            }
        });
    }

    public void message() {
    		JOptionPane.showMessageDialog(null, "This is a illegal move.");
    }
    
    public void paint(Graphics g) {
        setBackground(Color.ORANGE);
        
        g.drawRect(0, 0, 540, 540);
        for (int n = 1; n <= 17; n++) {
            g.drawLine(n * 30, 0, n * 30, 540);
            g.drawLine(0, n * 30, 5400, n * 30);
        }

        for (int i =3; i<=15; i+=6) 
        		for (int j =3; j<=15; j+=6){
        			g.fillOval(30*i-4, 30*j-4, 8, 8);
        }
        
        for (Piece s : pieces) {
            s.draw(g);
        }
    }

}
