import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.RecipientService;
import service.impl.RecipientServiceImpl;

public class MailApp {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        RecipientService recipientService = context.getBean(RecipientServiceImpl.class);

        recipientService.addRecipient("Jeff", "jeff@gmail.com");
        recipientService.addRecipient("Mark", "mark@gmail.com");
        recipientService.addRecipient("Nick", "nick@gmail.com");

        //recipientService.doMailing("Special Promotion", "Hello! Here is our special offer: ...");

    }
}
