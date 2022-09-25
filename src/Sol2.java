import java.util.ArrayList;
import java.util.Comparator;

public class Sol2 {

    //Given in the problem statement, no changes

    static void sort(ArrayList<Integer> list, Integer left, Integer right) {

        if (right <= left)
            return;

        Integer s = part(list, left, right);

        sort(list, left, s - 1);

        sort(list, s + 1, right);

    }
//this is the only method where changes was done, I directly passed the comparator object in Lambda expression
    static Integer part(ArrayList<Integer> list, Integer left, Integer right) {

        assert(left < right);

        Integer i = left - 1, j = right;


        // Creating a lambda expression where the comparator object of type java.util.Comparator for comparing Integer objects is passed to it.

        Comparator<Integer> testCompareLambda = (Integer o1, Integer o2) -> o1.compareTo(o2);

        for(;;) {

            while (testCompareLambda.compare(list.get(++i), list.get(right))==-1)//lambda expression object is passed directly
                ;

            while (testCompareLambda.compare(list.get(right), list.get(--j))==-1)//lambda expression object is passed directly
                if (j.equals(left))
                    break;

            if (i >= j)
                break;

            swap(list, i, j);

        }

        swap(list, i, right);

        return i;

    }
    //Given in the problem statement, no changes

    static void swap(ArrayList<Integer> list, Integer i, Integer j) {

        Integer h = list.get(i);

        list.set(i, list.get(j));

        list.set(j, h);

    }

    public static void main(String[] args) {

        // "This was given" We test the sorting procedure with a list of random integer objects

        Integer n = 100000; //Given: Integer n given "100000" value
        //Given: no changes->  object "list" of Arraylist where Integer wrapper class is used as its type to store multiple values
        ArrayList<Integer> list = new ArrayList<Integer>();

        //Given in the problem statement, no changes
        for (int i = 0; i < n; i++)
            list.add((int) Math.ceil(Math.random() * n));
        //Given in the problem statement, no changes

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

