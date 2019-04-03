

/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
	public int glassFallingRecur(int floors, int sheets) {
        if (floors == 1 || floors == 0)  
            return floors;  
       
        if (sheets == 1)  
            return floors;  
      
        int min = Integer.MAX_VALUE;  
        int res;  
      
        for (int i = 1; i <= floors; i++) {
			res = Math.max(glassFallingRecur(i - 1, sheets - 1),//if glass sheets breaks
						   glassFallingRecur(floors - i, sheets));
            if (res < min)  
                min = res;  
        }  
      
        return min + 1;  
   }  
	
  public int glassFallingMemoized(int floors, int sheets,int[][] memo){
	
	  // check if answer already exist in memo, if so return the answer
			if (memo[floors][sheets] != 0) {
				return memo[floors][sheets];
			}
			
    //1.if the floor we're testing is 0th floor then there will be no answer(no trial need)
	//2.if there's only one floor we only need 1 trial
	//in both cases we do not care about the amount of glass sheets we have
			if (floors == 1 || floors == 0){
				memo[floors][sheets] = floors;
				 return floors; 
			}
	         
    //3.if there is only one glass sheet, we then try each floor with it untill it break
			if (sheets == 1) {
				memo[floors][sheets] = floors;
				return floors;
			}
			
			int solu = Integer.MAX_VALUE;
			int worstNumTrial;
			
	//test in the simulation we make up
			for (int i = 1; i <= floors; i++) {
				worstNumTrial = Math.max(glassFallingMemoized(i - 1, sheets - 1, memo),//if glass sheets breaks
										 glassFallingMemoized(floors - i, sheets, memo));//if glass sheets doesn't break
				if (worstNumTrial < solu)
					solu = worstNumTrial;
			}
	//worst num of trials plus the actual drop
			int actualDrop = solu + 1;
			
			memo[floors][sheets] = actualDrop;
			return actualDrop;
  }


  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
	  
	  int drop[][] = new int[sheets + 1][floors + 1];
	  
	  // If we have 0 floors we need 0 trials
      // If we have 1 floor we need 1 trial
		for (int i = 1; i < sheets; i++) {
			drop[i][1] = 1;
			drop[i][0] = 0;
		}
		
	  //If we only have one glass sheet then start trial from floor 1 to all the way up till it breaks
	  //this will give us the total level of floors in the worst case
		for (int j = 1; j < floors; j++) {
			drop[1][j] = j;
		}
		
	  //Solve the rest of the subproblems
	    for (int s = 2; s <= sheets; s++) {
	      for (int f = 2; f <= floors; f++) {
	    	  drop[s][f] = Integer.MAX_VALUE;

				int trial;
				for (int z = 1; z < f; z++) {
					trial = 1 + Math.max(drop[s - 1][z - 1], drop[s][f - z]);
					drop[s][f] = Math.min(trial, drop[s][f]);
				}
			}
		}
		return drop[sheets][floors];
  }
		


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();
      int [][] TP = new int [101][4];

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3, TP);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}

