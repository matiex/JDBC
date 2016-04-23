package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import baza.Baza;
import baza.Baza.Nauczyciel;
import baza.Baza.Uczen;
import javax.swing.JScrollBar;

public class View {

    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private Baza baza;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    View window = new View();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     * 
     * @throws SQLException
     */
    public View() throws SQLException {
	baza = new Baza();
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void newTextArea() {
	textArea = new JTextArea();
	textArea.setBounds(142, 66, 1024, 492);
	frame.getContentPane().add(textArea);

	JScrollPane scr = new JScrollPane(textArea,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scr.setBounds(184, 81, 982, 477);
	scr.setOpaque(false);
	scr.getViewport().setOpaque(false);
	frame.getContentPane().add(scr);
    }

    private void wyswietlPomoc() {
	textArea.append("Przyciski Pokaż uczniów i Pokaż Nauczycieli wyświetlają listy uczniów i nauczycieli. \n");
	textArea.append("Przycisk Dodaj ucznia wymaga wypełnienia pól Imię Nazwisko Płeć i Pesel. Dodaje ucznia o podanych danych. \n");
	textArea.append("Przycisk Usuń ucznia wymaga wypełnienia pola ID. Usuwa ucznia o podanym ID.  \n");
	textArea.append("Przycisk Edytuj ucznia wymaga wypełnienia pola ID. Zmienia nazwisko ucznia o podanym ID na Kowalski \n");
	textArea.append("Przyciski Licz nauczycieli i Licz uczniów zliczają odpowiednio nauczycieli i uczniów  \n");
    }

    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 1185, 608);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);

	newTextArea();

	textField_1 = new JTextField();
	textField_1.setBounds(142, 38, 200, 23);
	frame.getContentPane().add(textField_1);
	textField_1.setColumns(10);

	textField_2 = new JTextField();
	textField_2.setColumns(10);
	textField_2.setBounds(348, 38, 200, 23);
	frame.getContentPane().add(textField_2);

	textField_3 = new JTextField();
	textField_3.setColumns(10);
	textField_3.setBounds(554, 38, 200, 23);
	frame.getContentPane().add(textField_3);

	textField_4 = new JTextField();
	textField_4.setColumns(10);
	textField_4.setBounds(760, 38, 200, 23);
	frame.getContentPane().add(textField_4);

	textField_5 = new JTextField();
	textField_5.setColumns(10);
	textField_5.setBounds(966, 38, 200, 23);
	frame.getContentPane().add(textField_5);

	JTextPane txtpnId = new JTextPane();
	txtpnId.setText("ID");
	txtpnId.setBounds(142, 11, 200, 23);
	frame.getContentPane().add(txtpnId);

	JTextPane txtpnImie = new JTextPane();
	txtpnImie.setText("Imie ");
	txtpnImie.setBounds(348, 11, 200, 23);
	frame.getContentPane().add(txtpnImie);

	JTextPane txtpnNazwisko = new JTextPane();
	txtpnNazwisko.setText("Nazwisko");
	txtpnNazwisko.setBounds(554, 11, 200, 23);
	frame.getContentPane().add(txtpnNazwisko);

	JTextPane txtpnPlec = new JTextPane();
	txtpnPlec.setText("Plec");
	txtpnPlec.setBounds(760, 11, 200, 23);
	frame.getContentPane().add(txtpnPlec);

	JTextPane txtpnPesel = new JTextPane();
	txtpnPesel.setText("Pesel");
	txtpnPesel.setBounds(966, 11, 200, 23);
	frame.getContentPane().add(txtpnPesel);

	JButton btnPokazUczniow = new JButton("Pokaz uczniow");
	btnPokazUczniow.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    textArea.setText(" ");
		    baza.ShowStudents();
		    ArrayList<Uczen> u = baza.getUczList();
		    textArea.append("id \t imie \t nazwisko \n");
		    for (int i = 0; i < u.size(); i++) {
			textArea.append(u.get(i).getId() + " \t"
				+ u.get(i).getImie() + "\t "
				+ u.get(i).getNazwisko() + "\n");
		    }
		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});

	btnPokazUczniow.setBounds(10, 11, 122, 23);
	frame.getContentPane().add(btnPokazUczniow);

	JButton btnPokazNuczycieli = new JButton("Pokaz nuczycieli");
	btnPokazNuczycieli.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    textArea.setText(" ");
		    baza.SelectTeacher();
		    ArrayList<Nauczyciel> u = baza.getNauList();
		    textArea.append("id \t nazwisko \n");
		    for (int i = 0; i < u.size(); i++) {
			// System.out.println(u.get(i).getImie() + " " +
			// u.get(i).getNazwisko() + " " + u.get(i).getId() +
			// " ");
			textArea.append(u.get(i).getId() + "\t "
				+ u.get(i).getNazwisko() + "\n");
		    }
		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});

	btnPokazNuczycieli.setBounds(10, 45, 122, 23);
	frame.getContentPane().add(btnPokazNuczycieli);

	JButton btnDodajUcznia = new JButton("Dodaj Ucznia");
	btnDodajUcznia.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    textArea.setText(" ");
		    baza.AddStudent(textField_2.getText(),
			    textField_3.getText(), textField_4.getText(),
			    textField_5.getText());
		    textArea.append("Uczen zostal dodany do bazy danych.\n");

		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});

	btnDodajUcznia.setBounds(10, 75, 122, 23);
	frame.getContentPane().add(btnDodajUcznia);

	JButton btnUsunUcznia = new JButton("Usun Ucznia");
	btnUsunUcznia.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    textArea.setText(" ");
		    Integer a = new Integer(textField_1.getText());
		    baza.EraseStudent(a);
		    textArea.append("Uczen zostal usuni�ty\n");

		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});

	btnUsunUcznia.setBounds(10, 109, 122, 23);
	frame.getContentPane().add(btnUsunUcznia);

	JButton btnEdytujUcznia = new JButton("Edytuj Ucznia");
	btnEdytujUcznia.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    textArea.setText(" ");
		    Integer a = new Integer(textField_1.getText());
		    baza.UpdateStudent(a);
		    textArea.append("Uczeniowi o ID = " + a
			    + " zmieniono nazwisko na Kowalski\n");

		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});

	btnEdytujUcznia.setBounds(10, 143, 122, 23);
	frame.getContentPane().add(btnEdytujUcznia);

	JButton btnLiczUczniow = new JButton("Licz uczniow");
	btnLiczUczniow.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    textArea.setText(" ");
		    textArea.append("Liczba uczniow " + baza.StudentCounter());

		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});

	btnLiczUczniow.setBounds(10, 177, 122, 23);
	frame.getContentPane().add(btnLiczUczniow);
	
	JButton btnLiczNauczycieli = new JButton("Licz nauczycieli");
	btnLiczNauczycieli.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    textArea.setText(" ");
		    textArea.append("Liczba nauczycieli "
			    + baza.TeacherCounter());

		} catch (SQLException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});

	btnLiczNauczycieli.setBounds(10, 211, 122, 23);
	frame.getContentPane().add(btnLiczNauczycieli);
	
	JButton btnPomoc = new JButton("Pomoc");
	btnPomoc.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		textArea.setText(" ");
		wyswietlPomoc();
	    }
	});
	btnPomoc.setBounds(10, 245, 122, 23);
	frame.getContentPane().add(btnPomoc);

	wyswietlPomoc();

    }
}
