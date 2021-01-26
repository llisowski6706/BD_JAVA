//import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
//import sun.plugin2.main.client.WMozillaServiceDelegate;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Artykuly {


    DefaultTableModel dtm = new DefaultTableModel();
    JTable tab = new JTable(dtm){
        private static final long serialVersionUID = 1L;

        public boolean isCellEditable(int row, int column) {
            return false;
        };
    };
    JScrollPane scroll = new JScrollPane(tab);
    JButton wys = new JButton("Wyświetl paczki");
    JButton add = new JButton("Dodaj paczke");
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6;
    JTextField id, imie, nazwisko, adres, telefon, data, podszer, podwys;
    JLabel lab1, lab2, lab3, lab4, lab5, lab6, lbl_img, lab_status, labwys, labszer;
    JButton img, oblicz, ilosc;
    String filename = null;
    byte[] art_img = null;
    double szer1 = 0;
    double wys1 = 0;
    double pp = 0;
    double z = 0;



 //Panel pol txt do dodawania artykulów

    private JPanel txtpanel() {

        id = new JTextField(20);
        imie = new JTextField(20);
        nazwisko = new JTextField(20);
        adres = new JTextField(20);
        telefon = new JTextField(20);
        data = new JTextField(20);

        lab1 = new JLabel("Id");
        lab2 = new JLabel("Imie");
        lab3 = new JLabel("Nazwisko");
        lab4 = new JLabel("Adres");
        lab5 = new JLabel("Telefon");
        lab6 = new JLabel("Data");
        lab_status = new JLabel ("Status");

        JPanel txt = new JPanel();
        txt.setPreferredSize(new Dimension(200,350));
        txt.setBorder(BorderFactory.createTitledBorder("Wprowadzenie danych"));
        BoxLayout lay1 = new BoxLayout(txt, BoxLayout.Y_AXIS);
        txt.setLayout(lay1);

        id.setAlignmentX(Component.CENTER_ALIGNMENT);
        id.setSize(100,20);
        imie.setAlignmentX(Component.CENTER_ALIGNMENT);
        nazwisko.setAlignmentX(Component.CENTER_ALIGNMENT);
        adres.setAlignmentX(Component.CENTER_ALIGNMENT);
        telefon.setAlignmentX(Component.CENTER_ALIGNMENT);
        data.setAlignmentX(Component.CENTER_ALIGNMENT);

        lab1.setAlignmentX(Component.LEFT_ALIGNMENT);
        lab2.setAlignmentX(Component.LEFT_ALIGNMENT);
        lab3.setAlignmentX(Component.LEFT_ALIGNMENT);
        lab4.setAlignmentX(Component.LEFT_ALIGNMENT);
        lab5.setAlignmentX(Component.LEFT_ALIGNMENT);
        lab6.setAlignmentX(Component.LEFT_ALIGNMENT);
        lab_status.setAlignmentX(Component.LEFT_ALIGNMENT);

        txt.add(Box.createRigidArea(new Dimension(0, 20)));
        txt.add(lab1);
        txt.add(id);
        txt.add(Box.createRigidArea(new Dimension(0, 5)));
        txt.add(lab2);
        txt.add(imie);
        txt.add(Box.createRigidArea(new Dimension(0, 5)));
        txt.add(lab3);
        txt.add(nazwisko);
        txt.add(Box.createRigidArea(new Dimension(0, 5)));
        txt.add(lab4);
        txt.add(adres);
        txt.add(Box.createRigidArea(new Dimension(0, 5)));
        txt.add(lab5);
        txt.add(telefon);
        txt.add(Box.createRigidArea(new Dimension(0, 5)));
        txt.add(lab6);
        txt.add(data);
        txt.add(Box.createRigidArea(new Dimension(0, 5)));

        return txt;

    }
