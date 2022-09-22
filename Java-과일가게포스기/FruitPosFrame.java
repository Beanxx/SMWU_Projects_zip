import javax.swing.JFrame;

public class FruitPosFrame extends JFrame {
	public FruitPosFrame() {
		setTitle("FruitPos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new FruitPosPanel());
		setSize(1000,700);
		setVisible(true);
	}
}


