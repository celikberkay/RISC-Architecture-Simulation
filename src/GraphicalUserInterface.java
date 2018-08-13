import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GraphicalUserInterface {
	InstructionSet iset = new InstructionSet();
	int click_count=0;
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JTable table_5;
	private JScrollPane scrollPane_6;
	private JTable table_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicalUserInterface window = new GraphicalUserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicalUserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		for (int i = 0; i < iset.display().length; i++) {
			iset.all_convert((String) iset.display()[i]);
		}
		
		
		frame = new JFrame();
		frame.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(563, 13, 171, 193);
		frame.getContentPane().add(scrollPane);
		
		Object[][] arr = new Object[10][1];
		for (int i = 0; i < 10; i++) {
			arr[i][0] = (String)iset.display()[i];
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			arr,
			new String[] {
				"Code"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		
		String[][] inst_arr = new String[32][1];
		for (int i = 0; i < 32; i++) {
			inst_arr[i][0] = iset.instruction_memory[i];
			System.out.println(inst_arr[i][0]);
		}
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(746, 13, 100, 280);
		frame.getContentPane().add(scrollPane_2);
		
		String [][] data_arr = new String[16][1];
		for (int i = 0; i < 16; i++) {
			data_arr[i][0] = iset.data_memory[i];
		}
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			data_arr,
			new String[] {
				"DM"
			}
		));
		scrollPane_2.setViewportView(table_2);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(746, 306, 100, 247);
		frame.getContentPane().add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"SM"
			}
		));
		scrollPane_3.setViewportView(table_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(24, 203, 234, 84);
		frame.getContentPane().add(scrollPane_4);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			iset.label_table,
			new String[] {
				"Name", "DEC", "HEX", "Binary"
			}
		));
		scrollPane_4.setViewportView(table_4);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(870, 13, 100, 540);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			inst_arr,
			new String[] {
				"IM"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(table_1);
		JButton btnDebug = new JButton("Debug");
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iset.run();
				table_5.setValueAt(iset.instruction_register.data, 5, 1);
				table_5.setValueAt(iset.program_counter.data, 1, 1);
				table_6.setValueAt("IR<-IM[PC]", 0, 1);
				table_6.setValueAt("PC<PC+1", 1, 1);
				table_6.setValueAt("D0..D15<-IR[9..6],  Q<-IR[10]=1, S2<-IR[1..0], S1<-IR[3..2], D<-IR[5..4]", 2, 1);
				btnDebug.setVisible(false);
			}
		});
		btnRun.setBounds(293, 12, 97, 25);
		frame.getContentPane().add(btnRun);
		
		
		btnDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				click_count++;
				if(click_count==1)
				{
					iset.t0();					
					table_5.setValueAt(iset.instruction_register.data, 5, 1);
					table_6.setValueAt("IR<-IM[PC]", 0, 1);
				}
				
				else if(click_count==2)
				{
					iset.t1();
					table_5.setValueAt(iset.program_counter.data, 1, 1);
					table_6.setValueAt("PC<PC+1", 1, 1);
				}
				else if (click_count==3) {
					iset.t2();
					table_6.setValueAt("D0..D15<-IR[9..6],  Q<-IR[10]=1, S2<-IR[1..0], S1<-IR[3..2], D<-IR[5..4]", 2, 1);
				}

			}
		});
		btnDebug.setBounds(402, 12, 97, 25);
		frame.getContentPane().add(btnDebug);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(24, 13, 234, 177);
		frame.getContentPane().add(scrollPane_5);
		
		Object[][] reg_arr = new Object[][]{{"AR",iset.address_register.data},{"PC",iset.program_counter.data}, {"SP",iset.stack_pointer.data},{"INPR",iset.input_register.data},{"OUTR",iset.output_register.data},{"IR",iset.instruction_register.data},{"R1",iset.register1.data},{"R2",iset.register2.data},{"R3",iset.register3.data}};
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
			reg_arr,
			new String[] {
				"Reg Name", "Value"
			}
		));
		
		
		scrollPane_5.setViewportView(table_5);
		
		scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(24, 518, 589, 102);
		frame.getContentPane().add(scrollPane_6);
		
		table_6 = new JTable();
		table_6.setModel(new DefaultTableModel(
			new Object[][] {
				{"T0", null},
				{"T1", null},
				{"T2", null},
				{"T3", null},
				{"T4", null},
			},
			new String[] {
				"Time", "Process"
			}
		));
		scrollPane_6.setViewportView(table_6);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frame.getContentPane(), scrollPane, table, scrollPane_2, table_2, scrollPane_3, table_3, scrollPane_4, table_4}));
		
		
		
	
		
		
	}
}
