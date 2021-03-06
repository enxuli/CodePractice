import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;


public class BinarySearch {
	
	//34. Find First and Last Position of Element in Sorted Array
    public int[] searchRange(int[] nums, int target) {
        // brute force O(n)
        if(nums == null || nums.length ==0) return new int[] {-1,-1};
        int low = 0, high = nums.length-1;
        while(low < high ){
            int mid = (low + high) /2 ;
            if(nums[mid]>target) high = mid - 1;
            else if(nums[mid]<target) low = mid + 1;
            else {low = mid; break;}
            
        }
        if(nums[low]!=target) return new int[] {-1,-1};
        else{
            int i= low, j = low;
            while((i>=0&&nums[i]==target)||(j<=nums.length-1&& nums[j]== target)){
                if(i>=0&&nums[i]==target){i--;}
                if(j<=nums.length-1&&nums[j]== target){j++;}
            }
            return new int[] {i+1,j-1};
        }
    }
    // learn how to bias in the binary search using high = mid -1, or low = mid +1!
    // two binary search could done this
    //firstly using low = mid +1 when nums[mid]<target,
    // when nums[mid]==target, low will not move, and high would move forward low untill they are eqaul.
    public int[] searchRange2(int[] nums, int target) {
        // brute force O(n)
        int[] ans = new int[] {-1,-1};
        if(nums == null || nums.length ==0) return ans;
        
        int low = 0, high = nums.length-1;
        while(low < high ){
            int mid = (low + high) /2 ;
            if(nums[mid]<target) low = mid + 1; //always moving left
            else  high = mid; 
        }
        if(nums[low]!=target) return ans;
        else ans[0] = low;
        high = nums.length-1;
        while(low < high){
            int mid = (low + high) / 2 + 1;//bias to right move the comparasion on!!!
            if(nums[mid]>target) high = mid -1;//always moving right
            else low = mid;
        }
        ans[1]=low;
        return ans;
        
    }
	
