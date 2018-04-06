import java.util.List;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Character> l = new ArrayList<Character>();
		
		char h = 'h';
		char e = 'e';
		char l1 = 'l';
		char l2 = 'l';
		char o = 'o';
		char w = 'w';
		char o2 = 'o';
		char r = 'r';
		char l3 = 'l';
		char d = 'd';
		l.add(d);
		l.add(l3);
		l.add(r);
		l.add(o2);
		l.add(w);
		l.add(o);
		l.add(l2);
		l.add(l1);
		l.add(e);
		l.add(h);
		l = reverse_order(l);
		for(int x = 0; x < l.size(); x++) {
			System.out.print(l.get(x));
		}
		
		
	}
	
	private static List reverse_order(List l) {
		
	
		
		for(int x = 0; x < (l.size()-1)/2; x++) {
			try {
				char c = (char)l.get(x);
				char d = (char)l.get(l.size()-1-x);
				l.set(x, d);
				l.set(l.size()-1-x, c);
			}
			catch(Exception e) {
				
			}
			
		}
		
		return l;
	}

}
