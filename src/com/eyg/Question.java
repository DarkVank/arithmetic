package com.eyg;

public class Question {


    public static int[] arr = {1,2,4,6,6,7,7,4};
    public static int target = 4;

    public static void main(String[] args){
//        printOddTimesNum1();
//        printOddTimesNum2();

//        int i = smallSum(arr);
//
//        System.out.println(i);

//       onePartition(arr,target);
        twoPartition(arr,target);


    }

    /**
     * 荷兰国旗 O(N)
     * 1、x为划分，
     * 小于等于x 在左边 i++，
     * 2、x为划分，
     * 小于x ,交换，i++ 在左边，
     * 等于 x i++
     * 在中间，大于 x在右边  i不变
     */
    public static void twoPartition(int[] arr,int target){
        //准备边界 2、三等分
        int L = -1;
        int R = arr.length;
        int i = 0;

        while(i != R){
            if(arr[i] < target){
                swap(arr,++L,i++);
            }else if(arr[i] > target){
                //>区的左边界 -1 与 当前数交换
                swap(arr,--R,i);

            }else{
                i++;
            }
        }

        for(i=0 ; i<arr.length ; i++){
            System.out.print(arr[i]);
        }

    }

    /**
     * 荷兰国旗 O(N)
     * 1、x为划分，
     * 小于等于x 在左边 i++，
     */
    public static void onePartition(int[] arr,int target){
        //准备空间,左边界 1、
        int L = -1;
        int i = 0;
       while(i<arr.length){
           if(arr[i] <= target){
               swap(arr,i,++L);

           }
           i++;
       }

        for( i=0 ; i<arr.length ; i++){
            System.out.print(arr[i]);
        }
    }

    private static void swap(int[] arr, int i, int L) {
        //交换数据
        int temp = arr[i];
        arr[i] = arr[L];
        arr[L] = temp;

    }

    /**
     *小和问题
     * 求出数组中每个数左侧小于它的数，汇总起来
     */

    public static int smallSum(int[] arr){
        //判断数组是否符合条件
        if(arr == null || arr.length < 2){
            return 0;
        }

        //执行归并排序
        return process(arr,0,arr.length - 1);
    }
    public static int process(int[] arr,int L,int R){
        if(L == R){
            return 0 ;
        }
        //归并排序
        //求出中间数
        int mid = L + ((R-L) >> 1);

        return process(arr,L,mid) + process(arr,mid+1,R) + merge(arr,L,mid,R);
    }

    public static int merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L +1];
        int p1 = L;
        int p2 = mid + 1;
        int i = 0;
        int res = 0;
        //外排序，汇总
        while(p1<=mid && p2<=R){
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[i] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while(p1 <= mid){
            help[i++] = arr[p1++];
        }
        while(p2<=R){
            help[i++] = arr[p2++];
        }
        //拷贝回原数组
        for(i = 0;i < help.length;i++){
            arr[L + i] = help[i];
        }
        return res;
    }


    /**
     * 奇偶问题 :OddEven
     * 1、一个数组中，只有一种数是出现奇数次，other numbers出现的是偶数次。
     * 方法：由 0 异或数组中所有数，将会得到那个出现奇数次数字（基于 N ^ N = 0）
     * 2、一个数组中，有两个数出现了奇数次，other numbers出现了偶数次
     * 方法：设两个数分别为： a  b
     *      求出 a ^ b,存储在err中
     *      求出最右侧的1,由于a != b ,所以，a b 肯定有一位不同
     *      异或这位等于1的数组元素，存储在otherOne
     */

    public static void printOddTimesNum1(){
        int err = 0;
     /* for(int i = 0 ;i<arr.length; i++){
              err ^= arr[i];
        }*/

        for(int item : arr){
            err ^= item;
        }
        System.out.println(err);
    }

    public static void printOddTimesNum2(){
        int err = 0;
        /*
          设两个数分别为： a  b
          求出 a ^ b,存储在err中
         */
        for(int item : arr){
            err ^= item;
        }
        //求出最右侧的1,由于a != b ,所以，a b 肯定有一位不同
        int rightOne = err & (~err + 1);
        int otherOne = 0;

        for(int item : arr){
            if((rightOne & item) == 1){
                otherOne ^= item;
            }
        }

        System.out.println(otherOne + "," + (err ^ otherOne));

    }
}