	//35. Search Insert Position
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length-1;
        while(low <= high){
            int mid = (low + high) / 2 ;
            if(nums[mid] > target) high = mid - 1;
            else if(nums[mid] < target) low = mid + 1;
            else return mid;
        }
        return low;
    }
	
	//50. Pow(x, n)
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        
        if(n<0){
            x = 1/x;
        }
        return (n%2 == 0) ? myPow(x*x, (n<0? -1:1)* (n/2)) : x*myPow(x*x, (n<0? -1:1)* (n/2));
    }
    // sqrt(x) 
    // brute force O(sqrt(n))
    public int mySqrt(int x) {
        int i = 0;
        for(; ((long)i*i)<=x; i++){}
        
        return i-1;
    }
    //binary search approach
    //O(log n)
    public int mySqrt2(int x) {
        if(x == 1) return 1;
        long low = 0, high = x;
        while(low < high-1){
            long mid = (low + high) / 2;
            if(mid * mid < x) low = mid;
            else if(mid * mid > x) high = mid;
            else return (int)mid;
        }
        if(high * high == x) return (int) high;
        return (int)low;
    }
    // 74. Search a 2D Matrix
    //log(n*m)
    public boolean searchMatrix1(int[][] matrix, int target) {
    	if(matrix.length==0||matrix[0].length==0) return false;
        //treat the matrix as a array.
    	int row_num = matrix.length;
    	int col_num = matrix[0].length;
    	
    	int begin = 0, end = row_num * col_num - 1;
    	
    	while(begin <= end){
    		int mid = (begin + end) / 2;
    		int mid_value = matrix[mid/col_num][mid%col_num];
    		
    		if( mid_value == target){
    			return true;
    		
    		}else if(mid_value < target){
    			//Should move a bit further, otherwise dead loop.
    			begin = mid+1;
    		}else{
    			end = mid-1;
    		}
    	}
    	
    	return false;
    }
    // m + log(n) worse than log(m) + log(n)
    public boolean searchMatrix3(int[][] matrix, int target) {
        int row = 0;
        while(row < matrix.length){
            //System.out.println(row);
            int low = 0, high = matrix[row].length -1 ;
            if(high>=0 && matrix[row][low]<=target && matrix[row][high]>=target){
                while(low <= high){
                    int mid = (low + high) / 2;
                    if(matrix[row][mid]>target) high = mid - 1;
                    else if (matrix[row][mid]<target) low = mid + 1;
                    else return true;
                }
                break;
            }else row++;
        }
        return false;
    }
    // space reduction here, m + n! not better than 
    public boolean searchMatrix4(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int i = 0, j = matrix[0].length - 1;
            while (i < matrix.length && j >= 0) {
                    if (matrix[i][j] == target) {
                        return true;
                    } else if (matrix[i][j] > target) {
                        j--;
                    } else {
                        i++;
                    }
                }
            
            return false;
    }
    //81. Search in Rotated Sorted Array II
    public boolean search(int[] nums, int target) {
        int low =0, high = nums.length-1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(nums[mid]==target) return true;
            else if(nums[low]<= nums[mid]) {
                if(nums[low]== nums[mid]) low ++;
                else if(nums[low]<=target && nums[mid]>target) high = mid -1;
                else low = mid;
    	    }else {
                if(nums[high]== nums[mid]) high --;
                else if(nums[mid]<target && nums[high]>=target) low = mid + 1;
                else high = mid;
    	    }
        }
        return false;
    }
    
    
    //153. Find Minimum in Rotated Sorted Array

    public int findMin(int[] nums) {
        //intuitively using binray search
        int low = 0, high = nums.length-1;
        while(low<high){
            int mid = (low + high)/2;
            if(nums[mid]>nums[high]) {low = mid+1;}
            else{high = mid;}
        }
        return nums[low];
    }
    //154 II including duplicates
    public int findMin2(int[] nums) {
        //intuitively using binray search
        int low = 0, high = nums.length-1;
        while(low<high){
            int mid = (low + high)/2;
            if(nums[mid]>nums[high]) {low = mid+1;}
            else if(nums[mid]<nums[high]){high = mid;}
            else { //actually here can be just high--;
            		// same effect and time Complexity like the isLeft!
                if(isLeft(nums,low,high)) low = mid+1;
                else high = mid;
            }
        }
        return nums[low];
    }
    private boolean isLeft(int[] nums, int low, int high){
        for(int i = low ; i < (low+high)/2; i++){
            if(nums[i+1]!=nums[i]) return false;
        }
        return true;
    }
    
    
	
	//Binary search in 1D array Pattern!!!!
	//Max Sum of Subarray No Larger Than K
	public int maxSumSubArray(int[] a , int k){
		int max = Integer.MIN_VALUE; 
		int sumj = 0; 
		TreeSet<Integer> ts = new TreeSet(); 
		ts.add(0); 
		for(int i=0;i<a.length;i++){
			sumj += a[i]; 
			/*E ceiling(E e): This method returns the least element 
			 * in this set greater than or equal to the given element,
			 *  or null if there is no such element.*/
			Integer gap = ts.ceiling(sumj - k);
			if(gap != null) max = Math.max(max, sumj - gap);
			ts.add(sumj); 
		} 
		return max;
	}
	//222. Count Complete Tree Nodes

    public int countNodes(DFS.TreeNode root) {
        //brute force O(n) dfs but TLE
        if(root == null) return 0;
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return 1 + left + right;
    }
    
    //Binary Search 
    public int countNodes2(DFS.TreeNode root) {
        //O(logn*logn) dfs
        //go to all the way down to the botton to see if the subtree is full (left == right == null)
        // if it is, then return 2^n-1 
        if(root == null) return 0;
        int height = 0;
        DFS.TreeNode left = root, right = root;
        while(right!= null){
            left = left.left;
            right = right.right;
            height ++;
        }
        if(left == null) return (1<<height)-1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
	//240. Search a 2D Matrix II
	// optimized binary search worst case log(log(n!)
    public boolean searchMatrix(int[][] matrix, int target) {
        //binary search both matrix[0][] and matrix[][0], find the boundry
        //binary search every col or row (depending on which is longer)
        if(matrix.length == 0|| matrix[0].length==0 || matrix == null) return false;
        int m = seachArray(matrix, false, 0, target,0,matrix.length-1); // row number
        int n = seachArray(matrix, true, 0, target,0,matrix[0].length-1); // col number
        //System.out.println("["+m+","+n+"]");
        if(matrix[0][n]==target || matrix[m][0] == target) return true;
        for(int i = 1 ; i <= (m<n?m:n); i++){
            int colfind = seachArray(matrix, true, i, target,i, n);
            int rowfind = seachArray(matrix, false, i, target,i, m);
            if(matrix[i][colfind]==target||matrix[rowfind][i]==target) return true;
        }
        return false;
    }
    private int seachArray(int[][] matrix, boolean isRow, int index, int target, int low ,int high){
        while(low < high){
            int mid = (low + high) / 2;
            //System.out.println(isRow?matrix[index][mid]:matrix[mid][index]);
            if((isRow?matrix[index][mid]:matrix[mid][index]) > target) high = mid;
            else if ((isRow?matrix[index][mid]:matrix[mid][index]) < target) low = mid + 1;
            else {low = mid; break;} 
        }
        return low;
    }
    // more awesome space deduct! O(m+n) which is DFS!!!
    public boolean searchMatrix2(int[][] matrix, int target) {
        //reshaping the search range every time!
        int col = 0, row = matrix.length-1;
        while(row >=0&&col<=matrix[0].length-1 ){
            if(matrix[row][col]>target) row--;
            else if (matrix[row][col]<target) col ++;
            else return true;
        }
        return false;
    }
    //275. H-Index II
    // why this works?
    // because when citations.length - mid == citations[mid], we find the answer
    // although high would skip to mid -1, low would finally == high+1!!!
    // another thing is that you have to learn that when low <= high, you need high = mid -1!
    // also in thie quiz, this kind of operation avoid the zero problem
    // becuase if it has answer it would bias to left part, if it does not have answer it would bias to the right part
    // which is a amazing find!
    public int hIndex(int[] citations) {
        int low = 0, high = citations.length-1;
        while(low <= high){
            int mid = (low + high) / 2 ;
            if(citations.length - mid > citations[mid]) low = mid + 1;
            else high = mid -1;
        }
        return citations.length-low;
    }
    
    
    //287. Find the Duplicate Number
    	// but this is nlog n. not as tricky as the LL two pointers approach!
    // his is a very great example of using the [range] as the search space!!!
    /*you perceive the indices as the values.
	Then count the number of values lesser than the mid
	If the if the count is lesser than mid, we assume the duplicate number should be on the higher side of the number scale.
	so we make low = mid + 1
	else we assume the duplicate number should be on the lower end of the number scale.
	so we make high = mid - 1

	We continue until low <=hight no longer holds true.*/
    public int findDuplicate(int[] nums) {
    	int low = 1, high = nums.length - 1;
        while (low <= high) {
            int mid = (int) (low + (high - low) * 0.5);
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
    
    //300. Longest Increasing Subsequence
    // O(n^2)
    public int lengthOfLIS(int[] nums) {
        //dp approach
        int max = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        for(int i = nums.length-1; i>=0; i--){
            int tmp = dp[i];
            for(int j = i+1; j< nums.length; j++){
                if(nums[j]>nums[i]) dp[i]=Math.max(tmp+dp[j],dp[i]);
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }
    // DP + binarySearch nlogn
    public int lengthOfLIS2(int[] nums) {
        //dp approach + binary search the dp! for update the last value of that len
        //mean idea is to store all the last value in the dp of all the length
        //so that you can find current number should append to which length!!! then update that length+1 or length++
        int[] dp = new int[nums.length];
        int[] data = new int[nums.length];
        int len = 0;
        Arrays.fill(data,-1);
        for(int i = 0; i < nums.length; i ++){
            int low = 0, high = len;
            while(low < high){
                int mid = (low + high) / 2;
                if(dp[mid] < nums[i]) low = mid + 1;
                else high = mid;
            }
            if(low == len) {
                len++;
            }
            dp[low]=nums[i];
        }
        return len;
    }
	
    //354. Russian Doll Envelopes
    // new sorting method for array when sort the 2D array for only one D inside of them!!
    public int maxEnvelopes(int[][] nums) {
    	// every clever!!!
        //Arrays.sort(nums, (a,b) -> a[0]-b[0]);
        //there was a problem here because a[0] can be equal to b[0]
        //but a[1] would larger than b[1] which makes it a ascending sequance and we dont want it
        //if a[0]==b[0], we need to switching a[1] b[1] by descending.
        Arrays.sort(nums, (a,b) -> Integer.compare(a[0]==b[0]?b[1]:a[0], a[0]==b[0]?a[1]:b[0]));
        int m = nums.length;
        int[] dp = new int[m];
        int len = 0;
        for(int i = 0 ; i < m; i++){
            int insert = Arrays.binarySearch(dp,0,len,nums[i][1]);
            if(insert < 0) insert = - (insert+1);
            if(insert == len) len++;
            dp[insert] = nums[i][1];
        }
        return len;
    }
	
	//363  combine all the selected col to 1D array and then use the binary search in 1D array!!. 
	//363. Max Sum of Rectangle No Larger Than K
    public int maxSumSubmatrix(int[][] matrix, int target) {
        int row = matrix[0].length;
        int col = matrix.length;
        if(col == 0) return 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < row; i++){
            int[] array = new int[col];
            for(int j = i; j < row; j++ ){
                TreeSet<Integer> preSum = new TreeSet<>();
                preSum.add(0);
                int sum = 0;
                for(int k = 0; k < col; k++){
                    array[k] = array[k]+matrix[k][j];
                    sum += array[k];
                    Integer lastElement = preSum.ceiling(sum - target);
                    if(lastElement!=null) max = Math.max(max, sum - lastElement);
                    preSum.add(sum);
                }
            }
        }
        return max;
    }
    
    //367. Valid Perfect Square
    //just always be aware of overflow of the int!!
    public boolean isPerfectSquare(int num) {
        int low = 0, high = num;
        while(low < high){
            int mid = low + (high - low) / 2;
            if(mid!=0&&mid*mid/mid!=mid) high = mid;
            else if(mid*mid < num) low = mid + 1;
            else high = mid;
        }
        return low*low==num;
    }
    
    //368. Largest Divisible Subset

    public List<Integer> largestDivisibleSubset(int[] nums) {
        ////// MY INTUITION IS FCKING NOT WRONG `!!!! SAME WITH LIS!!! BUT I FORGET TO JUST STORE THE MAX!!!
        //SHOULD HAVE LEARN THAT THIS IS NOT BINARY SEARCH!!!!!!!!

        int n = nums.length;
        int[] count = new int[n];
        int[] pre = new int[n];
        Arrays.sort(nums);
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            pre[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + count[j] > count[i]) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }
    
    //378. Kth Smallest Element in a Sorted Matrix

    public int kthSmallest(int[][] matrix, int k) {
    	// brute force O(m*n)
        int m = matrix.length;
        int n = matrix[0].length;
        int[] arr = new int[m*n];
        for(int i = 0 ; i < m; i ++){
            for(int j = 0; j < n; j++){
                arr[i*m+j]=matrix[i][j];
            }
        }
        Arrays.sort(arr);
        //System.out.println(Arrays.toString(arr));
        return arr[k-1];
    }
    // guess number
    public int guessNumber1(int n) {
        int low = 1, high = n;
        while(low < high){
            int mid = low + (high - low) / 2; 
            // OOOOOHHHHHH SHIT THERE CAN BE OVERFLOW WHEN YOU DIRECTLY USE (i + j)/2
            // you have to remember this, always be careful about the overflow!!!
            // if the range is not clearly mentioned
            if (guess(mid) == 0) {
                return mid;
            }else if(guess(mid) == 1) {
                low = mid + 1;
            }else {
                high = mid;
            }
        }
        return low;
    }
    //441. Arranging Coins
    //gotta be careful about the over flow!!!!!
    // always need to carefully think about the low <= high!!
    public int arrangeCoins(int n) {
        int low = 1 , high = n;
        while(low <= high){
            long mid = low + (high - low) / 2;
            if(n / ((mid+1)*mid/2) >=1) low = (int) mid + 1;
            else high = (int)mid-1;
        }
        return low-1;
    }
    
    //475. Heaters
    	//bruteforce should be n^2
    public int findRadius(int[] houses, int[] heaters) {
        //the idea should be find all the spot around heaters that should insert house!!!
        //I was thinking the totally opposite way!
        //ALWAYS REMEMBER WHEN DOING BINARY SEARCH YOU HAVE HAVE HAVE TO SORT!!!
        Arrays.sort(heaters);
        int max = 0, prepos = 0;
        for(int i = 0 ; i < houses.length; i ++){
            int pos = Arrays.binarySearch(heaters,houses[i]);
            if(pos < 0) pos= - (pos + 1);
            // I was confused at here!!
            int distance1 = pos > 0? houses[i] - heaters[pos-1] : Integer.MAX_VALUE;//if pos == 0, use heaters[pos] - houses[i];
            int distance2 = pos< heaters.length? heaters[pos] - houses[i] : Integer.MAX_VALUE;
            //if pos == heaters.length, use houses[i] - heaters[pos-1];
            max = Math.max(max,Math.min(distance1,distance2));
        }
        return max;
    }
    
    //658. Find K Closest Elements

    public List<Integer> findClosestElements(int[] nums, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int low = 0, high = nums.length - k -1 ;
        while(low <= high){
            int mid = (low + high) / 2; 
            if(Math.abs(nums[mid]-x) > Math.abs(nums[mid+k]-x)) low = mid + 1;
            else high = mid - 1;
        }
        for(int i = low; i < low+k; i++){
            ans.add(nums[i]);
        }
        return ans;
    }
    
    //852. Peak Index in a Mountain Array
    public int peakIndexInMountainArray(int[] A) {
        //brute force n
        for(int i = 0; i< A.length-1; i++){
            if(A[i+1]<A[i]) return i;
        }
        return -1;
    }
    //binary search log(n);
    public int peakIndexInMountainArray2(int[] A) {
        //binary search log(n)
        int low = 0 , high = A.length -1 ;
        while(low < high){
            int mid = (low + high) / 2;
            if(A[mid] > A[mid + 1]) high = mid;
            else low = mid+1;
        }
        return low;
    }
    //maybe this is better to understand!
    public int peakIndexInMountainArray3(int[] nums) {
        int left = 0, right = nums.length -1;
        
        while(left< right){
            int mid = (right+left) / 2;
            if(nums[mid] < nums[mid+1]){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return right;
        
    }


}
