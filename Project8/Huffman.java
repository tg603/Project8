import java.util.*;
public class Huffman{
	static char[] car;
	static int[] freq;
	static Object[] code;
	static String coding = "";
	static Stack<Integer> map = new Stack<Integer>();
	static Hashtable<Character, String> book = new Hashtable<Character, String>();
	
	
	// Variables to send to my GUI
	//Frequency Tree
	static String freqTree = "";
	//Huffman Tree
	static String huffMan = "";
	
	//Decode book
	static String[] Booker;
	
	//static String CookBook = "";
	//Encode
	static String Encoded = "";
	//Decode
	static String DecodedMes = "";
	
	
	public static HuffmanNode root;
	static char none;
	int key = 0;
	int count = 0;
	public Huffman(){
		root = null;
	}
	
	//Diver method for all the other methods
	public static void FaxBro(String thing){
		//Refreshes Global Variables that addwrite
		refresh();
		//Method Populates char[] car array with unique characters
		//Outprints Frequency Table as well
		intake(thing);
		//Creates new Huffman Tree from the frequency Table
		Huffman tree = new Huffman();
		//Insertion method creates Huffman Nodes and compresses it into all into a single root to unwind
		//Inside of my preTraverse method
		tree.root = insertion(car);
		tree.lvlTraverse(root);
		//System.out.println("huff is: " + huffMan);
		//preTraverse to create our codes and unwind the collapsed root
		tree.preTraverse(root);
		CodeBook();
		//System.out.println("Cook Book: ");
		
		//Ready to send back to GUI
		//System.out.println(CookBook);
		
		
		//Final code book
		System.out.println(book.toString());
		Encoded = Encode(thing);
		DecodedMes = Decode(Encoded);
		System.out.println("Encoded message: " + Encoded);
		System.out.println("Decoded message: " + DecodedMes);
		
		//return thing;
	}
	
	//Refreshes variables for retry's
	public static void refresh(){
	char[] car = new char[0];
	int[] freq = new int[0];
	Object[] code = new Object[0];
	coding = new String("");
	freqTree = new String("");
	huffMan = new String("");
	Encoded = new String("");
	DecodedMes = new String("");
	}
	
	//Encoded method
	public static String Encode(String egg){
		String cracked = "";
		for(int i = 0; i < egg.length(); i++){
			char temp = egg.charAt(i);
			String data = book.get(temp);
			cracked += data;
		}
		return cracked;
	}
	
	//Decoded method
	public static String Decode(String scrambled){
		String bites = "";
		String Egg = "";
		String[] codes = new String[freq.length];  
		for(int i = 0; i < car.length; i++){
			String temp = book.get(car[i]);
			codes[i] = temp;
		}
		for(int i = 0; i < scrambled.length(); i++){
			 char temper = scrambled.charAt(i);
			 bites += Character.toString(temper);
			 for(int j = 0; j < codes.length; j++){
					//System.out.println(bites + " | " + codes[j]);
				 if(codes[j].equals(bites)){
					 Egg += car[j];
					 bites = "";
				 }
			 }
		}
		//System.out.println(Egg);
		return Egg;
	}
	
	
	public static void CodeBook(){
		Booker = new String[car.length];
		Iterator<Map.Entry<Character, String>> road = book.entrySet().iterator();
		Map.Entry<Character, String> entry = null;
		System.out.println();
		while(road.hasNext()){
			entry = road.next();
			
			for(int i = 0; i < car.length; i++){
				if(car[i] == entry.getKey()){
					Booker[i] = entry.getValue();
				}
			}
			
			//CookBook +=  "|Character: " + entry.getKey() + "|" + "Code: " + entry.getValue() + "|\n";
			System.out.println( "|Character: " + entry.getKey() + "|" + "Code: " + entry.getValue() + "|");
		}
	}
	
	
	//PreOrder Traversal
	public void preTraverse(HuffmanNode node){
			if (node != null){
				//count = 0;
				if(node.parent != null){
						if(node.parent.left == node){
							map.push(0);
						}else if(node.parent.right == node){
							map.push(1);
						}
					}
					
					
				if(node.a != none){
				code = map.toArray();
				//coding = new int[code.length];
				for(int i = 0; i < code.length; i++){
					coding += code[i];
				}
				System.out.println("codes:" + coding);
				book.put(node.a,coding);
				}
				coding = "";
				System.out.print(map);
				System.out.print(node.a + ":" + node.b + " ");
				
				
				preTraverse(node.left);
				preTraverse(node.right);
				if(map.size() != 0){
				map.pop();
				}
				//map.poll();
				}
				/*else{
					if(count != 1){
					map.pop();
					count++;
					}
				}
				*/
			}

	//InOrder
	public void inTraverse(HuffmanNode node){
		int number = 0;
		if (node != null){
			inTraverse(node.left);
			for(int i = 0; i < car.length; i++){
				if(node.a == car[i]){
					number = i;
					break;
				}
			}
			System.out.print(node.a + ":" + freq[number] + " ");
			inTraverse(node.right);
		}
	}
	
	

