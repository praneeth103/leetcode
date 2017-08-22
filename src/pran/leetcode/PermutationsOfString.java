package pran.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PermutationsOfString {
	
	public static void main(String[] args) {
		
		String s = "abcdef";
		List<List<Character>> result = new ArrayList<>();
		List<Character> temp = new ArrayList<>();
		helper(result,temp,s);
		System.out.println(result);
		

	}

	private static void helper(List<List<Character>> result, List<Character> temp, String s) {
		if(temp.size()==s.length()){
			result.add(new ArrayList(temp));
			//System.out.println(temp);
			return;
		}
		for(int i = 0; i < s.length();i++){
			if(temp.contains(s.charAt(i)))continue;
			temp.add(s.charAt(i));
			helper(result,temp,s);
			temp.remove(temp.size()-1);
		}
		
	}
}
