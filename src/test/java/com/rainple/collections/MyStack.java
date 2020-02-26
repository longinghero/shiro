package com.rainple.collections;

/**
 *ջ���ݽṹ
 */
public class MyStack {

    //�������
    private Object[] data;
    //������
    private int size;
    //ջ��
    private int top;
    //Ĭ��ջ��С
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    //�������
    private int maxCapacity;

    public MyStack(){
        data = new Object[DEFAULT_INITIAL_CAPACITY];
        top = -1;
        size = 0;
        maxCapacity = DEFAULT_INITIAL_CAPACITY;
    }

    public MyStack(int initialCapacity){
        data = new Object[initialCapacity];
        top = -1;
        size = 0;
        maxCapacity = initialCapacity;
    }

    /**
     * ��ջ�з�����
     * @param obj
     * @return
     */
    public boolean push(Object obj){
        if (size >= maxCapacity) return false;
        data[++top] = obj;
        size++;
        return true;
    }

    /**
     * ��ջ�е�������
     * @return
     */
    public Object pop(){
        if (size <= 0) return null;
        size--;
        return data[top--];
    }

    /**
     * �鿴����
     */
    public Object peek(){
        if (isEmpty()) return null;
        return data[top];
    }

    /**
     * ���ջ����
     */
    public void clear(){
        while (top > -1){
            data[top--] = null;
        }
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

}
