import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class HelloWorldAgent extends Agent {
    protected void setup() {
        //Get the JADE runtime interface (singleton)
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        //Create a Profile, where the launch arguments are stored
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, "TestContainer");
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        //create a non-main agent container
        ContainerController container = runtime.createAgentContainer(profile);
        try {
            AgentController ag = container.createNewAgent("myagent1",
                    "com.company.HelloWorldAgent",
                    new Object[]{5});//arguments
            ag.start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
        Object[] obj = getArguments();
        int counter = Integer.parseInt(obj[0].toString());
        while (counter != 0) {
            System.out.println("Hello World! My name is " + getLocalName());
            counter--;
        }
        //doDelete();
    }
}