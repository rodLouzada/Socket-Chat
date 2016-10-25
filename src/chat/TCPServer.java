/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;


import java.net.*;
import java.awt.event.*;
import java.io.*;
 
public class TCPServer extends ChatGUI
{
    PrintWriter out;
    public ButtonHandler btnHandler;
   
    public TCPServer (String title)
    {
        super (title);
        btnHandler = new ButtonHandler ();
        btnEnviar.addActionListener (btnHandler);
    }
   
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            String outputLine = txtMessagem.getText ();
            out.println (outputLine);
            txtHistoria.setText(txtHistoria.getText()+"\n Voce: "+ outputLine);
            txtMessagem.setText ("");
        }
    }
   
    public void run () throws IOException
    {
        ServerSocket serverSocket = new ServerSocket (4444);
        Socket clientSocket = serverSocket.accept ();
       
        out = new PrintWriter (clientSocket.getOutputStream (), true);
        BufferedReader in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream ()));
       
        String inputLine;
       
        while ((inputLine = in.readLine ()) != null)
        {
            txtHistoria.setText (txtHistoria.getText () + "\n Cliente:" + inputLine);
        }
       
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
   
   
    public static void main(String[] args) throws IOException
    {
        TCPServer f = new TCPServer ("TCP Servidor");
       
        f.pack ();
        f.setSize (480, 300);
        f.show();
        f.run ();
    }
}