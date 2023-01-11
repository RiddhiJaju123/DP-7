Problem-10 (Regular Expression Matching)
TC and SC- O(m*n)
Solution- 
  
class Solution
{
    public boolean isMatch(String s, String p)
    {
        if(s.equals(p)) return true;
        int s1=s.length();
        int p1=p.length();
        boolean dp[][]=new boolean[s1+1][p1+1];
        dp[0][0]=true;
        for(int j = 1; j < dp[0].length ; j++){
            //first row - we have a *
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2]; //2 steps back
            }else {
                dp[0][j] = false;
            }
        }

        for(int i = 1 ; i < dp.length ; i++){
            for(int j = 1 ; j < dp[0].length ; j++){
                //incoming character is regular character
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        // diagonal element
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                //incoming character is *
                else{
                    //zero case - 2 steps back
                    dp[i][j] = dp[i][j-2];

                    //one case - 2 steps back OR one step above
                    // check if preceding character is same
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'){
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[s1][p1];


        
    }
}