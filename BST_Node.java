package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; 
  boolean justMade; 
  
  
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

  

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  
  
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
		return null; 
  } 
  
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
	  
	  
  } 
  



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
  
  
  
}
