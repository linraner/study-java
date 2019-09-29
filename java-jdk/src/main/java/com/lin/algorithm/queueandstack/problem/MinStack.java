package com.lin.algorithm.queueandstack.problem;

import java.util.Stack;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-29 15:03
 * @Description:
 **/
/*
设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

push (x) -- 将元素 x 推入栈中。
pop () -- 删除栈顶的元素。
top () -- 获取栈顶元素。
getMin () -- 检索栈中的最小元素。
示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
 */
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
public class MinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;


    /**
     * initialize your data structure here.
     */
    public MinStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int x) {
        stackData.push(x);
        if (stackMin.isEmpty()) {
            stackMin.push(x);
        } else if (x <= this.getMin()) {
            stackMin.push(x);
        }
    }

    public void pop() {
        Integer value = stackData.pop();
        if (value.equals(getMin())) {
            stackMin.pop();
        }
    }

    public int top() {
        return this.stackData.peek();
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("the minStack is empty");
        }
        return stackMin.peek();
    }
}

