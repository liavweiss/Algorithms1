package MinMaxAlgorithms;

/**
 * This class represents the solution to the problem of finding two maximum organs using a stack.
 */
public class MinMax_stack {

   public static int max1Max2(Node [] a , int low , int high){
        if(low<high){
           int index=0;
           int mid = (low+high)/2;
           int i=max1Max2(a,low,mid);
           int j=max1Max2(a,mid+1,high);

           if(a[i].number>a[j].number){
               a[i].st.push(a[j].number);
               index=i;
           }
           else{
               a[j].st.push(a[i].number);
               index=j;
           }
           return index;
        }
        else{
            return low;
        }
    }

//A shell function that receives an array and sends each member to Node.
    public static void MaxMax2(int[] arr) {
        int n=arr.length;
        Node[] node=new Node[n];
        for (int i = 0; i < n; i++) {
            node[i] =new Node(arr[i]);
        }
        int index=	max1Max2(node,0,n-1);
        int max1=node[index].number;
        int max2=node[index].st.pop();
        while(!node[index].st.isEmpty()) {
            int temp = node[index].st.pop();
            if(temp>max2) max2=temp;
        }
        System.out.println("Max1: " + max1 + ", max2: " + max2);
    }

    public static void main(String[] args) {
        int [] arr ={0,1,2,3,4,5,6,7,8,9,55,4,5,255};
        MaxMax2(arr);
    }
}
