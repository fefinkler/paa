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
import java.net.MulticastSocket;

/**
 *
 * @author fef
 */
public abstract  class Cliente extends Thread {

    public Cliente()
    {
        setDaemon(true);
    }

    
    public void send( String  source ) throws Exception
    {
        if ( source != null ) 
        {
            byte[] b = source.getBytes();

            InetAddress addr   = InetAddress.getByName( "224.0.0.2" );

            DatagramSocket ds  = new DatagramSocket();

            DatagramPacket pkg = new DatagramPacket( b, b.length, addr, 5555 );

            ds.send( pkg );
        }
    }
    
    public abstract void onRecive( String  data ) throws Exception;
    
    @Override
    public void run() 
    {
        MulticastSocket socket = null;
        
        try 
        {
            // determina endereco e porta
            InetAddress grupo = InetAddress.getByName( "224.0.0.2" );

            // cria multicast socket e se une ao grupo
            socket = new MulticastSocket( 5555 );
            
            socket.joinGroup( grupo );
        }
        
        catch ( IOException e )
        {
            System.err.println( e );
        }
        
        try
        {
            while ( true ) 
            {
                // prepara buffer (vazio)
                byte[] buf = new byte[256];

                // prepara pacote para resposta
                DatagramPacket pacote = new DatagramPacket( buf, buf.length );

                // recebe pacote
                socket.receive( pacote );

                System.out.println( new String( pacote.getData() ) );
//                onRecive( Serializer.deserialize( pacote.getData() ) );
            }
        }

        catch ( Exception ex )
        {
            System.err.println( ex );
        }
            
        // fecha socket
        socket.close();
    }
}
