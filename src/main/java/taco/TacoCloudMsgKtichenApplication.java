package taco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class TacoCloudMsgKtichenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudMsgKtichenApplication.class, args);
	}

}