//Panel labelki do wyswietlania zdjecia

   /* private JPanel image(){
        lbl_img = new JLabel();

        lbl_img.setMinimumSize(new Dimension(200,200));
        lbl_img.setPreferredSize(new Dimension(300,400));
        lbl_img.setMaximumSize(new Dimension(300,400));
        JPanel img1 = new JPanel();
        img1.setPreferredSize(new Dimension(300,400));
        img1.setBorder(BorderFactory.createTitledBorder("Podgląd zdjęcia paczki"));
        BoxLayout lay2 = new BoxLayout(img1, BoxLayout.Y_AXIS);
        img1.setLayout(lay2);
        img1.add(lbl_img);

        return img1;
    } */

   //Panel buttonow odpowiedzialnych za dodawania i wyswietlanie bazy

    private JPanel btnpanel(){

        add = new  JButton("Dodaj paczki");
     //   img = new JButton("Wybierz zdjęcie");

        JPanel btn1 = new JPanel();
        btn1.setPreferredSize(new Dimension(150,130));
        btn1.setBorder(BorderFactory.createTitledBorder("Zarządzanie paczkami "));
        BoxLayout lay2 = new BoxLayout(btn1, BoxLayout.Y_AXIS);
        btn1.setLayout(lay2);

        add.setAlignmentX(Component.CENTER_ALIGNMENT);
    //    img.setAlignmentX(Component.CENTER_ALIGNMENT);
        wys.setAlignmentX(Component.CENTER_ALIGNMENT);


        btn1.add(Box.createRigidArea(new Dimension(0, 15)));
        btn1.add(wys);
        btn1.add(Box.createRigidArea(new Dimension(0, 5)));
        btn1.add(add);
        btn1.add(Box.createRigidArea(new Dimension(0, 5)));
     //   btn1.add(img);
        btn1.add(Box.createRigidArea(new Dimension(0, 5)));

        return btn1;
    }
//Panel z tabela artykolow

    private JPanel tablepan (){

        JPanel tabpan = new JPanel();


        dtm.addColumn("Id");
        dtm.addColumn("Imie");
        dtm.addColumn("Nazwisko");
        dtm.addColumn("Adres");
        dtm.addColumn("Telefon");
        dtm.addColumn("Data");
        dtm.addColumn("Status paczki");



        tabpan.setPreferredSize(new Dimension(700,400));
        tabpan.setBorder(BorderFactory.createTitledBorder("Tabela paczek"));
        BoxLayout layout1 = new BoxLayout(tabpan, BoxLayout.Y_AXIS);
        tab.setPreferredSize(new Dimension(460,360));
        tabpan.setLayout(layout1);
        tabpan.add(Box.createRigidArea(new Dimension(0,10)));
        tabpan.add(scroll);

        return tabpan;
    }
//Panel z componentami do liczenia ile materialu bedzie potrzebne

 /*   public JPanel licz(){
        podszer = new JTextField(20);
        podwys = new JTextField(20);
        labszer = new JLabel("Podaj szerokość powierzchni:");
        labwys = new JLabel("Podaj wysokość powierzchni:");

        JPanel iletw = new JPanel();
        iletw.setPreferredSize(new Dimension(200,120));
        iletw.setBorder(BorderFactory.createTitledBorder("Ile materiałów jest potrzebne"));
        BoxLayout lay = new BoxLayout(iletw, BoxLayout.Y_AXIS);
        iletw.setLayout(lay);

        podszer.setAlignmentX(Component.CENTER_ALIGNMENT);
        podwys.setAlignmentX(Component.CENTER_ALIGNMENT);
        labszer.setAlignmentX(Component.LEFT_ALIGNMENT);
        labwys.setAlignmentX(Component.LEFT_ALIGNMENT);

        iletw.add(Box.createRigidArea(new Dimension(0, 10)));
        iletw.add(labszer);
        iletw.add(podszer);
        iletw.add(Box.createRigidArea(new Dimension(0, 5)));
        iletw.add(labwys);
        iletw.add(podwys);
        iletw.add(Box.createRigidArea(new Dimension(0, 5)));

        return iletw;
    } */
/*
    public JPanel bntlicz(){
        oblicz = new JButton("Oblicz pole powierzchni");
        ilosc = new JButton("<html><center>Ilość<br /> potrzebnego materiału</center></html>");

        JPanel iletw1 = new JPanel();
        iletw1.setPreferredSize(new Dimension(200,120));
        iletw1.setBorder(BorderFactory.createTitledBorder("Obliczenia"));
        BoxLayout lay = new BoxLayout(iletw1, BoxLayout.Y_AXIS);
        iletw1.setLayout(lay);

        iletw1.add(Box.createRigidArea(new Dimension(0, 10)));
        iletw1.add(oblicz);
        iletw1.add(Box.createRigidArea(new Dimension(0, 5)));
        iletw1.add(ilosc);

        oblicz.setAlignmentX(Component.CENTER_ALIGNMENT);
        ilosc.setAlignmentX(Component.CENTER_ALIGNMENT);

        return iletw1;
    }
*/
    public void btnlicz(){
        oblicz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String wys = podwys.getText();
                    wys1 = Double.parseDouble(wys);
                    String szer = podszer.getText();
                    szer1 = Double.parseDouble(szer);


                    if(podszer.getText().isEmpty() & podwys.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Pola są puste!");
                }
                    else if (szer1==0 && wys1==0 ){
                        JOptionPane.showMessageDialog(null,"Wymiary są nieprawidłowe!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Dane zostały wprowadzone!");
                        //wys1 * szer1 = pp;
                        //z = pp/10000;
                        //z *=100;
                        //z = Math.round(z);
                        //z /=100;

                    }

                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            }
        });

    }

   // public double Liczenie(){
        //double liczbaa = szer1;
        //double liczbab = wys1;
        //liczbaa * liczbab = pp;
        //z = pp/10000;
        //z *=100;
        //z = Math.round(z);
        //z /=100;


        //return z;
    //}

