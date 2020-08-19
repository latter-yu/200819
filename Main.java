import java.util.*;

public class Main {
    public int Sum_Solution(int n) {
        // 求 1 + 2 + 3 + ... + n
        // 要求不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句（A ? B : C）。
        
        int sum = n;
        if (n == 1) {
            return n;
        }
        sum += Sum_Solution(n - 1);
        return sum;
    }

//    public static void main(String[] args) {
//        int n = 5;
//        System.out.println(Sum_Solution(n));
//    }

    public String ReverseSentence(String str) {
        // 牛客最近来了一个新员工 Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
        // 同事 Cat 对 Fish 写的内容颇感兴趣
        // 有一天他向 Fish 借来翻看，但却读不懂它的意思。
        // 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了
        // 正确的句子应该是 “I am a student.”。
        // Cat 对一一的翻转这些单词顺序可不在行，你能帮助他么？

        String[] strArr = str.split(" ");
        if (strArr.length == 0 || strArr.length == 1) {
            // 不判断会报错
            return str;
        }
        String s = strArr[strArr.length - 1] + " ";
        for (int i = strArr.length - 2; i > 0; i--) {
            s += strArr[i] + " ";
        }
        s += strArr[0];
        return s;
    }

    public static void FindNumsAppearOnce(int [] array, int num1[] , int num2[]) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int a : array) {
            if (!map.containsKey(a)) {
                map.put(a, 1);
            } else {
                map.put(a, 2);
            }
        }
        int count = 0;
        for (Map.Entry m : map.entrySet()) {
            if ((int)m.getValue() == 1) {
                if (count == 0) {
                    num1[0] = (int)m.getKey();
                    count++;
                } else {
                    num2[0] = (int)m.getKey();
                }
            }
        }
    }

//    public static void main(String[] args) {
//        int[] arr = {2, 4, 3, 6, 3, 2, 5, 5};
//        int[] num1 = new int[1];
//        int[] num2 = new int[1];
//        FindNumsAppearOnce(arr, num1, num2);
//    }

    public int InversePairs(int [] array) {
        // 错
        int count = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                String str = array[i] + " " + array[j];
                if (array[i] > array[j]) {
                    map.put(str, 1);
                }
            }
        }
        for (String s : map.keySet()) {
            count++;
        }
        return count % 1000000007;
    }

//    public static void main(String[] args) {
//        int[] array = {1, 2, 3, 4, 5, 6, 7, 0};
//        System.out.println(InversePairs(array));
//    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
class Solution {
    public int TreeDepth(TreeNode root) {
        // 输入一棵二叉树，求该树的深度。
        // 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径
        // 最长路径的长度为树的深度。

        // 递归
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }
    // 非递归（层序遍历）
    public int TreeDepth1(TreeNode root) {
        int depth = 0;
        if(root != null) {
            Queue q = new LinkedList<TreeNode>();
            q.offer(root);
            q.offer(new TreeNode(Integer.MIN_VALUE));
            while(!q.isEmpty()) {
                TreeNode tmp = (TreeNode)q.poll();
                if(tmp.val != Integer.MIN_VALUE) {
                    if(tmp.left != null)
                        q.offer(tmp.left);
                    if(tmp.right != null)
                        q.offer(tmp.right);
                } else {
                    // 当遇到特殊节点时，说明当前层已经遍历完了同时下一层的所有节点已经全部入队了，在每层的最后压入一个特殊节点
                    depth++;
                    if(!q.isEmpty()) {
                        // 重点，不然会死循环
                        q.offer(new TreeNode(Integer.MIN_VALUE));
                    }
                }
            }
        }
        return depth;
    }

    public boolean IsBalanced_Solution(TreeNode root) {
        // 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
        // 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树

        return treeDepth(root) != -1;
    }
    public int treeDepth (TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = treeDepth(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }
}