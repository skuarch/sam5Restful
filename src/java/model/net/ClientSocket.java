package model.net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.UnexpectedException;

/**
 * Stablish connection with a remote host.
 *
 * @author skuarch
 */
public class ClientSocket {

    private Socket socket = null;
    private String host = null;
    private int port;
    private int timeout;

    //==========================================================================
    /**
     * create a instance.
     */
    public ClientSocket() {
    } // end ClientSocket   

    //==========================================================================
    /**
     * create a instance with parameters.
     *
     * @param host String ip address, domain or hostname
     * @param port int number of port
     * @param timeOut int time for close the connection
     */
    public ClientSocket(String host, int port, int timeOut) {
        this.host = host;
        this.port = port;
        this.timeout = timeOut;
    } // end ClientSocket   

    //==========================================================================
    /**
     * create connection with the host.
     *
     * @throws UnknownHostException
     * @throws IOException
     */
    public void connect() throws UnknownHostException, IOException {
        
        if (host == null || host.length() < 1) {
            throw new NullPointerException("host is null");
        }

        if (port <= 1) {
            throw new NullPointerException("port is less than 0");
        }

        if (timeout < 1) {
            throw new NullPointerException("timeOut is less than 0");
        }

        socket = new Socket(host, port);        
        socket.setSoTimeout(timeout);
        
    } // end connect

    //==========================================================================
    /**
     * close socket
     */
    public void closeSocket() throws IOException {

        if (socket != null && socket.isConnected()) {
            socket.close();
        }

    } // end closeSocket

    //==========================================================================
    /**
     * validate if is possible to do the connection.
     *
     * @throws UnexpectedException
     */
    public void checkSocket() throws UnexpectedException {
        if (socket == null || socket.isClosed()) {
            throw new UnexpectedException("illegal operation socket is closed or null");
        }
    } // end checkSocket

    //==========================================================================
    /**
     * return socket
     *
     * @return Socket
     */
    public Socket getSocket() {
        return socket;
    }

    //==========================================================================
    /**
     * set socket
     *
     * @param socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    //==========================================================================
    /**
     * return host.
     *
     * @return String
     */
    public String getHost() {
        return host;
    }

    //==========================================================================
    /**
     * set host.
     *
     * @param host String
     */
    public void setHost(String host) {
        this.host = host;
    }

    //==========================================================================
    /**
     * get port.
     *
     * @return int
     */
    public int getPort() {
        return port;
    }

    //==========================================================================
    /**
     * set port
     *
     * @param port int
     */
    public void setPort(int port) {
        this.port = port;
    }

    //==========================================================================
    /**
     * get timeout.
     *
     * @return int
     */
    public int getTimeout() {
        return timeout;
    }

    //==========================================================================
    /**
     * set timeout
     *
     * @param timeout int.
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
} // end class 
