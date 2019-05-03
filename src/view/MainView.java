package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.dan.lt.BryTranslt;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainView {

	private JFrame frame;
	private JRadioButton rdbtnBinary;
	private JRadioButton rdbtnText;
	private ButtonGroup buttonGroup;
	private JTextArea textAreaBinary;
	private JTextArea textAreaHumanLanguage;
	private BryTranslt bryTranslt;
	private String text;
	private String binaries;
	private JLabel lblCampoBinrios;
	private JLabel lblCampoTexto;
	private JButton btnTranslate;
	private Border lineBorder;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
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
	public MainView() {
		initialize();
	}
	
	public int[] startSize() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (((int) dimension.getWidth()/2.1)+15);
		int height = (int) ((int) dimension.getHeight()/1.5);
		return new int[] {width, height};
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/binary-code.png")));
		frame.setTitle("Binary Translator");
		int[] dimension = startSize();
		frame.setSize(dimension[0], dimension[1]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JScrollPane scrollPaneBinary = new JScrollPane();
		
		btnTranslate = new JButton("Translate");
		btnTranslate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTranslate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				translateEvent();
			}
		});
		
		rdbtnBinary = new JRadioButton("Binary to Text");
		rdbtnBinary.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		rdbtnText = new JRadioButton("Text to Binary");
		rdbtnText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//Adding radio buttons to radio group
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnBinary);
		buttonGroup.add(rdbtnText);
		
		JScrollPane scrollPaneHumanLanguage = new JScrollPane();
		
		lblCampoBinrios = new JLabel("Campo Binários");
		
		lblCampoTexto = new JLabel("Campo Texto");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.CENTER)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
								.addComponent(scrollPaneBinary)
								.addComponent(scrollPaneHumanLanguage)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCampoTexto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(497))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnTranslate, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(rdbtnBinary, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnText, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
									.addGap(86)))
							.addGap(391))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblCampoBinrios, GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.CENTER)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(lblCampoBinrios, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(scrollPaneBinary, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblCampoTexto, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPaneHumanLanguage, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTranslate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(rdbtnBinary, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(rdbtnText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(53))
		);
		
		textAreaHumanLanguage = new JTextArea();
		textAreaHumanLanguage.setEditable(true);
		textAreaHumanLanguage.setEnabled(true);
		textAreaHumanLanguage.setLineWrap(true);
		textAreaHumanLanguage.setWrapStyleWord(true);
		textAreaHumanLanguage.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				scrollPaneHumanLanguage.setBorder(null);
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				scrollPaneHumanLanguage.setBorder(lineBorder);
			}
		});
		scrollPaneHumanLanguage.setViewportView(textAreaHumanLanguage);
		
		textAreaBinary = new JTextArea();
		textAreaBinary.setEnabled(true);
		textAreaBinary.setEditable(true);
		textAreaBinary.setLineWrap(true);
		textAreaHumanLanguage.setWrapStyleWord(true);
		textAreaBinary.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				scrollPaneBinary.setBorder(null);
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				scrollPaneBinary.setBorder(lineBorder);
			}
		});
		scrollPaneBinary.setViewportView(textAreaBinary);
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				textAreaBinary.update(textAreaBinary.getGraphics());
				textAreaHumanLanguage.update(textAreaHumanLanguage.getGraphics());
			}
		};
		
		Thread thread = new Thread(runnable);
        thread.start();
		
		frame.getContentPane().setLayout(groupLayout);
		
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
        SwingUtilities.updateComponentTreeUI(frame);
        
        bryTranslt = new BryTranslt(); 
        
        frame.setVisible(true);
              
	}
	
	public void translateEvent() {
		
		try {
			
			if(rdbtnBinary.isSelected()) {
				
				binaries = textAreaBinary.getText().toString();
				if(binaries.isEmpty() || binaries == null) {
					JOptionPane.showMessageDialog(null, "Insira Algum Texto Binário Para Iniciar!","Mensagem", JOptionPane.INFORMATION_MESSAGE);
				}else {
					textAreaHumanLanguage.setText(bryTranslt.getTextFromBinaries(binaries));
				}
				
			}else if(rdbtnText.isSelected()) {
				
				text = textAreaHumanLanguage.getText().toString();
				if(text.isEmpty() || text == null) {
					JOptionPane.showMessageDialog(null, "Insira Algum Texto Plano Para Iniciar!","Mensagem", JOptionPane.INFORMATION_MESSAGE);
				}else {
					System.out.println(bryTranslt.getBinariesFromText(text));
					textAreaBinary.setText(bryTranslt.getBinariesFromText(text));
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Selecionar uma das Opções Disponíveis para Prosseguir!", "Atenção", JOptionPane.WARNING_MESSAGE);
			}
			
		}catch (Exception e) {
			
			if(e instanceof java.lang.NumberFormatException) {
				JOptionPane.showMessageDialog(null, "Erro ao tentar Converter Valores, verificar se os capos foram alimentados corretamente!", "Atenção", JOptionPane.ERROR_MESSAGE);
			}else {				
				JOptionPane.showMessageDialog(null, "Selecionar uma das Opções Disponíveis para Prosseguir!", "Atenção", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

}
