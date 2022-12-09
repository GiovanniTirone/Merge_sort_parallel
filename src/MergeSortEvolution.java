import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortEvolution {

    int [] arr;

    int [] tempArr;

    List<String> evolution;

    private enum LeftRight {LEFT,RIGHT;};

    public MergeSortEvolution(int[]arr){
        this.arr = arr;
        this.tempArr = new int[arr.length];
        this.evolution = new ArrayList<>();
    }



    public void showArr () {
        Arrays.stream(arr).forEach(el -> System.out.print(el + " "));
        System.out.println("");
    }

    public void sort () {
        mergeSort(0,arr.length-1, null);
    }

    private void mergeSort (int start, int end, LeftRight leftRight) {


        if(leftRight == LeftRight.LEFT) {
            String str = "";
            for(int i=start; i<=end; i++) str += arr[i] + " ";
            evolution.add("Split LEFT: " + str);
        } else if (leftRight == LeftRight.RIGHT){
            String str = "";
            for(int i=start; i<=end; i++) str += arr[i] + " ";
            evolution.add("Split RIGHT: " + str);
        }else {
            evolution.add("first call");
        }

        if(start>=end) {
            evolution.set(evolution.size()-1, evolution.get(evolution.size()-1) +  "return" );
            return;
        }
        int middle = start + ((end - start) / 2);

        mergeSort(start,middle, LeftRight.LEFT);
        mergeSort(middle+1,end, LeftRight.RIGHT);
        merge(start,middle,end);
    }

    private void merge (int start, int mid, int end) {

        String strLeft = "";
        for (int i = start; i <= mid; i++) strLeft += arr[i] + " ";
        String strRight = "";
        for (int i = mid + 1; i <= end; i++) strRight += arr[i] + " ";
        evolution.add("----------MERGE---------\nmerge " + strLeft + " | " + strRight);

        for (int i = start; i <= end; i++) //copy arr into the tempArr
            tempArr[i] = arr[i];

        int i = start;  //indexes the left temp-subarray
        int j = mid + 1; // indexes the right temp-subarray
        int k = start;  // indexes the final ordered arr

        while (i <= mid && j <= end) {
            if (tempArr[i] < tempArr[j]) {
                evolution.add(tempArr[i] + " < " +tempArr[j]);
                arr[k] = tempArr[i];
                ++i;
                String strL = "";
                String strR = "";
                String strFinal = "";
                for (int t = i; t <= mid; t++) strL += tempArr[t] + " ";
                for (int s = j; s <= end; s++) strR += tempArr[s] + " ";
                for (int r = 0; r <= k; r++) strFinal += arr[r] + " ";
                evolution.add(strL + " | " + strR + "\nfinal: " + strFinal);
            } else {
                evolution.add(tempArr[i] + " >= " +tempArr[j]);
                arr[k] = tempArr[j];
                ++j;
                String strL = "";
                String strR = "";
                String strFinal = "";
                for (int t = i; t <= mid; t++) strL += tempArr[t] + " ";
                for (int s = j; s <= end; s++) strR += tempArr[s] + " ";
                for (int r = 0; r <= k; r++) strFinal += arr[r] + " ";
                evolution.add(strL + " | " + strR + "\nfinal: " + strFinal);
            }
            ++k;
        }

        //copy the remaining items of left temp-subarr
        while (i <= mid) {
            arr[k] = tempArr[i];
            ++k;
            ++i;
            String strFinal = "";
            for (int r = 0 ; r <= end; r++) strFinal += arr[r] + " ";
            evolution.add("adding remaining left items:\nfinal: " + strFinal);
        }

        //copy the remaining items of right temp-subarr
        while (j <= end) {
            arr[k] = tempArr[j];
            ++k;
            ++j;
            String strFinal = "";
            for (int r = 0 ; r <= end; r++) strFinal += arr[r]+ " ";
            evolution.add("adding remaining right items:\nfinal: " + strFinal);
        }

        evolution.add("\n");
    }

    public void showEvolution ( ) {
        evolution.forEach(System.out::println);
    }




    public static void main(String[] args) {
        int arr [] = {5,-1,0,7,2,3,2,1,0,1,2};
        MergeSortEvolution ms = new MergeSortEvolution(arr);
        ms.showArr();
        ms.sort();
        ms.showArr();
        System.out.println("\n");
        ms.showEvolution();
    }

}
