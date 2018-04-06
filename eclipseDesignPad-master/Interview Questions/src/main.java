
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int changes=0;
		int[] array = {1,2,2,3,4,5,6,6,7,8,8,9,10};
		int[] array2 = new int[0];
		for (int x = 0; x<array.length; x++) {
			try {
				if(array[x]==array[x+1]) {
					changes++;
					for(int y = x; y< array.length; y++) {
						try {
							array[y]=array[y+1];
						}
						catch(Exception e) {
						}
					}
					
				}
			}
			catch(Exception e) {}
			}
		if(changes%2!=0) {
			changes=changes+1;
		}
		array2 = new int[array.length-changes/2];
		for(int z=0; z<array2.length; z++) {
			array2[z]=array[z];
			System.out.print(array2[z]);
		}
		}
	}


