import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyMergeSort {



    private static int[] mergeSort (int arr []) {
        int l = arr.length;
        int m = (int) Math.floor(l/2);

        if(l==2) return merge(slice(0,1,arr),slice(1,arr));
        else if(l==3) return merge(mergeSort(slice(0,2,arr)),slice(2,arr)) ;
        else  return merge(mergeSort(slice(0,m,arr)),mergeSort(slice(m,arr))) ;
    }


    private static int[] merge (int ar1[],int ar2[]) {
        int l1 = ar1.length;
        int l2 = ar2.length;
        if(l1==0) return ar2;
        if(l2==0) return ar1;

        if(ar1[0]<ar2[0]) return concat(shift(ar1),merge(ar1,ar2));
        else return concat(shift(ar2),merge(ar1,ar2));
    }




    private  static int [] slice (int start, int end, int arr[]){
        if(start == 0) return Arrays.stream(arr).limit(end).toArray();
        return null;
    }

    private static int [] slice (int start,  int arr[]){
        return Arrays.stream(arr).skip(start).toArray();
    }

    private static int [] shift (int arr []){
        return Arrays.stream(arr).skip(0).toArray();
    }

    private static int [] concat (int ar1[], int ar2[]){
        Integer[] arr1 = Arrays.stream( ar1 ).boxed().toArray( Integer[]::new );
        Integer[] arr2 = Arrays.stream( ar2 ).boxed().toArray( Integer[]::new );
        List<Integer> resultList = new ArrayList<>(ar1.length + ar2.length);
        Collections.addAll(resultList, arr1);
        Collections.addAll(resultList, arr2);
        int [] resultArray =  resultList.stream().mapToInt(el -> el).toArray();
        return resultArray;
    }


    public static void main(String[] args) {
        int arr[] = {3,4,6,7,5,2,1};
        int sortedArr [] = mergeSort(arr);
        Arrays.stream(sortedArr).forEach(el -> System.out.println(el));
    }

}
