package chat;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class index extends JFrame implements ActionListener {

    public static int opt;
    public static String end;

    public JButton btnOK;
    public Container container;
    public JTextField IP;
    JComboBox con;
    JLabel lbl;
    String[] conect = {"Selecione metodo ", "TCP", "UDP"};

    public index(String title) throws IOException {
        super(title);
        container = getContentPane();
        container.setLayout(new FlowLayout());
        btnOK = new JButton("Conectar");
        IP = new JTextField(10);
        con = new JComboBox(conect);
        lbl = new JLabel("IP:");
        con.setSelectedIndex(0);
        con.addActionListener(this);
        container.add(lbl);
        container.add(IP);
        container.add(con);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == con) {
            JComboBox cb = (JComboBox) event.getSource();
            String msg = (String) cb.getSelectedItem();
            String ip = IP.getText();
            switch (msg) {
                case "TCP":
                    opt = (1);
                    end = ip;
                    System.out.println(opt);

                    break;
                case "UDP":
                    opt = (2);
                    end = ip;
                    System.out.println(opt);
                    break;
                case "Selecione metodo": ;
                    break;
                default:
                    System.out.println("bo");
            }
        }
        /*
            TCPClient n = new TCPClient("TCP Cliente");
            n.setSize (480, 300);
            n.setVisible(true);
            System.out.println ("tcp" ); */
    }

    public static void main(String[] args) throws IOException {

        index i = new index("index");
        i.pack();
        i.setSize(200, 200);
        i.setVisible(true);

    }

}
