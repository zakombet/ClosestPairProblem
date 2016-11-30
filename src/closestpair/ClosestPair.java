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
    
    private Point[]area = new Point[10000];
    private Point[]area2 = new Point[1000000];
    private static int MAX = Integer.MAX_VALUE -1;
    private static int MIN = Integer.MIN_VALUE;
    
    //Method for filling the smaller of the two arrays with random values
    public void fillSmallArrary()
    {
        for(int i = 0; i < area.length-1; i++)
        {
            area[i] = new Point(ThreadLocalRandom.current().nextInt(MIN, MAX+1), 
                                                ThreadLocalRandom.current().nextInt(MIN, MAX+1));
            System.out.println(area[i]);
        }
    }
    
    //fills the larger of the two arrays with random values
    public void fillBigArrary()
    {
          for(int i = 0; i < area.length-1; i++)
        {
            area2[i] = new Point(ThreadLocalRandom.current().nextInt(MIN, MAX+1), 
                                                ThreadLocalRandom.current().nextInt(MIN, MAX+1));
            System.out.println(area2[i]);
        }
    }
    
    //method will calculate the distance between two points
    public double distance(int x1, int x2, int y1, int y2) //might need to make this return type int but not sure yet
    {
       return Math.sqrt(((x2-x1) * (x2-x1)) + ((y2-y1) * (y2-y1)));
    }
    
    
    public Point closestPairBruteForce(Point[]points)
    {
        //must apply pseudocode thats posted in the group chat
        //returns a Point which is the closest pair 
    }

   
    public static void main(String[] args) {
        ClosestPair b = new ClosestPair();
        b.fillSmallArrary();
        b.fillBigArrary();
       System.out.println( b.distance(1, 0, 2, 0));
    }
    
}
