import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class TranslatorServerApp {

	public static void main(String[] args) {
		
		final int serverPort=4228;
		int bufferSize = 1024;
		
		ServerFrame serverFrame = new ServerFrame();
		serverFrame.setVisible(true);
		serverFrame.updateServerStatus(false);
		Translator translator;
		
		
		
		try {
			// Bind to the port
			DatagramSocket serverSocket = new DatagramSocket (serverPort);
		
			// Create a buffer to store data received from client 
			byte clientInputBuff [] = new byte [bufferSize];
			
			DatagramPacket inputPacket = new DatagramPacket(clientInputBuff, clientInputBuff.length);
		    System.out.println("Waiting for a client to connect...");
		    
		    // Receive data from the client and store in inputPacket
		    serverSocket.receive(inputPacket);
			
			// Get client language option
		    clientInputBuff = Arrays.copyOfRange(inputPacket.getData(), 0, inputPacket.getLength());	    
		    int  languageOption = Integer.parseInt (new String (clientInputBuff));    
		    serverFrame.updateRequestStatus("Language option in comboBox sent from the client: " + languageOption);			
		    
		    // Create a buffer to store data received from client 
		 	byte clientInputBuff2 [] = new byte [bufferSize];
		 			
		 	DatagramPacket inputPacket2 = new DatagramPacket(clientInputBuff2, clientInputBuff2.length);		    
		    serverSocket.receive(inputPacket2);
		    
		    // Get sentence option from client
		    clientInputBuff2 = Arrays.copyOfRange(inputPacket2.getData(), 0, inputPacket2.getLength());	    
		    int  textOption = Integer.parseInt (new String (clientInputBuff2));    
		    serverFrame.updateRequestStatus("Sentence option in comboBox sent from the client: " + textOption);
		
    
		    
		    // Send result to client
		    translator = new Translator();
		    String result = translator.chooseLanguage(languageOption, textOption);
		    
		    byte clientOutputBuff[] = result.getBytes();
		    
		    // Get client's address
		    InetAddress clientAddress = inputPacket.getAddress(); 
		 	int clientPort = inputPacket.getPort();
		 			
		 	// Create new UDP packet with data to send to the client
		 	DatagramPacket outputPacket = new DatagramPacket(clientOutputBuff,
		 			clientOutputBuff.length, clientAddress, clientPort);
		 			
		 			
		 	// Send the created packet to client
		 	serverSocket.send(outputPacket);
		 		    
		   
		 	// Close the socket connection
		 	serverSocket.close();
			
			
			
		
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		

	}

}
