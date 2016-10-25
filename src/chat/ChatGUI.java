/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.*;
import javax.swing.*;
 
class ChatGUI extends JFrame
{
    public JButton btnEnviar;
    public JTextArea txtHistoria;
    public JTextField txtMessagem;
    public JScrollPane pane;
    public Container container;
    String[] conect = { "Selecione metodo ","TCP", "UDP" };
   
    public ChatGUI (String title)
    {
        super (title);
       
        container = getContentPane ();
        container.setLayout (new FlowLayout());
       
        txtMessagem = new JTextField (40);
        txtHistoria = new JTextArea (12, 40);
        txtHistoria.setEditable (false);
        pane = new JScrollPane (txtHistoria);
        btnEnviar = new JButton ("Enviar");
        container.add (pane);
        container.add (txtMessagem);
        container.add (btnEnviar);
    }
}