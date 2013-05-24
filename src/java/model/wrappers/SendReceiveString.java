package model.wrappers;

import java.io.IOException;
import java.rmi.UnexpectedException;
import model.net.SocketClientProccessString;

/**
 * SocketClientProccessString wrapper.
 * @author skuarch
 */
public class SendReceiveString {

    private SocketClientProccessString scps = null;

    //==========================================================================
    /**
     * create a instance.
     * @param host String
     * @param port int
     * @param timeOut  int
     */
    public SendReceiveString(String host, int port, int timeOut) {
        scps = new SocketClientProccessString(host, port, timeOut);
    }

    //==========================================================================
    /**
     * send and receive string.
     * @param text String
     * @return String or null
     * @throws UnexpectedException
     * @throws IOException 
     */
    public String sendReceive(String text) throws UnexpectedException, IOException {

        String dataReturned = null;

        try {
            
            scps.connect();
            scps.writeString(text);
            dataReturned = scps.receiveString();
            
        } catch (UnexpectedException ue) {
            throw ue;
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            scps.closeConnection();
        }

        return dataReturned;

    } // end sendReceive
} // end class