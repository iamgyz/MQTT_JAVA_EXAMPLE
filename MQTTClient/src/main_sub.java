import java.io.IOException;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class main_sub {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String topic        = "gyzgyz";
        String content      = "Message from MqttPublishSample";
        //0(only one time) 1(at least one time) 2(exactly one time)
        int qos             = 2;
        //String broker       = "tcp://iot.eclipse.org:1883";
        String broker = "tcp://broker.mqtt-dashboard.com:1883";
        String clientId     = "JavaSample";
        MqttClient client;
		try {
			client = new MqttClient(broker,clientId);
			client.connect();
			System.out.println("Subscribing to topic '" + topic + "' from "+ client.getServerURI());
			// Subscribing to specific topic
			client.subscribe(topic);
			// It will trigger when a new message is arrived
			MqttCallback callback = new MqttCallback() {
				public void connectionLost(Throwable arg0) {
					System.out.println("Connection lost");
				}

				public void deliveryComplete(IMqttDeliveryToken arg0) {
					// TODO Auto-generated method stub
					
				}

				public void messageArrived(String arg0, MqttMessage arg1)
						throws Exception {
					// TODO Auto-generated method stub
					System.out.println("Message:" + new String(arg1.getPayload()));
					
				}
			};
			// Continue waiting for messages until the Enter is pressed
			client.setCallback(callback);
			System.out.println("Press <Enter> to exit");
			try {
				System.in.read();
			} catch (IOException e) {
				// If we can't read we'll just exit
			}
			client.disconnect();
			System.out.println("Client Disconnected");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
