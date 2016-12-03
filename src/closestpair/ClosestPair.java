
package closestpair;

import java.util.concurrent.ThreadLocalRandom;

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
    public double distance(int x1, int x2, int y1, int y2) 
    {
       return Math.sqrt(((x2-x1) * (x2-x1)) + ((y2-y1) * (y2-y1)));
    }
    
    
    //This algorithm will solve the Closest Pair problem and do so in O(n^2) time
    public Point[] closestPairBruteForce(Point[]points , int size)
    {
        //must apply pseudocode thats posted in the group chat
        //returns a Point which is the closest pair
        Point[]closestPair = new Point[2];
        Point closest1 = null;
        Point closest2 = null;
        double minDistance = DOUBLE_MAX;
        for(int i = 0; i < size; i++)
        {
            for(int j = i+1; j < size; j++)
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
        System.out.println(minDistance);
        return closestPair;
    }
    
       
    //supporting method to sort arrays
    public void merge(Point[]first,Point[]second,Point[]third)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < first.length & j < second.length)
        {
            if(first[i].getX() < second[j].getX())
            {
                third[k] = first[i];
                i++;
            }
            else
            {
                third[k] = second[j];
                j++;
            }
            k++;
        }
        System.arraycopy(first,i,third,k,first.length-i);
        System.arraycopy(second,j,third,k,second.length-j);
    }

    //Non-linear sorting algorithm to sort array before being passed off to algorithm
    public Point[] mergeSort(Point[] points)
    {
        if(points.length <= 1)
            return points;
        else
        {
            Point[] first = new Point[points.length/2];
            Point[]second = new Point[points.length - first.length];
            System.arraycopy(points, 0, first, 0, first.length);
            System.arraycopy(points, first.length, second, 0, second.length);
            mergeSort(first);
            mergeSort(second);
            merge(first,second,points);
        }
        return points;
    }
    
    /*
    public Point[] closestPair(Point[] points)
    {
        double minDistance = DOUBLE_MAX;
        if(points.length <= 3)
            return closestPairBruteForce(points);
        else
        {
            Point[] leftPoints = new Point[points.length/2];
            Point[] rightPoints
        }
    }
    */

    
    
    //method to find the closest pair in O(nlogn) time
    //input is a sorted array, in this implementation we have used merge sort
    public Point[] closestPair(Point[]points, int size)
    {
        int mid = size/2+size%2;
        if(size <= 3)
            return closestPairBruteForce(points,size);
        Point[] leftPoints = new Point[mid]; //create subarray for points on left half
        Point[] rightPoints = new Point[mid]; //create subarray for points on right half
        Point[] minLeft, minRight, closest;
        for(int i = 0; i < mid; i++)
            leftPoints[i] = points[i]; //filling left half array
        for(int j = 0; j < mid; j++)
            rightPoints[j] = points[mid+j-1]; //filling right half array    

        minLeft = closestPair(leftPoints,mid); //reduce the left half to 2 points
        minRight = closestPair(rightPoints,mid); //reduce the right half to 2 points
        //closest = combine(minLeft, minRight); //combine the closest from each half and compare them
      //and determine the closest pai
         
        return points;
    }
    
    //method that will compare the closest points from each half array and determine the closest pair
    public Point[] combine(Point[] leftArr, Point[] rightArr)
    {
        double distLeft = distance(leftArr[0].getX(), leftArr[1].getX(), leftArr[0].getY(), leftArr[1].getY());
        double distRight = distance(rightArr[0].getX(),rightArr[1].getX(), rightArr[0].getY(), rightArr[1].getY());
        double theDistance; //the smallest distance of the two distances we have
        if(distLeft < distRight)
            theDistance = distLeft;
        else
            theDistance = distRight;
        Point[] closestPair; //the actual closest pair
        if(distLeft < distRight)
            closestPair = leftArr;
        else                                 //closest pair is either in the left or right array
            closestPair = rightArr;
       
        //We must see if there is a case where the closest pair has one point in the right and one in the left
        for(int i = 0; i < leftArr.length; i++)
        {
            for(int j = 0; j < rightArr.length; j++)
            {
                Point point1 = leftArr[i];
                Point point2 = rightArr[j];
                if(leftArr[i].getX() < rightArr[j].getX() + theDistance &&
                        leftArr[i].getY() + theDistance > rightArr[j].getY() &&
                        rightArr[j].getY() > leftArr[i].getY() - theDistance){
                    if(distance(point1.getX(),point2.getX(),point1.getY(),point2.getY()) < theDistance)
                        return new Point[] {point1, point2};
                }
            }
        }
        System.out.println(theDistance);
        return closestPair;
    }

   
    public static void main(String[] args) {
        ClosestPair close = new ClosestPair();
        /*
        //test this first (comment out second case so it only runs this case)
        //case for 10,000 points O(n^2)
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
       
        
        long startTime2 = System.currentTimeMillis();
        //test this second (comment out first case so it only runs this case)
        //case for 1,000,000 points O(n^2)
        Point[]test2 = new Point[1000000];
        close.fillArray(test2);
        System.out.println("+++++Closest Pair+++++");
        Point[]answer2 = close.closestPairBruteForce(test2);
        for(int j = 0; j < answer2.length; j++){
            System.out.println(answer2[j]);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println(endTime2 - startTime2 +"ms");
        */
        
        /*
         //test this first (comment out second case so it only runs this case)
        //case for 10,000 points O(nlogn)
        long startTime3 = System.currentTimeMillis();
        Point[]test3 = new Point[10000];
        close.fillArray(test3);
        close.mergeSort(test3);
        System.out.println("=====Closest Pair======");
        Point[]answer3 = close.closestPair(test3,test3.length);
        for(int i = 0; i < answer3.length; i++){
            System.out.println(answer3[i]);
        }
        long endTime3 = System.currentTimeMillis();
        System.out.println(endTime3 - startTime3 + "ms");
       */
        
        
        long startTime4 = System.currentTimeMillis();
        //test this second (comment out first case so it only runs this case)
        //case for 1,000,000 points O(nlogn)
        Point[]test4 = new Point[1000000];
        close.fillArray(test4);
        close.mergeSort(test4);
        System.out.println("+++++Closest Pair+++++");
        Point[]answer4 = close.closestPair(test4,test4.length);
        for(int j = 0; j < answer4.length; j++){
            System.out.println(answer4[j]);
        }
        long endTime4 = System.currentTimeMillis();
        System.out.println(endTime4 - startTime4 +"ms");
    }
    
}
