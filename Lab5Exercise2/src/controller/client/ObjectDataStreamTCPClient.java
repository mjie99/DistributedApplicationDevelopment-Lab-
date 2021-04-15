package controller.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import model.ItemProduct;

public class ObjectDataStreamTCPClient {

	public static void main(String[] args) {
		
		System.out.println("ClientSideApp: Demo of Object Stream\n");

		// Request data
		ItemProduct item = new ItemProduct();
		item.setName("Huawei XPro");
		item.setPrice(2500);
		
		
		try {
			
			// Data to establish connection to server
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(serverAddress, portNo);
			
			
			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
			
			
			// Send request to server
			System.out.println(" Send object to server: " + item.toString());
			objectOS.writeObject(item);
			objectOS.flush();
			
			// Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			
			
			// Get object from stream and display details
			item = (ItemProduct) objectIS.readObject();
			
			String result = (String)objectIS.readObject();
			
			System.out.println("\n Validation Result : "+ result+ "\n");
			System.out.print (" Product Id : " + item.getItemProductId() + "\n Product Name : " + item.getName() );
			System.out.print("\n Product Price : RM "+ item.getPrice()+"\n");
			
			
			// Close all closable objects
			objectOS.close();
			objectIS.close();
			socket.close();
			
		
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		

	}

}
