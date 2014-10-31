/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wdhb.nms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JessVoig
 */
public class WDHBNMS {
    private static int networkSocketNumber = 1234;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while(true){
        if(args.length > 1){
        if(!args[0].equals("")){
            try{
                networkSocketNumber = Integer.valueOf(args[0]);
            }
            catch(NumberFormatException e){
                System.err.println(e);
            }
        }
        }
        try {
            ServerSocket sendSocket = new ServerSocket(networkSocketNumber);
            Socket clientSocket = sendSocket.accept();
            System.out.println("Connected to"+clientSocket.getLocalSocketAddress().toString());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.print("test");
            out.flush();
            String inputLine;
            while((inputLine = in.readLine()) != null){
                System.out.println(inputLine);
            }
            clientSocket.close();
        }
        
        catch (IOException ex) {
            System.err.println("Could not create socket for port: "+String.valueOf(networkSocketNumber));
            Logger.getLogger(WDHBNMS.class.getName()).log(Level.SEVERE, null, ex);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException exx) {
                Logger.getLogger(WDHBNMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
}
    
    

