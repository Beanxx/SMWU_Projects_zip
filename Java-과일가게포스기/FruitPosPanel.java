import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;



public class FruitPosPanel extends JPanel {

	JButton[] fb = new JButton[8];
	String menu[] = {
			"사과 최상(1개)","사과 중(1개)",
			"블루베리 최상(500g)","블루베리 상(500g)",
			"딸기 상(500g)","딸기 하(500g)",
			"수박 최상(1개)","수박 상(1개)"};

	int[] price = {
			1000, 600,
			8000, 7000,
			6000, 4000,
			10000, 8000};
	JTextField totalwon = new JTextField(30);
	JButton[] mb = new JButton[3];
	String[] mbstr = {"선택취소","전체취소","총주문액"};
	String [] ColName = {"Menu","Count","Price"};
	String [][] Data ;
	int count = 1;
	DefaultTableModel model = new DefaultTableModel(Data,ColName);
	JTable table = new JTable(model);
	
	class Screen extends JPanel{
		Screen(){
			setBackground(Color.WHITE);
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			table.setRowHeight(50);
			table.getTableHeader().setFont(new Font("궁서체", Font.BOLD, 15));
			add(new JScrollPane(table));
		}
	}
	
	class MenuBtn extends JPanel{
		MenuBtn(){
			setLayout(new GridLayout(4, 2, 3, 3));
			
			
			setBackground(Color.WHITE);
			for(int i=0;i<fb.length;i++) {
				fb[i]= new JButton(menu[i]);
				fb[i].setBackground(new Color(250, 244, 192));
				add(fb[i]);
			}
		}
	}
	
	class StrBtn extends JPanel{
		StrBtn(){
			setBackground(Color.WHITE);
			setLayout(new GridLayout(1, 3, 3, 3));
			
			for(int i=0;i<mbstr.length;i++) {
				mb[i]= new JButton(mbstr[i]);
				mb[i].setBackground(new Color(217, 229, 255));
				add(mb[i]);
			}
		}
	}
	
	public FruitPosPanel() {
		setLayout(null);
		setBackground(Color.WHITE);
		MenuBtn mbtn = new MenuBtn();
		StrBtn sbtn = new StrBtn();
		Screen sc = new Screen();
		

		totalwon.setSize(450, 70);
		totalwon.setLocation(50, 480);
		totalwon.setBackground(new Color(250, 225, 212));
		add(totalwon);
		
		sc.setSize(500, 500);
		sc.setLocation(25, 20);
		add(sc);
		
		mbtn.setSize(400, 430);
		mbtn.setLocation(530, 23);
		add(mbtn);
		
		sbtn.setSize(400, 70);
		sbtn.setLocation(530, 480);
		add(sbtn);


		
		
		for(int i=0;i<fb.length;i++) {
			final int index =i;
			fb[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton fb = (JButton)e.getSource();
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.addRow(new Object[]{menu[index],count,price[index]});
				}
			});
		}

			
		mb[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton fb = (JButton)e.getSource();
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				
				m.removeRow(table.getSelectedRow());
			}
		});
		
		
		mb[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton fb = (JButton)e.getSource();
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				
				m.setRowCount(0);
				totalwon.setText(String.valueOf(""));
			}
		});
		
		
		mb[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton fb = (JButton)e.getSource();
				int rowCont = table.getRowCount();
				int sum =0;
				for(int i=0;i<rowCont;i++) {
					sum += (int)table.getValueAt(i, 2);
				}
				totalwon.setText(String.valueOf( " " + sum + "원"));
				totalwon.setFont(new Font("함초롬바탕", Font.BOLD, 30));
			}
		});
	}
}

