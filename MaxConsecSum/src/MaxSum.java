public class MaxSum {

	public static void main(String[] args) {
		 int[] input = new int[]{ 10, -2, 5, 6, -5 };
         int solution = Answer2(input);
         System.out.println(solution);
	}
	
    public static int Answer2(int input[]){
        
        int temp = 0;
        int max = 0;
        
        for(int i = 0; i < input.length - 1; i++){
            
            temp = input[i] + input[i + 1];
            
            if(temp >= max){
                max = temp;
            }
        }
        return max;
    }

}