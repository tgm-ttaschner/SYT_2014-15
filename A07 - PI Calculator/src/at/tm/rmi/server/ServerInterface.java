package at.tm.rmi.server;

import java.io.Serializable;
import java.net.URI;
import java.rmi.*;
import java.rmi.registry.Registry;

/**
 * @author Patrick Malik
 * @version 08.01.2015
 * 
 * Interface of the Server class.
 * This Interface extends the Interfaces Serializable and Remote.
 * It provides all essential methods for connection, PI calculation, name, registry and port management.
 */
public interface ServerInterface extends Remote, Serializable	{
	
	/**
	 * Starts a server on a given address with a given port.
	 * 
	 * @param balancer the URI the server runs on
	 * @throws RemoteException is thrown when a remote error occurrs (e.g. no connection to the server)
	 */
	public void connect(URI balancer) throws RemoteException;
	
	/**
	 * Getter for calculator.
	 * 
	 * @return the calculator object
	 * @throws RemoteException is thrown when a remote error occurrs (e.g. no connection to the server)
	 */
	public Calculator getCalc() throws RemoteException;
	
	/**
	 * Getter for name.
	 * 
	 * @return the servers name
	 * @throws RemoteException is thrown when a remote error occurrs (e.g. no connection to the server)
	 */
	public String getName() throws RemoteException;
	
	/**
	 * Setter for name.
	 * 
	 * @param name the name the server will have
	 * @throws RemoteException is thrown when a remote error occurrs (e.g. no connection to the server)
	 */
	public void setName(String name) throws RemoteException;
	
	/**
	 * Getter for registry.
	 * 
	 * @return the server registry
	 * @throws RemoteException is thrown when a remote error occurrs (e.g. no connection to the server)
	 */
	public Registry getRegistry() throws RemoteException;
	
	/**
	 * Setter for registry.
	 * 
	 * @param registry the servers registry
	 * @throws RemoteException is thrown when a remote error occurrs (e.g. no connection to the server)
	 */
	public void setRegistry(Registry registry) throws RemoteException;
	
	/**
	 * Getter for port.
	 * 
	 * @return the port the server runs on
	 * @throws RemoteException is thrown when a remote error occurrs (e.g. no connection to the server)
	 */
	public int getPort() throws RemoteException;
	
	/**
	 * Setter for port.
	 * 
	 * @param port the port the server will run on
	 * @throws RemoteException is thrown when a remote error occurrs (e.g. no connection to the server)
	 */
	public void setPort(int port) throws RemoteException;
}