	//Huffman Algorithm
	public static HuffmanNode insertion(char[] c){
		int k = 0;
		int height = c.length;
		//Create every key make it a node
		//A is the key and B is the value = weight and/or frequencies
		//Pop small amount of weights
		PriorityQueue<HuffmanNode> pQueue = new PriorityQueue<HuffmanNode>(height, new Comparing());
		for(int i = 0; i < c.length; i++){
			HuffmanNode node = new HuffmanNode(c[i], freq[i]);
			pQueue.add(node);
		}
		System.out.println(pQueue);
		for(int i = 0; i < height - 1; i++){
			HuffmanNode z = new HuffmanNode(none, 0);
			HuffmanNode x = pQueue.poll();
			//System.out.println("X NODE" + x.a + ":" + x.b);
			HuffmanNode y = pQueue.poll();
			//System.out.println("Y NODE" + y.a + ":" + y.b);
			z.b = x.b + y.b;
			System.out.println(z.a + ":" + z.b);
			z.left = x;
			x.parent = z;
			z.right = y;
			y.parent = z;
			pQueue.add(z);
		}
		System.out.println(root);
		root = pQueue.poll();
		//System.out.println(root.a + ":" + root.b);
		//System.out.println(root.right.b);
		return root;
	}
	
		
		
	//LevelOrder
	public void lvlTraverse(HuffmanNode node){
		int level = treeHeight(node);
		for(int i = 1; i <= level; i++){
			System.out.println("Level " + i + " ");
			huffMan += "Level " + i + " \n";
			printTravNodes(node, i);
			huffMan += "\n";
			System.out.println("\n");
		}
	}
	
	//NodesIndiviually
	public void printTravNodes(HuffmanNode node, int level){
		if(node == null){
			return;
		}
		if(level == 1){
			int number = 0;
			for(int i = 0; i < car.length; i++){
				if(node.a == car[i]){
					number = i;
					break;
				}
			}
			huffMan += "{" + node.a + ":" + node.b + "}" + " ";
			System.out.print(node.a + ":" + node.b + " ");
		}
		else if(level > 1){
			printTravNodes(node.left, level - 1);
			printTravNodes(node.right, level - 1);
		}
	}		
		
	public static int treeHeight(HuffmanNode node){
		if(node == null){
			return 0;
		}
			int lefty = treeHeight(node.left);
			int righty = treeHeight(node.right);
		
			if(lefty < righty){
				return righty + 1;
			}else{
				return lefty + 1;
			}
		}		
		
		
	//Makes the String into a char array
	//Populates both Arrays
	//Status of intake: DONE
	public static void intake(String everything){
		//Pull each character without duplicates into a Char[]
		//Integer array holds frequencies
		//Go through everything and compare with Char[] 
		System.out.println(everything);
		//Queue for seperated characters
		PriorityQueue<Character> pQueue = new PriorityQueue<Character>();
		//Making priority queue with singled out characters
		for(int i = 0; i < everything.length(); i++){
			if(pQueue.contains(everything.charAt(i))){
				
			}else{
				pQueue.add(everything.charAt(i));
			}
		}
		
		
		
		int size = pQueue.size();
		//System.out.println("Before" + pQueue);
		car = new char[size];
		//System.out.println("Before" + Arrays.toString(car));
		//Pulls each element into the car array
		for(int i = 0; i < car.length; i++){
			while(pQueue.size() != 0){
				car[i] = pQueue.poll();
				i++;
			}
		}
		//System.out.println("After" + Arrays.toString(car));
		//Now I have the car with each unique character
		freq = new int[car.length];
		int count = 0;
		while(count != freq.length){
			//System.out.println(count);
			for(int i = 0; i < everything.length(); i++){
				char begin = car[count];
				if(everything.charAt(i) == begin){
					//System.out.println(begin);
					freq[count] += 1;
				}
			}
			count++;
		}
		//System.out.println(Arrays.toString(freq));
		System.out.println("Frequency Table");
		for(int i = 0; i < car.length; i++){
			freqTree += "{" + car[i] + ":" + freq[i] + "}" + "\n";
			System.out.println(car[i] + ":" + freq[i]);
		}
		//System.out.println("FreqTree" + freqTree);
		//Now to check the frequencies of each element in the queue
		//Add them into our int array and compare it to the everything string
		//System.out.println(Arrays.toString(car));
		//System.out.println("After" + pQueue);
	}
	
	
	public static void main(String[] args){
		/*
		String stuff = "aaaaaabbcccdddeeeefffffffff";
		intake(stuff);
		System.out.println("Char[]" + Arrays.toString(car));
		System.out.println("Frequency[]" + Arrays.toString(freq));
		Huffman tree = new Huffman();
		tree.root = insertion(car);
		System.out.println("Huffman Tree:");
		tree.lvlTraverse(root);
		tree.preTraverse(root);
		CodeBook();
		System.out.println(book.toString());
		//CodeBook();
		String n = book.get('b');
		//String a = book.get("100");
		//System.out.println(n);
		String Encoded =  Encode(stuff);
		System.out.println("Encoded message: " + Encoded);
		System.out.println("Decoded message: " + Decode(Encoded));
		String example = "Hi How aRe YuO DoING!?";
		FaxBro(example);
		*/
		/*String stuff = "aaaaaabbcccdddeeeefffffffff";
		FaxBro(stuff);
		*/
	}
}