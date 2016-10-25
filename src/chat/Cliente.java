/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

/**
 *
 * @author rodri
 */
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Cliente extends ChatGUI {
    static int n;
    static String ip;
    
 PrintWriter out;
    BufferedReader in;
    BufferedReader stdin;
    String inputLine, outputLine;
    public ButtonHandler btnHandler = new ButtonHandler();
    DatagramSocket socket;
   

     public Cliente (String title) throws SocketException
    {
        super (title);
        btnHandler = new ButtonHandler();
        btnEnviar.addActionListener(btnHandler );
        
        socket = new DatagramSocket (4466);
    }
   

     
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            if(n == 1) //TCP
            {
            String outputLine = txtMessagem.getText ();
            out.println (outputLine);
            txtHistoria.setText(txtHistoria.getText()+"\n Voce: "+ outputLine);
            txtMessagem.setText ("");
            }
            if(n == 2){ //UDP
                try
          {             
               DatagramSocket socket = new DatagramSocket ();
               byte[] buf = new byte[256];
 
               String outputLine = txtMessagem.getText ();
         
               buf = outputLine.getBytes ();
               InetAddress address = InetAddress.getByName (ip);
               DatagramPacket packet = new DatagramPacket (buf, buf.length, address, 4455);
               socket.send(packet);
               txtHistoria.setText(txtHistoria.getText()+"\n Voce: "+ outputLine);
               txtMessagem.setText ("");
          }
          catch (IOException e)
          {
          }
            }
        }
    }
   
    public void run () throws IOException{
        if(n == 1){
            Socket socket = new Socket (ip, 4444);
        BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
        out = new PrintWriter (socket.getOutputStream (), true);
       
        String inputLine;
       
        while ((inputLine = in.readLine ()) != null)
        {
            txtHistoria.setText (txtHistoria.getText () + "\n Servidor: " + inputLine);
        }
       
        out.close();
        in.close();
        socket.close();
        }
        if(n == 2){
            try
          {
               DatagramPacket packet;
               byte[] buf = new byte[256];
 
               while (true)
                     {
                    packet = new DatagramPacket (buf, buf.length);
                     socket.receive (packet);
                     String msgRecebida = new String (packet.getData());
                          txtHistoria.setText (txtHistoria.getText () + "\n" + msgRecebida);
               }
          }
          catch (IOException e)
          {
          }
        }
        
    }

 

    public static void main(String[] args) throws IOException
    {
        
        index i = new index("index");
        i.setSize(200, 200);
        i.setVisible (true);
        n = 0;
        
        while(n == 0)
        {
            n = i.opt;
            ip = i.end;
            System.out.println (n );
            
        }
        i.setVisible (false);
        Cliente f = new Cliente ("TCP Cliente");
       
        //f.pack ();
        f.setSize (480, 300);
        f.setVisible (true);
        
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
