package pran.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		
//		lengthOfLongestSubstring("abba");
//		TreeNode tn = getTree();
//		printPreOrder(tn);
//		levelOrder(tn);
//		ListNode ln  = reverseList(getList());
		

		
		
		

	}

	private static ListNode getList() {
		ListNode l1 = new ListNode(0);
		ListNode l2 = new ListNode(1);
		ListNode l3 = new ListNode(3);
		 l1.next = l2;
		 l2.next=l3;
		 return l1;
		
	}

	private static List<Integer> printPreOrder(TreeNode tn) {
		
		//iterative
		List<Integer> result = new ArrayList<>();
		if(tn==null) return result;
		Stack<TreeNode> stack =  new Stack<>();
		stack.push(tn);
		while(!stack.isEmpty()){
			TreeNode visiting  =  stack.pop();
			result.add(visiting.val);
			if(visiting.right!=null) stack.add(visiting.right);
			if(visiting.left!=null) stack.add(visiting.left);
			
		}
		return result;
				
	}
	
	public TreeNode constructTree(int [] preorder, int[] inorder){

		return helper(0,0,inorder.length-1,preorder,inorder);
	}
	
	private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
	    if (preStart > preorder.length - 1 || inStart > inEnd) {
	        return null;
	    }
		TreeNode root = new TreeNode(preorder[preStart]);
		int inIndex = 0;
		for(inIndex = inStart;inIndex<inEnd;inIndex++){
			if(preorder[preStart]==inorder[inIndex]) break;			
		}
		root.left = helper(preStart+1,inStart,inIndex-1,preorder,inorder);
		root.right = helper(preStart+inIndex-inStart,inIndex+1,inEnd,preorder,inorder);
		return root;
	}
	
    public int uniquePaths(int m, int n) {
        int [][] matrix = new int [m][n];
        for(int i=0;i<m;i++){
        	matrix[i][0]=1;
        }
        for(int j=0;j<n;j++){
        	matrix[0][j]=1;
        }
        for(int i=1;i<m;i++){
        	for(int j=1;j<n;j++){
        		matrix[i][j]=matrix[i-1][j]+matrix[i][j-1];
        	}
        }
        return matrix[m-1][n-1];
    }
    
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        
    	List<List<Integer>> result = new ArrayList<>();
    	List<Integer> currentResult = new ArrayList<>();
    	pathSumHelper(root,sum,currentResult,result);
    	return result;
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        List<List<Integer>> result = new ArrayList<>();
        
        while(!queue.isEmpty()){
        	int s = queue.size();
        	List<Integer> tempResult = new ArrayList<>();
        	for(int i = 0; i < s; i ++){
        		TreeNode element = queue.poll();
        		tempResult.add(element.val);
        		if(element.left!=null) queue.add(element.left);
        		if(element.right!=null)queue.add(element.right);
        	}
        	result.add(tempResult);
        }
        return result;
    }

	private static void pathSumHelper(TreeNode root, int sum, List<Integer> currentResult, List<List<Integer>> result) {
		
		if(root==null) return;
		currentResult.add(root.val);
		if(root.left==null&&root.right==null&&root.val==sum){
			
			result.add(currentResult);
			currentResult.remove(currentResult.size()-1);
			return;
		}
		else{
			pathSumHelper(root.left,sum-root.val,currentResult,result);
			pathSumHelper(root.right,sum-root.val,currentResult,result);		
		}
		currentResult.remove(currentResult.size()-1);
		
	}

	private static List<List<Integer>> levelOrder(TreeNode root) {
		
		List<List<Integer>> wrapList = new ArrayList<>();  
		if(root == null) return wrapList;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		
		
		if(!queue.isEmpty()){
			int cnt = queue.size();
			List<Integer> level = new ArrayList<>();
			for(int i = 0; i < cnt; i++){
				TreeNode current = queue.poll();
				level.add(current.val);
				if(current.left!=null){
					queue.add(current.left);
				}
				if(current.right!=null){
					queue.add(current.right);
				}
			}
			wrapList.add(level);
		}
		return wrapList;
		
	}

	private static TreeNode getTree() {
		
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		t1.right=t2;
		t2.left=t3;
		return t1;
	}
	
    public int climbStairs(int n) {

    	if(n==1)return 1;
    	if(n==2) return 2;
    	int oneStepBack = 2;
    	int twoStepsBack = 1;
    	int current = 3;
    	for(int i = 3; i <=n; i++){
    		current = oneStepBack+twoStepsBack;
    		twoStepsBack = oneStepBack;
    		oneStepBack = current;
    	}
    	return current;
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	
    	List<List<Integer>> result = new ArrayList<>();
    	List<Integer> temp = new ArrayList<>();
    	backTrackCombinationSum(candidates,result,temp,target,0);

		return result;
        
    }

	private void backTrackCombinationSum(int[] candidates, List<List<Integer>> result, List<Integer> temp, int remaining,
			int start) {
		if(remaining ==0){
			result.add(temp);
		}
		if(remaining<0) return;
		for(int i=start;i<candidates.length;i++){
			temp.add(candidates[i]);
			backTrackCombinationSum(candidates,result,temp,remaining-candidates[i],i);
			temp.remove(temp.size()-1);
		}
		
	}
	
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        subsetsBacktracker(result,temp,nums,0);
        return result;
    }

	private void subsetsBacktracker(List<List<Integer>> result, List<Integer> temp, int[] nums,int start) {
		result.add(new ArrayList<Integer>(temp));
		for(int i =start ; i <nums.length;i++){
			temp.add(nums[i]);
			subsetsBacktracker(result,temp,nums,i+1);
			temp.remove(temp.size()-1);
		}
		
	}
	
	public ListNode sortList(ListNode head) {
		if(head==null) return null;
		if(head.next == null) return head;
		ListNode slow = head;
		ListNode fast = head;
		ListNode prev = null;
		while(fast!=null &&fast.next!=null){
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		ListNode l1 = sortList(head);
		ListNode l2 =sortList(slow);
		return merge(l1,l2);
		
	}

	private ListNode merge(ListNode l1, ListNode l2) {
		ListNode n = new ListNode(0);
		ListNode head = n;
		while(l1!=null &&l2!=null){
			if(l1.val<l2.val){
				n.next=l1;
				l1=l1.next;
				n = n.next;
			}
			else{
				n.next=l2;
				l2=l2.next;
				n = n.next;
			}
			if(l1==null) n.next=l2;
			if(l2==null) n.next=l1;
		}
		return head.next;	
	}
	
	public ListNode insertionSortList(ListNode head) {
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = head;
		ListNode prev  = dummy;
		ListNode next = null;
		while(cur!=null ){
			next = cur.next;
			
			while(prev.next!=null&&prev.next.val<cur.val){
				prev= prev.next;
			}
			ListNode temp = prev.next;
			prev.next = cur;
			cur.next= temp;
			temp.next=next;
		}
		return dummy.next;
		
	}

	public static ListNode reverseList(ListNode head){
		
		if(head == null|| head.next == null) return head;
		
		ListNode second = head.next;
		head.next = null;
		ListNode reverseRest = reverseList(second);
		second.next = head;
		return reverseRest;
		
	}
	static int low;
	static int maxlen;
	public static String longestPalin(String s){
		
		for(int i = 0 ; i < s.length();i++){

			palindromeCheck(s,i,i);
			palindromeCheck(s,i,i+1);
			
		}
		return s.substring(low, maxlen);
	}

	private static void palindromeCheck(String s, int j,int k) {

		while(j>=0&&k<=s.length()-1&&s.charAt(j)==s.charAt(k)){
			j--;
			k++;
		}
		if(k-j-1>maxlen) {
			maxlen=k-j-1;
			low = j+1;
		}
		
	}
	
    public List<List<Integer>> combine(int n, int k) {
        
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combineBacktracker(result,temp,n,0,k);
        return result;
    }

	private void combineBacktracker(List<List<Integer>> result, List<Integer> temp, int n, int p,int k) {
		if(temp.size()==k) result.add(new ArrayList(temp));
		for(int i = p; i < n; i++){
			temp.add(i);
			combineBacktracker(result,temp,n,i+1,k);
			temp.remove(temp.size()-1);
			
		}
		
	}

	public ListNode reverseList2(ListNode head){
		
		if(head==null||head.next==null) return head;
		
		ListNode second = head.next;
		head.next=null;
		ListNode reverseRest = reverseList2(second);
		second.next = head;
		return reverseRest;
	}
	
    public List<Integer> preOrder(TreeNode root) {


    	List<Integer> result = new ArrayList<>();
    	Stack<TreeNode> stack = new Stack<>();
    	if(root==null) return result;
    	stack.push(root);
    	while(!stack.isEmpty()){

	    	TreeNode visiting = stack.pop();
	    	result.add(visiting.val);
	    	if(visiting.right!=null){
	    		stack.add(visiting.right);
	    	}
	    	if(visiting.left!=null){
	    		stack.add(visiting.left);
	    	}

    	}
    	
        return result;
    }
	
    public int[] twoSum(int[] nums, int target) {
        
    	HashMap<Integer, Integer> hmap = new HashMap<>();
    	
    	for(int i=0;i<nums.length;i++){
    		if(hmap.get(target-nums[i])!=null){
    			return new int[]{hmap.get(nums[i]),i};
    		}
    		hmap.put(nums[i], i);
    	}
    	return null;
    }
    
    public int maxDepth(TreeNode root) {
        
    	if(root == null) return 0;
    	
    	return 1+ Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    /* the basic idea is, keep a hashmap which stores the characters 
     * in string as keys and their positions as values, and keep 
     * two pointers which define the max substring. move the right 
     * pointer to scan through the string , and meanwhile update 
     * the hashmap. If the character is already in the hashmap, then move the left 
     * pointer to the right of the same character last found. Note that the two pointers can only move forward.
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
            	//abba (the second a is no longer a part of the current hashmap so j is still 3)
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }
    
    public int[] plusOne(int[] digits) {
    	int l = digits.length;
    	for(int j = l-1;j>0;j--){
    		if(digits[j]<9){
    			digits[j]++;
    			return digits;
    		}
    		digits[j]=0;
    	}
    	digits = new int [l+1];
    	digits[0]=1;
    	return digits;
    }
    
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }
    
    public int helper(TreeNode root, int curSum) {

    	if(root==null) return 0;
    	curSum = curSum*10+root.val;
    	if(root.left==null&&root.right==null){
    		return curSum;
    	}
    	return helper(root.left,curSum)+helper(root.right,curSum);
    }


	public int minPathSum(int[][] grid) {
    	for(int i = 1; i< grid.length;i++){
    		grid[i][0] = grid[i-1][0];
    	}
    	for(int j = 1; j< grid[0].length;j++){
    		grid[0][j] = grid[0][j];
    	}
    	for(int i= 0; i < grid.length;i++){
    		for(int j= 0; j < grid.length;j++){
    			grid[i][j]= Math.min(grid[i][j] + grid[i-1][j], grid[i][j] +grid[i][j-1] );
    		}
    	}
    	return grid[grid.length][grid[0].length];
        
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {

    	Stack<TreeNode> stack = new Stack<>();
    	List<Integer> list = new ArrayList<Integer>();
    	while(root!=null || !stack.isEmpty()){

    		while(root!=null){
    			stack.push(root);
    			root = root.left;
    		}
    		TreeNode e = stack.pop();
    		list.add(e.val);
    		root = e.right;
    		
    	}
    	return list;
    	
        
    }
    
    public static int lengthOfLongestSubstring2(String s) {
    	//comon
    	int len = 0;
    	int maxlen= 0;
    	HashMap<Character,Integer> hmap = new HashMap<>();
    		for(int i =0,j=0; i < s.length();i++){
    			char ch = s.charAt(i);
    			if(hmap.get(ch)==null){
    				
    				if(i-j>maxlen){
    					maxlen = len;
    				}
    			}
    			else{
    				j=i;
    			}
    			
    			
    		}
    }
    public int minSubArrayLen(int s, int[] nums) {
        
    }
    

		
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {
		val = x;
	}
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
