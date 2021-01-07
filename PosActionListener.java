import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class PosActionListener implements ActionListener {
	FruitPosPanel fpp;
	
	public PosActionListener(FruitPosPanel fpp) {
		this.fpp =fpp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton MBtn = (JButton)e.getSource();
		DefaultTableModel m = (DefaultTableModel)fpp.table.getModel();
		for(int i=0;i<16;i++)
			m.addRow(new Object[]{fpp.menu[i],fpp.count,fpp.price[i]});
	}
}
