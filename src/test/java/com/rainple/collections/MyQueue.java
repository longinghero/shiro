package com.rainple.collections;

/**
 * ����
 */
public class MyQueue {

    /**
     * ���йܵ������ܵ��д�ŵ����ݴ��ڹܵ��ĳ���ʱ��������push���ݣ�ֱ���ӹܵ���pop���ݺ�
     */
    private Object[] chanel;
    //���е�ͷ������ȡ����ʱ���Ǵ�ͷ����ȡ
    private int head;
    //����β����push����ʱ���Ǵ�β�����
    private int tail;
    //�ܵ����Ѿ���ŵ�������
    private int size;
    //�ܵ����ܴ�����ݵ��������
    private int maxCapacity;
    //����±�
    private int maxIndex;

    public MyQueue(int initialCapacity){
        chanel = new Object[initialCapacity];
        maxCapacity = initialCapacity;
        maxIndex = initialCapacity - 1;
        head = tail = -1;
        size = 0;
    }
    public MyQueue(){
        chanel = new Object[16];
        maxCapacity = 16;
        head = tail = -1;
        size = 0;
        maxIndex = 15;
    }

    /**
     * ���ܵ����������
     * @param object
     */
    public void push(Object object){
        if (size >= maxCapacity){
            return;
        }
        if (++tail > maxIndex){
            tail = 0;
        }
        chanel[tail] = object;
        size++;
    }

    /**
     * �ӹܵ��е�������
     * @return
     */
    public Object pop(){
        if (size <= 0){
            return null;
        }
        if (++head > maxIndex){
            head = 0;
        }
        size--;
        Object old = chanel[head];
        chanel[head] = null;
        return old;
    }

    /**
     * �鿴��һ������
     * @return
     */
    public Object peek(){
        return chanel[head];
    }

    /**
     * �ܵ��д洢��������
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * �ܵ��Ƿ�Ϊ��
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * ��չܵ�
     */
    public void clear(){
        for (int i = 0; i < chanel.length; i++) {
            chanel[i] = null;
        }
        tail = head = -1;
        size = 0;
    }

    @Override
    public String toString() {
        if (size <= 0) return "{}";
        StringBuilder builder = new StringBuilder(size + 8);
        builder.append("{");
        int h = head;
        int count = 0;
        while (count < size){
            if (++h > maxIndex) h = 0;
            builder.append(chanel[h]);
            builder.append(", ");
            count++;
        }
        return builder.substring(0,builder.length()-2) + "}";
    }
}
