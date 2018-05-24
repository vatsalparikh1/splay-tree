package SPLT_A4;

public class SPLT implements SPLT_Interface{
 private BST_Node root;
	//public BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() {
    return root;
  }

@Override
public void insert(String s) {
	
	if (empty()) {
		root = new BST_Node(s);
		size += 1;
		root.justMade = false;
		return;
	}else{
	
	BST_Node toSplay = root.insertNode(s);
		//splay that
		toSplay.splay();
		
		root = toSplay;
		
		if(root.justMade){
			size += 1;
			root.justMade = false;
		}else{
			
			return;
		}
	
	}
	
	
}

@Override
public void remove(String s) {
	
	boolean inTree = contains(s);
	
	if(!inTree){ //if not in tree, nothing is removed
		return;
	}else{
		
		BST_Node leftSub = root.left;
			
		BST_Node rightSub = root.right;
		
		if(leftSub != null && rightSub != null){
			leftSub.par = null;
			rightSub.par = null;
			
			BST_Node maxLeft = leftSub.findMax();
			maxLeft.splay();
			maxLeft.right = rightSub;
			rightSub.par = maxLeft;
			root = maxLeft;
		}else if(leftSub != null && rightSub == null){
			leftSub.par = null;
			
			BST_Node maxLeft = leftSub.findMax();
			maxLeft.splay();
			maxLeft.right = rightSub;
			root = maxLeft;
		}else if (leftSub == null && rightSub != null){//if there is only rightSub
			rightSub.par = null;
			
			BST_Node minRight = rightSub.findMin();
			minRight.splay();
			minRight.left = leftSub;
			root = minRight;
			
		}else{//if there are no subtrees
			root = null;
		}
		
		
		size = size - 1;
		
		return;
		
	}
	
	
	
}

@Override
public String findMin() {
	
	if (empty()) {
		return null;
	} else {
		
		BST_Node minimum = root.findMin();
		minimum.splay();
		
		root = minimum;
		
		return minimum.data;
	}
	
}

@Override
public String findMax() {
	if(empty()){
		return null;
	}else{
		
		BST_Node maximum = root.findMax();
		maximum.splay();
		
		root = maximum;
		
		return maximum.data;
	}
	
}

@Override
public boolean empty() {
	if (size == 0) {
		return true;
	} else {
		return false;
	}
}

@Override
public boolean contains(String s) {
	if (empty()) {
		return false;
	}
	
	BST_Node lastAccessed = root.containsNode(s);
	lastAccessed.splay();
	root = lastAccessed;
	
	if (lastAccessed.data == s){
		return true;
	}else{
		return false;
	}

}

@Override
public int size() {
	return size;
}

@Override
public int height() {
	if (empty()) {
		return -1;
	} else {
		return root.getHeight();
	}
}  

}
