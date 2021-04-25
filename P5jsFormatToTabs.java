import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

// converts 2 spaces into tabs
public class P5jsFormatToTabs {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String del = "#"; // delimiter
		sc.useDelimiter(del);
		String s = sc.next();
		String r = "";
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.substring(i, i + 2).equals("  ")) {
				r += "\t";
				i++;
			} else {
				r += s.charAt(i);
			}
		}
		r += s.charAt(s.length() - 1); // get last character
		copyToClipboard(r);
		println("The reformatted string has been copied to your clipboard.");
		sc.close();
	}
	public static void copyToClipboard(String s) {
		StringSelection selection = new StringSelection(s);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
	}
	
	public static void print(Object o) {
		System.out.print(o.toString());
	}
	
	public static void println(Object o) {
		System.out.println(o.toString());
	}
}
