import java.util.*;
public class Comparing implements Comparator<HuffmanNode>{

	public int compare(HuffmanNode x, HuffmanNode z){
		//System.out.println("Comparing: " + x.b + " " + z.b);
		//System.out.println("Comparing: " + x.a + " " + z.a);
		//For cases like note16.dvi page 20
		//Both d and c have the same frequencies so we'll organize them then lexicographical order
		if(x.b == z.b){
			if(Character.compare(x.a,z.a) < 0){
				return x.b;
			}
			else{
				return z.b;
			}
		}
		//System.out.println("Minus: " + (x.b - z.b));
		//Orders it by minHeap
		int subtraction = x.b - z.b;
		return subtraction;
	}
}