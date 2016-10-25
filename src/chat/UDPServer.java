/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.*;
import java.awt.event.*;   
import java.io.*;
 
public class UDPServer extends ChatGUI
{
    PrintWriter out;
    BufferedReader in;
    BufferedReader stdin;
    String inputLine, outputLine;
    public ButtonHandler btnHandler = new ButtonHandler();
    DatagramSocket socket;
        
    public UDPServer (String title) throws IOException
    {
     super (title);
     btnHandler = new ButtonHandler ();
     btnEnviar.addActionListener (btnHandler);
 
     socket = new DatagramSocket (4455);
    }
 
    // Transmit Message
    public class ButtonHandler implements ActionListener
    {
     public void actionPerformed (ActionEvent event) //throws IOException
     {
          try
          {             
               DatagramSocket socket = new DatagramSocket ();
               byte[] buf = new byte[256];
 
               String outputLine = txtMessagem.getText ();
         
               buf = outputLine.getBytes ();
               InetAddress address = InetAddress.getByName ("127.0.0.1");
               DatagramPacket packet = new DatagramPacket (buf, buf.length, address, 4466);
               socket.send(packet);
               txtHistoria.setText(txtHistoria.getText()+"\n Voce: "+ outputLine);
               txtMessagem.setText ("");
          }
          catch (IOException e)
          {
          }        
     }
    }
   
    // Receive Message
    public void run () throws IOException
    {
          try
          {
               DatagramPacket packet;
               byte[] buf = new byte[256];
 
               while (true)
                     {
                     packet = new DatagramPacket (buf, buf.length);
                     socket.receive (packet);
                     String msgRecebida = new String (packet.getData());
                     txtHistoria.setText (txtHistoria.getText () + "\n Cliente: " + msgRecebida);
               }
          }
          catch (IOException e)
          {
          }        
    }
 
    public static void main(String[] args) throws IOException
    {
 
     UDPServer f = new UDPServer ("Server UDP");
    
     f.pack ();
     f.setSize (480, 300);
     f.show ();
    
     try
     {
          f.run ();
     }
     catch (IOException e)
     {
          System.exit(1);
     }
    }
}