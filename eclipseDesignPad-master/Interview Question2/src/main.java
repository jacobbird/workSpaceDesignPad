
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,2,3,4,5,6,7};
		int[] array2 = new int[array.length];
		
		for(int x = 0; x<array.length; x++) {
			int product=1;
			for(int y = 0; y<array.length; y++) {
				if(y==x) {
				 
				}
				else {
					try {
						product=product*array[y];
					}
					catch(Exception e) {
						
					}
				}
			}
			array2[x]=product;
		}
		array = array2.clone();
		for(int z = 0; z<array.length; z++) {
			System.out.print(array2[z]+"\n");
		}
		
	}

}
