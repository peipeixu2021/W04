package pgdp.megamerge;

import java.util.Arrays;

public class MegaMergeSort {

	/**
	 * Sorts the array using mega merge sort with div splits
	 * @param array the array to be sorted
	 * @param div the split factor
	 * @return the sorted array
	 */
	protected int[] megaMergeSort(int[] array, int div) {
		return megaMergeSort(array, div, 0, array.length);
	}

	/**
	 * Sorts the array using mega merge sort with div splits in the defined range
	 * @param array the array to be sorted
	 * @param div the split factor
	 * @param from the lower bound (inclusive)
	 * @param to the upper bound (exclusive)
	 * @return the sorted array
	 */
	protected int[] megaMergeSort(int[] array, int div, int from, int to) {
		// TODO
		if (to <= from) {
			return new int[0];
		} else if (to <= 0 || from >= array.length) {
			return new int[0];
		} else {
			to = Math.min(array.length, to);
			from = Math.max(0, from);// get valid from and to
			int toBeSplittedLength = to - from;
			if (toBeSplittedLength == 1) {
				int[] result = new int[1];
				result[0] = array[from];
				return result;
			} else {
				int quotient = toBeSplittedLength / div;
				int remainder = toBeSplittedLength % div;
				if (remainder != 0) {
					quotient++;
				}
				int[][] arrays = new int[div][0];
				int arrayIndex = from;
				for (int i = 0; i < div; i++) {
					int subArrIndex = 0;
					boolean createdSubArr = false;
					for (int j = arrayIndex; j < Math.min(arrayIndex + quotient, to); j++) {
						if (!createdSubArr) {
							arrays[i] = new int[Math.min(quotient, to - arrayIndex)];
						}
						createdSubArr = true;
						arrays[i][subArrIndex] = array[j];
						subArrIndex++;
					}
					arrayIndex = arrayIndex + quotient;
					arrays[i] = megaMergeSort(arrays[i], div, 0, arrays[i].length);
				}
				return merge(arrays, 0, div);
			}
		}
	}

	/**
	 * Merges all arrays in the given range
	 * @param arrays to be merged
	 * @param from lower bound (inclusive)
	 * @param to upper bound (exclusive)
	 * @return the merged array
	 */
	protected int[] merge(int[][] arrays, int from, int to) {
		if (to <= from) {
			return new int[0];
		} else if (to <= 0 || from >= arrays.length) {
			return new int[0];
		} else {
			to = Math.min(arrays.length, to);
			from = Math.max(0, from);
			if (to - from == 1) {
				return arrays[from];
			}
			arrays[to - 2] = merge(arrays[to - 2], arrays[to - 1]);
			return merge(arrays, from, to - 1);
		}
	}

	/**
	 * Merges the given arrays into one
	 * @param arr1 the first array
	 * @param arr2 the second array
	 * @return the resulting array
	 */
	protected int[] merge(int[] arr1, int[] arr2) {
		// citation: solution of W04P04
		int[] merged = new int[arr1.length + arr2.length];
		int nextPositionArr1 = 0;
		int nextPositionArr2 = 0;
		int nextPositionMerged = 0;

		while(!(nextPositionArr1 >= arr1.length) && !(nextPositionArr2 >= arr2.length)) {
			if(arr1[nextPositionArr1] < arr2[nextPositionArr2]) {
				merged[nextPositionMerged++] = arr1[nextPositionArr1++];
			} else {
				merged[nextPositionMerged++] = arr2[nextPositionArr2++];
			}
		}

		while(nextPositionArr1 < arr1.length) {
			merged[nextPositionMerged++] = arr1[nextPositionArr1++];
		}
		while(nextPositionArr2 < arr2.length) {
			merged[nextPositionMerged++] = arr2[nextPositionArr2++];
		}

		return merged;
	}

	public static void main(String[] args) {
		MegaMergeSort mms = new MegaMergeSort();
		int[] arr = new int[] {};
		int[] res = mms.megaMergeSort(arr, 2);
		System.out.println(Arrays.toString(res));
	}
}
