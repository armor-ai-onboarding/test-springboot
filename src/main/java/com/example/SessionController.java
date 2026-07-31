package com.example;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	// Only these classes may be instantiated during deserialization.
	private static final Set<String> ALLOWED_CLASSES = new HashSet<>();
	static {
		ALLOWED_CLASSES.add("java.lang.String");
		ALLOWED_CLASSES.add("java.lang.Integer");
		ALLOWED_CLASSES.add("java.lang.Long");
		ALLOWED_CLASSES.add("java.lang.Boolean");
		ALLOWED_CLASSES.add("java.lang.Number");
	}

	/**
	 * An ObjectInputStream that only allows a strict whitelist of classes to be
	 * resolved, preventing arbitrary-class-instantiation / gadget-chain attacks
	 * when deserializing untrusted data.
	 */
	private static final class ValidatingObjectInputStream extends ObjectInputStream {
		ValidatingObjectInputStream(InputStream in) throws IOException {
			super(in);
		}

		@Override
		protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
			if (!ALLOWED_CLASSES.contains(desc.getName())) {
				throw new InvalidClassException("Unauthorized deserialization attempt", desc.getName());
			}
			return super.resolveClass(desc);
		}
	}

	@RequestMapping("/loadSession")
	public String load(@RequestParam("data") String data) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(data);
		try (ObjectInputStream in = new ValidatingObjectInputStream(new ByteArrayInputStream(bytes))) {
			Object obj = in.readObject();
			return obj.toString();
		}
	}
}
