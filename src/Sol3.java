import java.util.ArrayList;
import java.util.Comparator;

public class Sol3 extends Thread {

    static ArrayList<Integer> list = new ArrayList<Integer>();
    Integer left;
    Integer right;

    //creating the constructor for Sol3 class
    public Sol3(ArrayList<Integer> list, Integer left, Integer right) {
        this.left = left;
        this.right = right;
    }
    //updated the sort method and implemented the multithreading function

    static void sort(ArrayList<Integer> list, Integer left, Integer right) throws InterruptedException {
        //Given in the problem statement, no changes

        if (right <= left)
            return;

        Integer s = part(list, left, right);

        Sol3 sort1 = new Sol3(list,left,s-1);//same as the given statement just a minor change that storing it in the object of Sol3 class to pass it in the threads
        Sol3 sort2 = new Sol3(list,s + 1,right);//same as the given statement just a minor change that storing it in the object of Sol3 class to pass it in the threads
        Thread thread1 = new Thread(sort1); //creating the thread object and passing the sort1 object
        Thread thread2 = new Thread(sort2);//creating the thread object and passing the sort2 object
        thread1.start();//implementing thread1 as when it is called a new Thread is created and code inside run() method is executed in that new Thread
        thread1.join();// it allows the thread to wait until the other thread completes its execution.
        thread2.start();//implementing thread2 as when it is called a new Thread is created and code inside run() method is executed in that new Thread
        thread2.join();// it allows the thread to wait until the other thread completes its execution.

    }
    //No changes in this method as it was used in solution 2, it is used here as it is
    static Integer part(ArrayList<Integer> list, Integer left, Integer right) {

        assert(left < right);

        Integer i = left - 1, j = right;


        // Creating a lambda expression where the comparator object of type java.util.Comparator for comparing Integer objects is passed to it.

        Comparator<Integer> testCompareThread = (Integer o1, Integer o2) -> o1.compareTo(o2);

        for(;;) {

            while (testCompareThread.compare(list.get(++i), list.get(right))==-1)//lambda expression object is passed directly
                ;

            while (testCompareThread.compare(list.get(right), list.get(--j))==-1)//lambda expression object is passed directly
                if (j.equals(left))
                    break;

            if (i >= j)
                break;

            swap(list, i, j);

        }

        swap(list, i, right);

        return i;

    }

    //No changes in this method
    static void swap(ArrayList<Integer> list, Integer i, Integer j) {

        Integer h = list.get(i);

        list.set(i, list.get(j));

        list.set(j, h);

    }
//run method created to execute the code written inside it after the new thread is created
    public void run() {
        try {
            sort(list, left, right);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws InterruptedException {

        // "This was given" We test the sorting procedure with a list of random integer objects

        Integer n = 100000; //Given: Integer n given "100000" value

        //Given in the problem statement, no changes
        for (int i = 0; i < n; i++)
            list.add((int) Math.ceil(Math.random() * n));
        sort(list, 0, n-1);

        //Given in the problem statement, no changes
        for (int i = 0; i < n; i++) {

            System.out.print(list.get(i) + ", ");
            //Given in the problem statement, no changes
            if(i > 0 && i % 20 == 0)
                System.out.println();

        }

    }
}

