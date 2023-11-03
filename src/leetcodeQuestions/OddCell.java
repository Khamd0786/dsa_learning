package leetcodeQuestions;

public class OddCell {
	public static void main(String[] args) {
		 int[][] arr = {{0, 1}, {1, 1}};
	     System.out.println(oddCells(3, 2, arr));
	}
	
	   private static int oddCells(int m, int n, int[][] indices) {
	        int[][] arr = new int[n][m];
	        
	        for (int i = 0; i < indices.length; i++) {
	            for(int j = 0; j < indices[i].length; j++) {
	                int inc = 1;
	                // if (indices[i][j] > inc) {
	                //     inc = indices[i][j];
	                // }

	                if (j % 2 == 0) { //inc row
	                    for (int k = 0 ; k < m; k++) {
	                        arr[i][k]++;
	                    }
	                } else { // inc colum
	                    for (int k = 0; k < n; k++) {
	                        arr[k][i]++;
	                    }
	                }
	            }
	        }
	        
	        int count = 0;
	        for (int i = 0; i < arr.length; i++) {
	            for (int j = 0; j < arr[i].length; j++) {
	                if (arr[i][j] % 2 != 0) {
	                    count++;
	                }
	            }
	        }

	        return count;
	    }
}
