/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apoio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author fef
 */
public class Servidor extends Thread {
    private String source;
    
    public Servidor() 
    {
        setDaemon(true);
    }

    public void send( String source ) 
    {
        this.source = source;
    }

    @Override
    public void run()
    {
        while ( true )
        {
            try 
            {
                Thread.sleep( 10 );
                
                if ( source != null ) 
                {
                    byte[] b = source.getBytes();
                    
                    InetAddress addr   = InetAddress.getByName( "224.0.0.2" );
                
                    DatagramSocket ds  = new DatagramSocket();
                    
                    DatagramPacket pkg = new DatagramPacket( b, b.length, addr, 5555 );
                    
                    ds.send( pkg );
                    
                    source = null;
                }
            }
            
            catch ( InterruptedException | IOException e )
            {
                System.err.println(e );
            }
        }
    }

}
