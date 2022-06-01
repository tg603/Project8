public class HuffmanNode{
char a;
int b;
HuffmanNode parent;
HuffmanNode left;
HuffmanNode right;
	public HuffmanNode(char a, int b){
		//this.data = data;
		this.a = a;
		this.b = b;
		parent = null;
		left = null;
		right = null;
	}
}