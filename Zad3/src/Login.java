import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;

public class Login {

    JFrame ramka1;
    JLabel usr, pass;
    JTextField login;
    JPasswordField haslo; // password
    JButton zaloguj, wyjdz, dodaj;


    Login(){
        //tablica ikon
        Class<Login> klasa = Login.class;
        Icon[] ikony = { new ImageIcon(klasa.getResource("/wyjdz.png")),
                         new ImageIcon(klasa.getResource("/zaloguj.png")),
                         new ImageIcon(klasa.getResource("/nowe.png")),
        };
        //okno logowania
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame ramka1 = new JFrame("Logowanie: ");
        ramka1.setLayout(null);
        ramka1.setSize(350,250);


        login = new JTextField(20);
        login.setBounds(90,20,200,20);
        ramka1.add(login);

        usr = new JLabel("Login: ");
        usr.setBounds(50,20,50,20);
        ramka1.add(usr);

        haslo = new JPasswordField();
        haslo.setBounds(90, 45,200,20);
        ramka1.add(haslo);


        pass = new JLabel("Hasło: ");
        pass.setBounds(50,45,50,20);
        ramka1.add(pass);


        JButton zaloguj = new JButton("Zaloguj", ikony[1]);
        zaloguj.setBackground(Color.black);
        zaloguj.setForeground(Color.white);
        zaloguj.setFont(new Font("Tahoma",Font.BOLD, 8));
        zaloguj.setVerticalTextPosition(SwingConstants.BOTTOM);
        zaloguj.setHorizontalTextPosition(SwingConstants.CENTER);
        zaloguj.setBounds(35,80,90,100);
        ramka1.add(zaloguj);

        JButton wyjdz = new JButton("Wyjdź", ikony[0]);
        wyjdz.setBackground(Color.black);
        wyjdz.setForeground(Color.white);
        wyjdz.setFont(new Font("Tahoma",Font.BOLD, 8));
        wyjdz.setVerticalTextPosition(SwingConstants.BOTTOM);
        wyjdz.setHorizontalTextPosition(SwingConstants.CENTER);
        wyjdz.setBounds(130,80,90,100);
        ramka1.add(wyjdz);

        JButton dodaj = new JButton("Utwórz konto", ikony[2]);
        dodaj.setBackground(Color.black);
        dodaj.setForeground(Color.white);
        dodaj.setFont(new Font("Tahoma",Font.BOLD, 8));
        dodaj.setVerticalTextPosition(SwingConstants.BOTTOM);
        dodaj.setHorizontalTextPosition(SwingConstants.CENTER);
        dodaj.setBounds(225,80,90,100);
        ramka1.add(dodaj);


        ramka1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ramka1.setResizable(false);
        //ramka1.pack();


        ramka1.setLocationRelativeTo(null);
        ramka1.setVisible(true);

        zaloguj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        pol = polBD.programPolaczenie();
                        String sql = "SELECT * from dane where login=? and haslo=? ";
                        PreparedStatement zap = pol.prepareStatement(sql);
                        zap.setString(1, login.getText());
                        zap.setString(2, haslo.getText());
                        ResultSet wynik = zap.executeQuery();
                        if (wynik.next()) {
                            //new Program();
                            JOptionPane.showMessageDialog(null, "Zostałeś zalogowany !");
                            new Artykuly();
                            ramka1.dispatchEvent(new WindowEvent(ramka1, WindowEvent.WINDOW_CLOSING));

                        } else if (login.getText().isEmpty() & haslo.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "  Pola nie mogą byc puste ! \n            Uzupełnij pola !");
                        } else {
                            JOptionPane.showMessageDialog(null, "Login lub hasło są nieprawidłowe !");
                            login.setText("");
                            haslo.setText("");
                        }
                        pol.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                }
            });

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new newusr();
                ramka1.dispatchEvent(new WindowEvent(ramka1, WindowEvent.WINDOW_CLOSING));
            }
        });



    }
    private Connection pol = null;



}
