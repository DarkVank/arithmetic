package com.eyg;

public class sort {

   private static int[] arr = {1,2,4,1,6,3,1,7};

    public static void main(String[] args){
//      sort.selectSort();
//        System.out.println();
//      sort.bubbleSort();
//        System.out.println();
//      sort.insertSort();


//        int dichotomy = dichotomy(nums, 7);
//        System.out.println(dichotomy);

//        System.out.println(minNumSpace());
//        process(arrNum,0,arrNum.length - 1 );

        quickSort(arr);

        for (int i : arr) {
            System.out.print(i);
        }

    }


    /**
     * 快排3.0
     * 1.0 ； 选择最后一位作为划分值，整体分为两部分，小于等于划分值 和 大于等于划分值
     * 2.0： 选择最后一位作为划分值，整体分为三部分（荷兰国旗） ，小于划分值，等于划分值，和大于划分值
     * 3.0： 随机选择一位作为划分值，和最后一位交换，整体分为三部分，小于划分值，等于划分值，和大于划分值
     * 方法步骤
     * 1、快排方法（只接收数组）
     * 2、快排具体操作方法（递归调用） 接收数组 和 其开始 和 结束值
     * 处理当前部分数组是否符合条件
     * 3、partition方法（荷兰国旗） 接收数组 和 其开始 结束值
     * 开始值作为当前数的下标
     */
    public static void quickSort(int[] arr){

        if(arr==null || arr.length<2){
            return;
        }
        quickSort(arr,0,arr.length - 1);


    }

    public static void quickSort(int[] arr ,int L ,int R){
        if (L < R) {
            swap(arr,L + (int)(Math.random() * (R - L + 1)) , R);
            int[] p = partition(arr,L,R);
            quickSort(arr,L,p[0] - 1);
            quickSort(arr,p[1] + 1,R);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;   // <区的左边界
        int more = r;      //>区的右边界

        while(l < more){   // l 当前数的位置   arr[r] -> 划分值
             if(arr[l] < arr[r]){  //当前数小于 划分值
                 swap(arr,++less,l++); //<区的右边界 与 当前数交换
             }else if(arr[l] > arr[r]){
                 swap(arr,--more,l);
             }else{
                 l++;
             }

        }
        //大于划分好的当前数据 的第一个数与数组末尾的数交换  目的  ？
        swap(arr,more,r);

        return new int[] {less + 1, more};
    }

    /**
     * 归并排序
     * 先递归二分排序，再汇总（merge）
     *
     */

    public static void process(Integer[] arrNums , int L ,int R){
        //判断L是否等于R
        if(L == R){
            return;
        }
        //求出中间下标
        int mid = L + ((R - L) >> 1) ;
        //递归排序
        process(arrNums,L,mid);
        process(arrNums,mid + 1,R);
        merge(arrNums,L,mid,R);
    }

    public static void merge(Integer[] arrNums, int L, int mid, int R) {
      //准备空间
        Integer help[] = new Integer[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;

        while(p1 <= mid && p2 <= R){
            //汇总到新数组
            help[i++] = arrNums[p1] <= arrNums[p2] ? arrNums[p1++] : arrNums[p2++];
        }
        //处理未越界的数据
        while(p1<=mid){
            help[i++] = arrNums[p1++];
        }
        // or
        while(p2<=R){
            help[i++] = arrNums[p2++];
        }

        //汇总到原来数据
        for(i = 0; i < help.length;i++){
            arrNums[L + i] = help[i];
        }

    }


    /**
     * 选择排序
     * 区域比较：等价交换 时间复杂度：o(n^2)
     * 由小到大
     */
    public static void selectSort(){
//        Integer[] arr = new Integer[10];
//      判断数组是否要执行
        if(arr == null | arr.length < 2){
            return;
        }
        for(int i = 0 ; i < arr.length; i++){
            //本次循环中的最小值的下标
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++ ){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr,i,minIndex);
        }

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i] + ",");
        }
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(N^2)
     * 采用：后置空间存储顺序数字
     * 从小到大
     */
    public static void bubbleSort(){
//      判断数组是否要执行
        if(arr == null | arr.length < 2){
            return;
        }

        for(int e = arr.length -1;e > 0 ;e--){
            for(int i = 0;i< e ;i++){
                if(arr[i] > arr[i+1] ){
                    swap(arr,i,i+1);
                }
            }
        }

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i] + ",");
        }

    }

    /**
     * 插入排序
     * 时间复杂度：O(N^2) 最坏
     * 采用：从后往前交换
     * 从小到大
     */
    public static void insertSort(){
        // 0 - 0 有序
        // 0 - i 有序
        for(int i = 1;i < arr.length ;i++){

            //比较交换，出界停，或无小于当前数停
            for(int j = i - 1 ; j>=0 && arr[j] > arr[j+1] ; j--){
                swap(arr,j,j+1);
            }
        }

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i] + ",");
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        /**
         * 0 ^ N = N   N ^ N = 0   (异或)
         */
//        arr[i] = arr[i] ^ arr[j];
//        arr[j] = arr[i] ^ arr[j];
//        arr[i] = arr[i] ^ arr[j];

    }

    private static Integer[] nums = {0,1,2,3,4,5,6,7};

    /**
     * 二分排序
     * 在有序的数组中寻找是否包含某个数 ，有返回：下标，无返回： -1
     * [a,b]:左闭右闭
     * [a,b)：左闭右开
     */
    public static int dichotomy(Integer[] nums,int target){
        int left = 0;
        int right = nums.length - 1;
        int middle = -1;
//       [a,b]
//        while(left <= right){
//            middle = (left + right) / 2;
//
//            if(nums[middle] > target){
//                right = middle - 1;
//            }else if(nums[middle] < target){
//                left = middle + 1;
//            }else{
//               return middle;
//            }
//        }
//        [a,b)
        right = nums.length;
        while(left < right){
            middle = (left + right) / 2;

            if(nums[middle] > target){
                right = middle ;
            }else if(nums[middle] < target){
                left = middle + 1;
            }else{
                return middle;
            }
        }
        return -1;
    }

    private static Integer[] arrNum = {4,3,2,4,5};
    /**
     * 局部最小(a > b < c)
     * 0 - 1      n-1 - n
     */
    public static int  minNumSpace(){
        //二分求值[a,b)
        int left = 1;
        int right = arrNum.length;
        int middle ;

        //判断0 - 1 ： 0 是否小于1
        if(arrNum[0] < arrNum[1]){
            return arrNum[0];
        }
        //判断 N -2 - N-1 ,N-1是否小于N-2
         if(arrNum[right - 1] < arrNum[right - 2]){
             return  arrNum[right];
         }
        //二分求值[a,b)
        while(left < right){

          middle = (left + right ) / 2;
          if(arrNum[middle - 1] < arrNum[middle]){
              right = middle ;
          }else if(arrNum[middle] > arrNum[middle + 1]){
              left = middle + 1;
          }else{
              //如果arr[middle - 1] > arr[middle] < arr[middle + 1]
              return arrNum[middle];
          }
        }
        return -1;
    }





}