//Button akcji odpowiedzialny za wybranie zdjecia z dysku

    public void btnimgAction(){

        img.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser wybor = new JFileChooser();
                wybor.showOpenDialog(null);
                File f = wybor.getSelectedFile();
                filename = f.getAbsolutePath();
                ImageIcon ic = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
                lbl_img.setIcon(ic);
                try{
                    File image = new File(filename);
                    FileInputStream fis = new FileInputStream(image);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    for (int readNum; (readNum=fis.read(buf)) !=-1;){
                        bos.write(buf,0,readNum);

                    }
                    art_img=bos.toByteArray();


                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

//Button odpowiedzialny za dodawanie do bazy

    private void btnaddAction() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Connection pol = polBD.programPolaczenie();
                    String id1 = id.getText();
                    String imie1 = imie.getText();
                    String nazwisko1 = nazwisko.getText();
                    String adres1 = adres.getText();
                    String telefon1 = telefon.getText();
                    String data1 = data.getText();
                    String sql = "INSERT into Paczki(id, imie, nazwisko, adres, telefon, data, status) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement pst = pol.prepareStatement(sql);
                    pst.setString(1, id1);
                    pst.setString(2, imie1);
                    pst.setString(3, nazwisko1);
                    pst.setString(4, adres1);
                    pst.setString(5, telefon1);
                    pst.setString(6, data1);
                    pst.setBytes(7, art_img);

                    if (id.getText().isEmpty() & imie.getText().isEmpty() & telefon.getText().isEmpty() & data.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Dane nie zostały dodane. Prosze uzupelnić dane !");
                    } else {
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Dane został dodane poprawnie !");


                    }

                    pol.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Błąd podczas dodwania danych do bazy");
                }
            }
        });

    }

//Dane pobierane z bazy danych do tabeli za pomoca arraylist

    public ArrayList<produkty> listatw(){

        ArrayList<produkty> listatw = new ArrayList<>();
        try {
            Connection pol = polBD.programPolaczenie();
            String zapyt = "SELECT * from Paczki";
            Statement st = pol.createStatement();
            ResultSet rs = st.executeQuery(zapyt);
            produkty prod;
            while(rs.next()){
                prod= new produkty(rs.getFloat("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("adres"), rs.getString("telefon"), rs.getString("data"), rs.getBytes("status"));
                listatw.add(prod);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        return listatw;
    }

//Button odpowiedzialny za wyswietlanie danych w tabeli

    public void btnActionWys(){
        wys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<produkty> lista = listatw();
                DefaultTableModel model = (DefaultTableModel) tab.getModel();
                Object[] row = new Object[6];
                for(int i=0;i<lista.size();i++){
                    row[0]=lista.get(i).getId();
                    row[1]=lista.get(i).getImie();
                    row[2]=lista.get(i).getNazwisko();
                    row[3]=lista.get(i).getAdres();
                    row[4]=lista.get(i).getTelefon();
                    row[5]=lista.get(i).getData();
                    model.addRow(row);

                }

            }
        });

    }

//Wybor wiersza ktorego ma sie wyswietlic zdjecie w labelce

    public void selRow(){
        ListSelectionModel model = tab.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    int i = tab.getSelectedRow();
                    TableModel model = tab.getModel();
                    byte[] img = (listatw().get(i).getFoto());
                    ImageIcon ic = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH));
                    lbl_img.setIcon(ic);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Podany towar nie ma zdjęcia!");
                }
            }
        });
    }

//Konstruktor Artykulow

        Artykuly(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame ramka = new JFrame("Wyświetlanie danych z bazy danych: ");
        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ramka.setResizable(false);
        panel1 = tablepan();
      //  panel2 = image();
        panel3 = txtpanel();
        panel4 = btnpanel();
    //    panel5 = licz();
     //   panel6 = bntlicz();
        ramka.setPreferredSize(new Dimension(1050,750));
        ramka.setLayout(new FlowLayout(FlowLayout.LEFT));
        ramka.add(panel1);
      //  ramka.add(panel2);
        ramka.add(panel3);
        ramka.add(panel4);
     //   ramka.add(panel5);
      //  ramka.add(panel6);
        ramka.pack();
        ramka.setVisible(true);
        btnActionWys();
     //   btnimgAction();
        btnaddAction();
        selRow();
      //  bntlicz();



    }




}