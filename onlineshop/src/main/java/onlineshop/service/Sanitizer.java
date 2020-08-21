package onlineshop.service;

public class Sanitizer {
	/**
	 * Sanitize javaScript
	 *
	 * @param text the text
	 * @return the string
	 */
	public String sanitize(String text) {
		return text.replaceAll("<.*script.*>.*<\\/.*script.*>", "");
	}
}
