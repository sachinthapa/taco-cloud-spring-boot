package taco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class UserAccountAuthenticationSerializer extends JsonDeserializer<User> {

	@Override
	public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

		User userAccountAuthentication = new User();

		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);

		Iterator<JsonNode> elements = node.get("authorities").elements();
		while (elements.hasNext()) {
			JsonNode next = elements.next();
			System.out.println("next: " + next);
			JsonNode authority = next.get("authority");

			List<? extends GrantedAuthority> foos = new ArrayList<SimpleGrantedAuthority>();
//			userAccountAuthentication.getAuthorities().add(foos);
		}
		return userAccountAuthentication;
	}
}