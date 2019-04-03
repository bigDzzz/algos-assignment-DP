package ass1;

/**
 * Rod cutting problem described in Chapter 15 of textbook
 * Author: FengYu Zhang
 */
public class RodCutting {
  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
	 
	  int[] memo = new int [rodLength + 1];//create an array with the num of index same as length of the rod
	  
	  for(int i = 0; i < memo.length; i++) {
		  memo[i] = -1;
	  }
    
	  return memoizedRodCutting(rodLength, lengthPrices, memo);
  }
  
  public int memoizedRodCutting(int n, int[] p, int[] memo) {
	 
	  int ans;
	  
	  if(memo[n] >= 0) {
		  return memo[n];
	  }
	  
	  if(n == 0) {
		  ans = 0;
	  }
	  else {
		  ans = -1;
		  for(int i = 0; i < n; i++) {
			  ans = Math.max(ans, p[i] + memoizedRodCutting(n - i - 1, p , memo));
		  }
	  }
	  
	  memo[n] = ans;
	  
	  return ans;
  }
  
  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
	  int ans[] = new int[rodLength + 1]; 
      ans[0] = 0; 

      for (int i = 1; i <= rodLength; i++){ 
          int max_val = Integer.MIN_VALUE; 
          for (int j = 0; j < i; j++) {
              max_val = Math.max(max_val, lengthPrices[j] + ans[i-j-1]); 
          }
          ans[i] = max_val; 
      } 

      return ans[rodLength];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println(maxSell1Recur + " " + maxSell1Bottom);
      System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
