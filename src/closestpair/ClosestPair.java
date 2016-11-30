/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closestpair;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author plenhart
 */
public class ClosestPair {
    
    private static int MAX = Integer.MAX_VALUE -1; //used to generate random integers for arrays
    private static int MIN = Integer.MIN_VALUE; //used to generate random integers for arrays
    private static double DOUBLE_MAX = Double.MAX_VALUE; //for distance measurement
    private static double DOUBLE_MIN = Double.MIN_VALUE; //for distance measurement
    
    //Method for filling the smaller of the two arrays with random values
    public void fillArray(Point[]points)
    {
        for(int i = 0; i < points.length; i++)
        {
            //fills array with random integers in range Integer MIN and Integer MAX
            points[i] = new Point(ThreadLocalRandom.current().nextInt(MIN, MAX+1), 
                                                ThreadLocalRandom.current().nextInt(MIN, MAX+1));
            //System.out.println(points[i]);
        }
    }
    
    //method will calculate the distance between two points
    public double distance(int x1, int x2, int y1, int y2) //might need to make this return type int but not sure yet
    {
       return Math.sqrt(((x2-x1) * (x2-x1)) + ((y2-y1) * (y2-y1)));
    }
    
    
    //This algorithm will solve the Closest Pair problem and do so in O(n^2) time
    public Point[] closestPairBruteForce(Point[]points)
    {
        //must apply pseudocode thats posted in the group chat
        //returns a Point which is the closest pair
        Point[]closestPair = new Point[2];
        Point closest1 = null;
        Point closest2 = null;
        double minDistance = DOUBLE_MAX;
        for(int i = 0; i < points.length; i++)
        {
            for(int j = 0; j < i; j++)
            {
                Point temp1 = points[i];
                Point temp2 = points[j];
                if(distance(temp1.getX(), temp2.getX(), temp1.getY(), temp2.getY()) < minDistance)
                {
                    minDistance = distance(temp1.getX(), temp2.getX(), temp1.getY(), temp2.getY());
                    closest1 = temp1;
                    closest2 = temp2;
                }
            }
        }
        closestPair[0] = closest1;
        closestPair[1] = closest2;
        return closestPair;
    }

   
    public static void main(String[] args) {
        ClosestPair close = new ClosestPair();
        
        //test this first (comment out second case so it only runs this case)
        //case for 10,000 points
        long startTime = System.currentTimeMillis();
        Point[]test = new Point[10000];
        close.fillArray(test);
        System.out.println("=====Closest Pair======");
        Point[]answer = close.closestPairBruteForce(test);
        for(int i = 0; i < answer.length; i++){
            System.out.println(answer[i]);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + "ms");
       
        /*
        //test this second (comment out first case so it only runs this case)
        //case for 1,000,000 points
        Point[]test2 = new Point[1000000];
        close.fillArray(test2);
        System.out.println("+++++Closest Pair+++++");
        Point[]answer2 = close.closestPairBruteForce(test2);
        for(int j = 0; j < answer2.length; j++){
            System.out.println(answer2[j]);
        }
*/
    }
    
}
