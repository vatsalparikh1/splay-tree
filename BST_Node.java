package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public BST_Node containsNode(String s){//if contains=> return this; if !contains => parent;
		if(data.equals(s))return this;
		if(data.compareTo(s)>0){//s lexiconically less than data
			if(left==null)return this;
			return left.containsNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null)return this;
			return right.containsNode(s);
		}
		return null; //shouldn't hit
  } //note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
  
  public BST_Node insertNode(String s){

	  
	  if(data.compareTo(s)>0){
			if(left == null){
				left=new BST_Node(s);
				left.par = this;
				return left;
			}
			return left.insertNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null){
				right=new BST_Node(s);
				right.par = this;
				return right;
			}
			return right.insertNode(s);
		}
		return this;//ie we have a duplicate
	  
  } //Really same logic as above note
  
  public boolean removeNode(String s){ 
	  return false; 
  } //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
  
  public BST_Node findMin(){ 
	  if(left!=null)return left.findMin();
		return this;
  } 
  
  public BST_Node findMax(){ 
	  if(right!=null)return right.findMax();
		return this;
  }
  
  public int getHeight(){ 
	  int l=0;
	  int r=0;
		if(left!=null)l+=left.getHeight()+1;
		if(right!=null)r+=right.getHeight()+1;
		return Integer.max(l, r);
  }

  public void splay() { 
	  while(this.par != null){
		  if(this.par.left == this){
			  rotateLeft(this);
		  }else if(this.par.right == this){
			  rotateRight(this);
		  }
	  }
	  
	  
  } //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
  

  // --- end example methods --------------------------------------

  private void rotateLeft(BST_Node c){ //when c is left child of parent
	  BST_Node p = c.par;
	  BST_Node g = p.par;
	  
	  if(g == null){ //no grandparent
		  p.left = c.right;
		  if(p.left!= null){
			  p.left.par = p;
		  }
		  c.par = p.par;
		  c.right = p;
		  p.par = c;
		  
	  }else if(g.right == p){//parent is a right child
		  g.right = c;
		  c.par = g;
		  p.left = c.right;
		  if(p.left != null){
			  p.left.par = p;
		  }
		  c.right = p;
		  p.par = c;
		  
	  }else if(g.left == p){//parent is a left child
		  g.left = c;
		  c.par = g;
		  p.left = c.right;
		  if(p.left != null){
			  p.left.par = p;
		  }
		  c.right = p;
		  p.par = c;
		
	  }
	  
	  
	  
  }
  
  private void rotateRight(BST_Node c){ //when c is right child of parent
	  BST_Node p = c.par;
	  BST_Node g = p.par;
	  
	  if(g == null){ //no grandparent
		  p.right = c.left;
		  if(p.right != null){
			  p.right.par = p;
		  }
		  c.par = p.par;
		  c.left = p;
		  p.par = c;
		  
	  }else if(g.right == p){ //parent is a right child
		  g.right = c;
		  c.par = g;
		  p.right = c.left;
		  if(p.right != null){
			  p.right.par = p;
		  }
		  c.left = p;
		  p.par = c;
		  
	  }else if(g.left == p){ //parent is a left child
		  g.left = c;
		  c.par = g;
		  p.right = c.left;
		  if(p.right != null){
			  p.right.par = p;
		  }
		  c.left = p;
		  p.par = c;
		  
	  }
	   
	  
  }
  

  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  
}
