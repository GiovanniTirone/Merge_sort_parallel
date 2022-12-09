import java.util.Arrays;


public class MergeSort {

    int [] arr;

    int [] tempArr;



    public MergeSort (int[]arr){
        this.arr = arr;
        this.tempArr = new int[arr.length];
    }



    public void showArr () {
        Arrays.stream(arr).forEach(el -> System.out.print(el + " "));
    }

    public void sort () {
        mergeSort(0,arr.length-1);
    }

    private void mergeSort (int start, int end) {
        if(start>=end) return;
        int middle = start + ((end - start) / 2);

        mergeSort(start,middle);
        mergeSort(middle+1,end);
        merge(start,middle,end);
    }

    private void merge (int start, int mid, int end) {

        for (int i = start; i <= end; i++) //copy arr into the tempArr
            tempArr[i] = arr[i];

        int i = start;  //indexes the left temp-subarray
        int j = mid + 1; // indexes the right temp-subarray
        int k = start;  // indexes the final ordered arr

        while (i <= mid && j <= end) {
            if (tempArr[i] < tempArr[j]) {
                arr[k] = tempArr[i];
                ++i;
            } else {
                arr[k] = tempArr[j];
                ++j;
            }
            ++k;
        }

        //copy the remaining items of left temp-subarr
        while (i <= mid) {
            arr[k] = tempArr[i];
            ++k;
            ++i;
        }

        //copy the remaining items of right temp-subarr
        while (j <= end) {
            arr[k] = tempArr[j];
            ++k;
            ++j;
        }
    }




    public static void main(String[] args) {
        int arr [] = {5,-1,0,7,2,3,2,1,0,1,2};
        MergeSort ms = new MergeSort(arr);
        ms.sort();
        ms.showArr();
    }

}
