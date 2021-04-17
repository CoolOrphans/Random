import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Scanner;

public class EnglishToTwitchTranslator {
	public static String defaultText = "I am gay";
	public static String defaultEmote = "KappaPride";
	public static int defaultRepeat = 7;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		print("Text: ");
		String message = sc.nextLine();
		if (message.length() == 0) { // default text
			message = defaultText;
		}

		print("Emote: ");
		String emote = sc.nextLine();
		if (emote.length() == 0) {
			emote = defaultEmote;
		}

		print("Repeat: ");
		String Repeat = sc.nextLine();
		int repeat;
		if (Repeat.length() == 0) { // default repeat
			repeat = 5;
		} else {
			repeat = Integer.valueOf(Repeat);
		}

		message = message.toUpperCase(); // crucial
		// 50/50 for emote to go before message and vice versa
		message = Math.random() < .5 ? emote + " " + message : message + " " + emote;
		String result = "";
		for (int i = 0; i < repeat - 1; i++) {// repeats the message
			result += message + " ";
		}
		result += message; // no ending space

		println(result);
		copyToClipboard(result);
		// informs users that they can paste the message
		println("Your message has been copied to your clipboard! Paste (CTRL+V) to start chatting.");
		sc.close();
	}

	public static void copyToClipboard(String s) {
		Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable transferable = new StringSelection(s);
		clipBoard.setContents(transferable, null);
	}

	public static void print(Object o) {
		System.out.print(o.toString());
	}

	public static void println(Object o) {
		System.out.println(o.toString());
	}
}
