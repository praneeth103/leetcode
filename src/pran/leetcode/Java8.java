package pran.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8 {

	public static void main (String [] args ){
		
		List<String> array = Arrays.asList("eye","pad","air");
		array.stream().filter(s->!s.equals("eye")).collect(Collectors.toList()).forEach(System.out::println);
	}
}
