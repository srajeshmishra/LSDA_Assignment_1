import java.util.ArrayList;
import java.util.Comparator;

public class Sol1 {

   //Updated sort method: Another parameter of Comparator object is passed in the sort method
    static void sort(ArrayList<Integer> list, Integer left, Integer right, Comparator<Integer> testCompare) {

        if (right <= left)
            return;

        Integer s = part(list, left, right, testCompare); //Updated code: comparator object is passed here

        sort(list, left, s - 1, testCompare); //Updated code: comparator object is passed here

        sort(list, s + 1, right, testCompare); //Updated code: comparator object is passed here

    }

    //Updated part method: Another parameter of Comparator object is passed in the part method to compare the different integer values from the list
    static Integer part(ArrayList<Integer> list, Integer left, Integer right,Comparator<Integer> testCompare) {

        assert(left < right);

        Integer i = left - 1, j = right;

        for(;;) {

            while (testCompare.compare(list.get(++i), list.get(right))==-1)//object of type java.util.Comparator replaces the compare method commented below
                ;

            while (testCompare.compare(list.get(right), list.get(--j))==-1)//object of type java.util.Comparator replaces the compare method commented below
                if (j.equals(left))
                    break;

            if (i >= j)
                break;

            swap(list, i, j);

        }

        swap(list, i, right);

        return i;

    }

//This compare method from problem statement was replaced with object of type java.util.Comparator for comparing Integer objects
/*    static boolean compare(Integer x, Integer y) {

        return x < y;

    }*/
// No changes in this method, this was given
    static void swap(ArrayList<Integer> list, Integer i, Integer j) {

        Integer h = list.get(i);

        list.set(i, list.get(j));

        list.set(j, h);

    }

    public static void main(String[] args) {

        // "This was given "We test the sorting procedure with a list of random integer objects"

        Integer n = 100000; //Given: Integer n given "100000" value
        //Given: no changes->  object "list" of Arraylist where Integer wrapper class is used as its type to store multiple values
        ArrayList<Integer> list = new ArrayList<Integer>();

        // Creating a comparator object of type java.util.Comparator for comparing Integer objects where Integer wrapper class is used as its type
        Comparator<Integer> testCompare = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2); //comparing parameters here which will either return 0, 1 or -1
            }
        };

        //Given in the problem statement, no changes
        for (int i = 0; i < n; i++)
            list.add((int) Math.ceil(Math.random() * n));
        //Adding the testCompare object which we created above to pass the returned value to sort function
        sort(list, 0, n-1, testCompare);

        //Given in the problem statement, no changes
        for (int i = 0; i < n; i++) {

            System.out.print(list.get(i) + ", ");

            if(i > 0 && i % 20 == 0)
                System.out.println();

        }

    }
}
