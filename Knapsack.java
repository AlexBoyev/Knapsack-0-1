import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

class Question3 {
 
    static double max(double d, double k) {
    	if( d > k) {
    		return d;
    	}
    	else {
    		return k;
    	}
    }
    public static boolean checkNegative(int wt[],double val[]) {
    	for(int i= 0;i<wt.length;i++) {
    		if(wt[i] < 0 || val[i] < 0) {
    			return true;
    		}
    	}
    	return false;
    }
    static void knapSack(int W, int wt[], double val[]) {
    	if(W <= 0) {
    		System.out.println("invalid values");
    		return;
    	}
    	if(wt.length != val.length) {
    		System.out.println("invalid values");
    		return;
    	}
    	if(checkNegative(wt,val)) {
    		System.out.println("invalid values");
    		return;
    	}
    	int i, w;
    	double K[][] = new double[wt.length+1][W+1];
    	ArrayList<Integer> vector = new ArrayList<Integer>();
    	for (i = 0; i <= wt.length; i++) {
    		for (w = 0; w <= W; w++) {
        	 
    			if (i==0 || w==0) {
    				K[i][w] = 0;
    			}
    			else if (wt[i-1] <= w) {
    				K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
    			}
    			else {
    				K[i][w] = K[i-1][w];
    			}
         }
    	}
    	int tempW = 8;
    	for (int x = wt.length; x > 0; x--) {
    		if(tempW-wt[x-1] >= 0) {
    			double temp = K[x][tempW] - K[x-1][tempW-wt[x-1]];
    			DecimalFormat decimalFormat= new DecimalFormat("#.##");
    			decimalFormat.setRoundingMode(RoundingMode.FLOOR);
    			double g = Double.valueOf(decimalFormat.format(temp));
    			if (g == val[x-1]) {
    			 vector.add(1);
    			 tempW-=wt[x-1];
    		 }
    		 else {
    			 vector.add(0); 
    		 	}
    		}
    		else {
    			vector.add(0);
         	}
    	}
    	System.out.println("V="+K[wt.length][W]);
     	Collections.reverse(vector);
     	System.out.println("X="+vector);
    
    	}
    	
    
    public static void main(String args[]) {
    
    	double c[] = new double[] {6.0, 8.5, 4.3, 5.6, 3.0};
    	int w[] = new int[] {4, 5, 3, 4, 1};
    	int  W = 8;
    
    	knapSack(W, w, c);	
    	
    }
}