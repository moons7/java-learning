import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class BinaryTreeIteratorTest {

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
    }

    /**
     * 该逻辑其实为前序遍历构建二叉树
     *
     * @param deep
     * @return
     */
    public TreeNode createTestTree(int deep) {
        if (deep <= 0) {
            return null;
        }
        TreeNode treeNode = new TreeNode();
        treeNode.value = RandomUtil.randomInt(50);
        System.out.print(treeNode.value + " ");
        treeNode.left = createTestTree(deep - 1);
        treeNode.right = createTestTree(deep - 1);
        return treeNode;
    }


    /**
     * 递归实现
     */
    @Test
    public void allTest() {
        System.out.println("前序构建二叉树: ");
        TreeNode rootNode = createTestTree(3);
        System.out.println(" ");
        System.out.println("递归前序遍历: ");
        frontPrint(rootNode);
        System.out.println(" ");
        System.out.println("递归中序遍历: ");
        middlePrint(rootNode);
        System.out.println(" ");
        System.out.println("递归后序遍历: ");
        backPrint(rootNode);
        System.out.println(" ");
        System.out.println("非递归前序遍历二叉树: ");
        frontPrintWithNoRecursion(rootNode);
        System.out.println(" ");
        System.out.println("非递归中序遍历二叉树: ");
        middlePrintWithNoRecursion(rootNode);
        System.out.println(" ");
        System.out.println("非递归后序遍历二叉树: ");
        backendPrintWithNoRecursion(rootNode);
        System.out.println(" ");
    }

    /**
     * 前序遍历优先打印本结点，找左结点，在找右结点
     * 打印本节点(中)-->左-->右
     *
     * @param treeNode
     */
    public void frontPrint(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.value + " ");
        frontPrint(treeNode.left);
        frontPrint(treeNode.right);
    }

    /**
     * 中序遍历优先找左结点，在打印本结点值
     * 左-->打印本节点(中)-->右
     *
     * @param treeNode
     */
    public void middlePrint(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        middlePrint(treeNode.left);
        System.out.print(treeNode.value + " ");
        middlePrint(treeNode.right);
    }

    /**
     * 后序遍历优先找左结点，在优先遍历右结点，未有子结点则在打印本结点值
     * 然后向上回溯
     * 左-->右--->打印本节点(中)
     *
     * @param treeNode
     */
    public void backPrint(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        backPrint(treeNode.left);
        backPrint(treeNode.right);
        System.out.print(treeNode.value + " ");
    }


    /**
     * 非递归实现
     */
    @Test
    public void nonRecursionTest() {
        System.out.println("前序构建二叉树: ");
        TreeNode rootNode = createTestTree(3);
        System.out.println(" ");
        System.out.println("递归中序遍历: ");
        middlePrint(rootNode);
        System.out.println(" ");
        System.out.println("非递归中序遍历二叉树: ");
        middlePrintWithNoRecursion(rootNode);
        System.out.println(" ");
    }


    /**
     * 非递归前序遍历二叉树
     *
     * @param treeNode
     */
    public void frontPrintWithNoRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.empty()) {
            treeNode = stack.pop(); //出栈的不一定是已经处理完的结点的根结点，所以需要一个特殊标识，此处为NULL
            if (treeNode != null) {
                if (treeNode.right != null) {
                    stack.push(treeNode.right);
                }
                if (treeNode.left != null) {
                    stack.push(treeNode.left);
                }
                stack.push(treeNode);
                stack.push(null); //该标记根结点
            } else { //遇到根结点则再次出栈
                treeNode = stack.pop();
                System.out.print(treeNode.value + " "); //二叉树遍历算法本质上以特点顺序打印根结点
            }
        }
    }

    /**
     * 非递归中序遍历二叉树
     *
     * @param treeNode
     */
    public void middlePrintWithNoRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.empty()) {
            treeNode = stack.pop(); //出栈的不一定是已经处理完的结点的根结点，所以需要null标识
            if (treeNode != null) {
                if (treeNode.right != null) {
                    stack.push(treeNode.right);
                }
                stack.push(treeNode);
                stack.push(null);
                if (treeNode.left != null) {
                    stack.push(treeNode.left);
                }
            } else { //遇到根结点则再次出栈
                treeNode = stack.pop();
                System.out.print(treeNode.value + " ");
            }
        }
    }


    /**
     * 非递归后序遍历二叉树
     *
     * @param treeNode
     */
    public void backendPrintWithNoRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.empty()) {
            treeNode = stack.pop(); //出栈的不一定是已经处理完的结点的根结点，所以需要null标识
            if (treeNode != null) {
                stack.push(treeNode);
                stack.push(null); //该标记根结点
                if (treeNode.right != null) {
                    stack.push(treeNode.right);
                }
                if (treeNode.left != null) {
                    stack.push(treeNode.left);
                }
            } else { //遇到根结点则再次出栈
                treeNode = stack.pop();
                System.out.print(treeNode.value + " "); //遍历算法本质上打印根结点
            }
        }
    }
}
