import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

public class TranslatorClientApp {

	public static void main(String[] args) {
		
		final int serverPort=4228;
		int bufferSize = 1024;
		
		
		ClientFrame clientFrame = new ClientFrame();
		clientFrame.setVisible(true);
		
		
		try {
			
			DatagramSocket clientSocket = new DatagramSocket();
			
			// Get the IP address of server
			InetAddress serverAddress = InetAddress.getByName("localhost");
			
			
			clientFrame.waitForInputs();
			
			int language = clientFrame.getLanguage();
			int sentence = clientFrame.getSentence();
			
			// Create buffer to send data
			byte outputBuffer [] = Integer.toString(language).getBytes();
			
			// Create UDP Packet
			DatagramPacket  outputPacket = new DatagramPacket(outputBuffer, 
					outputBuffer.length, serverAddress, serverPort);		
			
			// Send client option to server
			clientSocket.send(outputPacket);
						
			// Buffer to send sentence option
			outputBuffer = Integer.toString(sentence).getBytes();
			
			// To reset the buffer
			outputPacket.setData(outputBuffer);
			
			clientSocket.send(outputPacket);
			
			
			
			// Receive translation result from server
			byte inputBuff [] = new byte [bufferSize];
			
			DatagramPacket inputPacket = new DatagramPacket (inputBuff, inputBuff.length);
			clientSocket.receive(inputPacket);
			
			// To get string with it original length without extra space
			inputBuff = Arrays.copyOfRange(inputPacket.getData(), 0, inputPacket.getLength());
			String result = new String (inputBuff);
			clientFrame.updateResultAns(result);
			
			
			clientSocket.close();
			
			
			
			
		} catch (InterruptedException | IOException e) {


			e.printStackTrace();
		}
		
		
		
		
		
	}

}
