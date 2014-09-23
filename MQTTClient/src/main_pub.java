import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class main_pub {

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
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("You can type message now");
            Scanner scanner = new Scanner(System.in);
            content = scanner.nextLine();
            while(true)
            {
	            System.out.println("Publishing message: "+content);
	            MqttMessage message = new MqttMessage(content.getBytes());
	            message.setQos(qos);
	            sampleClient.publish(topic, message);
	            System.out.println("Message published");
	            content = scanner.nextLine();
	            if(content.equalsIgnoreCase("exit")) break;
            }
            sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);

        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
        
	}

}
