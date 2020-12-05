package MinMaxAlgorithms;

public class MaxSearch {
    // O(n)
    public  static int maxSearchOnArray(int [] arr){
        int max = arr[0];
        for(int i=1; i<arr.length; i++){
            if(arr[i]>max){
                max=arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int [] arr ={0,1,2,3,4,5,6,7,8,9,55,4,5,255};
        System.out.println(maxSearchOnArray(arr));
    }
}
