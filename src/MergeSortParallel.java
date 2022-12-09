import java.util.Arrays;
import java.util.Random;


public class MergeSortParallel {

    int [] arr;

    int [] tempArr;



    public MergeSortParallel(int[]arr){
        this.arr = arr;
        this.tempArr = new int[arr.length];
    }

    private Thread createThread (int start, int end, int numOfThreads){
        return new Thread(() -> {
            parallelMergeSort(start,end,numOfThreads/2);
        });
    }

    public void parallelMergeSort (int start, int end, int numOfThreads){
        if(numOfThreads <= 1) {
            mergeSort(start,end);
            return;
        }

        int mid = start + (end-start)/2;

        Thread leftSorted = createThread(start,mid,numOfThreads);
        Thread rightSorted = createThread(mid+1,end,numOfThreads);

        leftSorted.start();
        rightSorted.start();

        try {
            leftSorted.join();
            rightSorted.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        merge(start,mid,end);
    }

    public void showArr () {
        Arrays.stream(arr).forEach(el -> System.out.print(el + " "));
    }

    public void sortParallel () {
        int numOfCores = Runtime.getRuntime().availableProcessors();
        parallelMergeSort(0,arr.length-1,numOfCores);
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

        int arr1 [] = createArr(100000000);
        int arr2 [] = Arrays.stream(arr1).toArray();

        MergeSortParallel msp = new MergeSortParallel(arr1);
        MergeSort ms = new MergeSort(arr2);

        long startTime1 = System.currentTimeMillis();
        msp.sortParallel();
        long endTime1 = System.currentTimeMillis();
        System.out.printf("Time with parallel: %6d ms\n" , endTime1-startTime1);

        long startTime2 = System.currentTimeMillis();
        ms.sort();
        long endTime2 = System.currentTimeMillis();
        System.out.printf("Time with sequential: %6d ms\n" , endTime2-startTime2);
    }

    private static int[] createArr (int length){
        Random random = new Random();
        int[] arr = new int [length];

        for(int i=0;i<length;++i){
            arr[i] = random.nextInt(length);
        }

        return arr;
    }

}
