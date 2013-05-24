package model.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.rmi.UnexpectedException;
import model.net.ClientSocket;
import util.IOUtilities;

/**
 *
 * @author skuarch
 */
public class SocketClientProccessString extends ClientSocket {

    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;    

    //==========================================================================
    public SocketClientProccessString(String host, int port, int timeOut) {
        super(host, port, timeOut);    
    }    
    
    
    //==========================================================================
    /**
     * send a String to the remote host
     *
     * @param text
     */
    public void writeString(String text) throws UnexpectedException, IOException {

        if(text == null || text.length() < 1){
            throw new NullPointerException("text is null");
        }
        
        checkSocket();
        outputStream = getSocket().getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeUTF(text);
        objectOutputStream.flush();

    } // end writeString

    //==========================================================================
    /**
     * receive a String from the remote host
     *
     * @return String or null if the remote host doesn't response.
     * @throws UnexpectedException
     * @throws IOException
     */
    public String receiveString() throws UnexpectedException, IOException {

        checkSocket();
        String text = null;

        inputStream = getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        text = objectInputStream.readUTF();

        return text;

    } // end receiveString
    
    //==========================================================================
    /**
     * close connection.
     */
    public void closeConnection(){    
        IOUtilities.closeInputStream(inputStream);
        IOUtilities.closeInputStream(objectInputStream);
        IOUtilities.closeOutputStream(outputStream);
        IOUtilities.closeOutputStream(objectOutputStream);
        IOUtilities.closeSocket(getSocket());        
    } // end closeConnection
    
} // end class