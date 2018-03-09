package geeksforgeeks.binarysearch;

public class FindFloor {
  private int findFloor(int[] arr, int key, int left, int right) {
    int mid;
    while(right - left > 1) {
      mid = left + (right - left)/2;
      if(arr[mid] <= key) {
        left = mid;
      } else {
        right = mid;
      }
    }
    return arr[left];
  }

  public int findFloor(int[] arr, int key) {
    if(key < arr[0]) {
      return -1;
    } else {
      return findFloor(arr, key, 0, arr.length);
    }
  }

  public static void main(String[] args) {
    int[] a = {2, 5, 8, 10, 11, 12};
    System.out.println(new FindFloor().findFloor(a, 7));
  }
}
