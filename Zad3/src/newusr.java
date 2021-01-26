import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;

public class newusr {

    JFrame ramka1;
    JLabel usr, pass;
    JTextField login, haslo;
    JButton dodaj, wyjdz;


    newusr(){
        //tablica ikon
        Class<Login> klasa = Login.class;
        Icon[] ikony = { new ImageIcon(klasa.getResource("/wyjdz.png")),
                new ImageIcon(klasa.getResource("/zaloguj.png")),
                new ImageIcon(klasa.getResource("/nowe.png")),
        };
        //okno logowania
        JFrame ramka1 = new JFrame("Dodawanie nowego użytkownika: ");
        ramka1.setLayout(null);
        ramka1.setSize(350,250);
        JFrame.setDefaultLookAndFeelDecorated(true);

        //pole loginu
        login = new JTextField(20);
        login.setBounds(90,20,200,20);
        ramka1.add(login);

        usr = new JLabel("Login: ");
        usr.setBounds(50,20,50,20);
        ramka1.add(usr);

        //pole hasla
        haslo = new JTextField();
        haslo.setBounds(90, 45,200,20);
        ramka1.add(haslo);

        pass = new JLabel("Hasło: ");
        pass.setBounds(50,45,50,20);
        ramka1.add(pass);

        //przycisk dodajacy nowego urzytkownika
        JButton dodaj = new JButton("<html><center>Dodaj<br /> użytkownika</center></html>", ikony[2]);
        dodaj.setBackground(Color.black);
        dodaj.setForeground(Color.white);
        dodaj.setFont(new Font("Tahoma",Font.BOLD, 8));
        dodaj.setVerticalTextPosition(SwingConstants.BOTTOM);
        dodaj.setHorizontalTextPosition(SwingConstants.CENTER);
        dodaj.setBounds(75,80,90,100);
        dodaj.setToolTipText("Po utworzeniu konta użytkownika zostaniesz przeniesiony do menu logowania");
        ramka1.add(dodaj);

        //przycisk powrotu do menu logowania
        JButton wyjdz = new JButton("Wróć", ikony[0]);
        wyjdz.setBackground(Color.black);
        wyjdz.setForeground(Color.white);
        wyjdz.setFont(new Font("Tahoma",Font.BOLD, 8));
        wyjdz.setVerticalTextPosition(SwingConstants.BOTTOM);
        wyjdz.setHorizontalTextPosition(SwingConstants.CENTER);
        wyjdz.setBounds(175,80,90,100);
        ramka1.add(wyjdz);


        ramka1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ramka1.setResizable(false);
        //ramka1.pack();


        ramka1.setLocationRelativeTo(null);
        ramka1.setVisible(true);

        //akcja po wcisnieciu przycisku dodania nowego uzytkownika
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    pol = polBD.programPolaczenie();
                    String user = login.getText();
                    String  pass = haslo.getText();
                    String sql =  "INSERT into dane VALUES ('"+user+"','"+pass+"')";
                    Statement wynik = pol.createStatement();
                    wynik.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, "Użytkownik został dodany !");
                    new Login();
                    ramka1.dispatchEvent(new WindowEvent(ramka1, WindowEvent.WINDOW_CLOSING));
                    pol.close();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Nazwa użytkownika jest już zajęta, prosze wybrać inny login");
                }

            }
        });
        //akcja klawisza wyjdz
        wyjdz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new  Login();
                ramka1.dispatchEvent(new WindowEvent(ramka1, WindowEvent.WINDOW_CLOSING));
            }
        });

    }



    private Connection pol = null;



}





