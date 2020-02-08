import java.awt.EventQueue;
import javax.swing.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import java.util.Scanner;
import javax.swing.text.Segment;
import javax.swing.text.Document;
public class Cifrado {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cifrado window = new Cifrado();
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
	public Cifrado() {
		initialize();
	}
	public static char toCesar(int c, int cesar) {
	    if((char)c>='d' || (char)c>='D'  && (char)c<='z' || (char)c>='Z') {
	            
	            c-=cesar;
	    }

	        else if((char)c=='c' || (char)c>='C') {
	         c='z';
	        }

	        else if((char)c=='b' || (char)c>='B')   {
	            
	            c='y';
	        }  

	        else if((char)c=='a' || (char)c>='A')  {
	            
	            c='x';
	        }  else {
	            c=(char) c;
	        }

	    
	        return (char)c;
	    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 682, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Cesar");
		panel.add(lblNewLabel);
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		JTextArea textArea = new JTextArea();
		splitPane.setRightComponent(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		splitPane.setLeftComponent(textArea_1);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroCesar =(int)comboBox.getSelectedItem();
				
			}
		});
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Descifrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cesar=comboBox.getSelectedIndex();
				String cadena="";
				String texto=textArea_1.getText();
				for(int i=0;i<1000;i++) {
				
					char caracter = texto.charAt(i);
					int ascii = (int)caracter;
					String valor = toCesar(ascii,(int)comboBox.getSelectedItem())+"";
					cadena+=valor;
					textArea.setText(cadena);
				}
				
					
				
			}
		});
		
		panel.add(btnNewButton);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Abrir");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser abrir = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int fichero = abrir.showOpenDialog(null);
				
				if(fichero==JFileChooser.APPROVE_OPTION) {
					File selectedFile = abrir.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					try {
					File archivo=new File(path);
					Scanner leer = new Scanner(archivo);
					while(leer.hasNextLine()) {
						String data=leer.nextLine();
						textArea_1.append(data);
						textArea_1.append("\n");
					}
					
					leer.close();
					
					}catch (FileNotFoundException err) {
						err.printStackTrace();
					}
				}
				
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Guardar como...");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Examinar...");
				
				int userSelection = fileChooser.showSaveDialog(frame);
				
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					
				    File fileToSave = fileChooser.getSelectedFile();
				   
				    System.out.println("Guardar como: " + fileToSave.getAbsolutePath());
				    
				    try {
				    	
				    	FileWriter escribir = new FileWriter(fileToSave.getAbsolutePath());
				    	System.out.println(textArea.getText());
				    	escribir.write(textArea.getText());
				    	escribir.close();
				    	
				    } catch(IOException exc) {
				    	System.out.println("Imposible abrir el archivo para escribir");
				    }
				    
				    
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Editar");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Color fuente");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Color fondo");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Tipo fuente");
		mnNewMenu_1.add(mntmNewMenuItem_4);
	}

}
