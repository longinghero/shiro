package com.rainple.collections;

import java.util.Arrays;

public class MyHashMap<K,V> {

    //Ĭ�ϳ�ʼ������
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    //hash����������
    private int threshold;
    //ת����������ֵ
    static final int TREEIFY_THRESHOLD = 1 << 3;
    //������ת������ֵ
    static final int UNTREEIFY_THRESHOLD = 6;
    //�������ӣ���map�����ݴﵽ �������Ӻ�threshold�ĳ˻�ʱ�Զ����ݣ�����hash��ײ
    private float load_factory;
    private static final float DEFAULT_LOAD_FACTORY = 0.75f;
    //��������
    private int size;
    //�������ݵ����飬��������ַ�ķ�ʽ�������ײ����
    //�����ȴ��� TREEIFY_THRESHOLD ֵʱ�Ὣ����ת���ɶ�����
    private Node<K,V>[] table;

    public MyHashMap() {
        this.threshold = DEFAULT_INITIAL_CAPACITY;
        this.load_factory = DEFAULT_LOAD_FACTORY;
        table = new Node[threshold];
    }

    public MyHashMap(int initialCapacity){
        this.threshold = initialCapacity;
        this.load_factory = DEFAULT_LOAD_FACTORY;
        table = new Node[threshold];
    }

    public MyHashMap(int capacity, float load_factory) {
        this.threshold = capacity;
        this.load_factory = load_factory;
        table = new Node[threshold];
    }

    public boolean putAll(MyHashMap<K,V> map){
        if (map == null || map.size() <= 0){
            return false;
        }
        Node<K, V>[] nodes = map.entrySet();
        for (Node<K, V> node : nodes) {
            put(node.key,node.val);
        }
        return true;
    }

    public void put(K key, V value){
        int hash = hash(key);
        int index = indexFor(hash);
        /**����λ��û��ռ�ã�ֱ�ӽ������ֵ�ŵ���λ����
         * �����ռ�ã����ڸ�λ������������
         * �������ȴﵽ TREEIFY_THRESHOLD �󽫻�ת���ɶ���������ʱû�о��������
         */
        if (table[index] == null) {
            Node<K, V> newNode = new Node<>(hash,key, value, null);
            table[index] = newNode;
            size++;
        }else {
            Node<K,V> first = table[index];
            if (first instanceof Tree){
                putTreeVal(hash,key, value, (Tree<K, V>) first);
            }else {
                putVal(key, value, hash, first,table);
            }
        }
        //����
        if (size > threshold*load_factory){
           threshold = threshold << 1;
           resize(threshold);
        }
    }

    /**
     * ����
     * @param newThreshold ����Ĵ�С
     */
    private void resize(int newThreshold) {
        Node<K,V>[] oldTab = table;
        table = new Node[newThreshold];
        for (int i = 0; i < oldTab.length; i++) {
            Node<K,V> node = oldTab[i];
            if (node != null) {
                int index = node.hash & (newThreshold - 1);
                table[index] = node;
//                if (node instanceof Tree){
//                    MyArrayList<Node<K,V>> all = ((Tree<K,V>) node).getTreeNodes();
//                    for (int j = 0; j < all.size(); j++) {
//                        Node<K, V> n = all.get(j);
//                        int index = n.hash & (newThreshold - 1);
//                        putOltNodeToNewNode(table,n,index);
//                    }
//                }else {//��������ÿ��Ԫ������hash�����ǽ���������ֱ�ӷ���������
//                    int index = node.hash & (newThreshold - 1);
//                    table[index] = node;
//                }
                oldTab[i] = null;
            }
        }
    }

    private void putOltNodeToNewNode(Node[] newTable, Node<K, V> current, int index) {
        Node newNode = newTable[index];
        if (newNode == null){
            newTable[index] = current;
        } else{
            Node n = newNode;
            int count = 0;
            while (n != null){
                Node next = n.next;
                count++;
                if (next == null){
                    if (count > TREEIFY_THRESHOLD){
                        transferToBinaryTree(newTable,current.hash);
                    }else {
                        n.next = current;
                    }
                }
                n = next;
            }
        }
    }

    /**
     * �� key-value ���������У��ȼ����ŵ�key��Ӧ���±������±�ȷ������������е�λ��
     * �����λ���Ѿ���ֵ�����ж�key�Ƿ���ȣ�������滻��ԭ����ֵ���������������һ�����ݣ��������ж�keyֵ�Ƿ���ȣ�����滻�����Դ�ѭ��
     * ����������û����ͬ��keyʱ�������ݴ����ڱ�β��
     * @param key ���������Դ��key��ͬ����������
     * @param value ֵ������Ϊ����ֵ�����ظ�
     * @param hash ����Ӧ��hashֵ
     * @param first �����ͷ��
     */
    private void putVal(K key, V value, int hash, Node<K, V> first,Node[] tbl) {
        Node<K, V> parent = first;
        //���ڼ�¼�Ƿ�ﵽת��������
        for (int count = 0; ; count++) {
            Node<K, V> next = parent.getNext();
            if (parent.key.equals(key) || parent.hash == key.hashCode()) {
                parent.val = value;
                break;
            }
            if (next == null) {
                parent.setNext(new Node<>(hash,key, value, null));
                if (count >= TREEIFY_THRESHOLD - 2) {
                    //ת���ɶ�����
                    transferToBinaryTree(tbl, hash);
                }
                size++;
                break;
            }
            parent = next;
        }
    }

    /**
     * �����ݷ��뵽 ��������,����key��hashCode �Ĵ�С��ȷ���ڵ��ŵ�λ��
     * @param key ��
     * @param value ֵ
     * @param root ������ͷ��
     */
    private void putTreeVal(int hash, K key, V value, Tree<K, V> root) {
        Node<K,V> newNode = new Node<K,V>(hash,key,value,null);
        root.insert(newNode);
        size++;
    }

    /**
     * ����ת������
     * @param table
     * @param hash
     */
    private void transferToBinaryTree(Node<K, V>[] table, int hash) {
        int index = indexFor(hash);
        Node<K, V> node = table[index];
        Tree tree = new Tree();
        while (node != null) {
            tree.insert(node);
            Node<K, V> n = node.getNext();
            node.next = null;
            node = n;
        }
        table[index] = tree;
    }

    /**
     * ����key��ȡ����
     * @param key
     * @return
     */
    public V get(Object key){
        int index = indexFor(hash(key));
        Node<K, V> first = table[index];
        if (first instanceof Tree){
            Node node = ((Tree) first).get(key);
            return (V) node.val;
        }
        Node<K, V> node = first;
        while (node != null){
            if (node.key.equals(key)){
                return node.val;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * ȷ�����ݴ�ŵ��±�
     * @param hash
     * @return
     */
    private int indexFor(int hash){
        return hash & (table.length - 1);
    }

    /**
     * �ߵ�λ��ϵļ��㷽ʽ
     * @param key
     * @return
     */
    private int hash(Object key){
       int h;
       return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    static class Tree<K,V> extends Node{
        private Tree<K, V> root;
        private Tree<K, V> leftChild;
        private Tree<K, V> rightChild;
        private Node<K,V> data;
        private int size;

        public Tree(){}

        public Tree(Tree<K, V> leftChild, Tree<K, V> rightChild, Node<K,V> data) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.data = data;
        }
        /**
         * ��ȡ�ڵ�
         */
        public Node<K,V> get(Object key){
            Tree<K, V> current = root;
            int keyCode = key.hashCode();
            while (current != null) {
                int currentCode = current.data.key.hashCode();
                if (keyCode > currentCode) {
                    current = current.rightChild;
                }else if (keyCode < currentCode) {
                    current = current.leftChild;
                }else {
                    if (key.equals(current.data.key)) {
                        return current.data;
                    }
                    current = current.rightChild;
                }
            }
                return null;
        }

        public int size(){return size;}

        /**
         * ��������,����key��hashCode��ȷ��node�ڶ������е�λ��
         * ������� key �� hashCode ��ֵ��� �����ķŽ� �Ҳ�
         * @param node
         * @return
         */
        public Node<K,V> insert(Node<K,V> node){
            if (node == null)
                return null;
            Tree<K, V> nt = new Tree<>(null,null,node);
            if (root == null) {
                root = nt;
                size++;
                return root.data;
            }
            Tree<K, V> current = root;
            int insCode = node.key.hashCode();
            Tree<K, V> parent;
            while (current != null){
                int currentCode = current.data.key.hashCode();
                parent = current;
                if (insCode > currentCode){
                    current = parent.rightChild;
                    if (current == null){
                        parent.rightChild = nt;
                        size++;
                        break;
                    }
                }else if (insCode < currentCode){
                    current = parent.leftChild;
                    if (current == null){
                        parent.leftChild = nt;
                        size++;
                        break;
                    }
                }else {
                    Object key = node.key;
                    Object key1 = current.data.key;
                    if (key.equals(key1)){
                        current.data = node;
                        break;
                    }
                    current = current.rightChild;
                }
            }
            return nt.data;
        }

        /**
         * ɾ������
         * @param key
         */
        public void delete(Object key){
            if (root == null) return;
            Tree<K,V> current = root;
            Tree<K,V> parent = null;
            Tree<K,V> delTree = null;
            boolean isLeft = true;
            int keyCode = key.hashCode();
            /**����ɾ���ڵ��Լ��丸�ڵ�*/
            if (keyCode == root.data.hash){//ɾ��root�ڵ�
                delTree = root;
                parent = root;
            }else {
                while (current != null) {
                    int currentCode = current.data.hash;
                    parent = current;
                    if (keyCode > currentCode) {
                        current = current.rightChild;
                        if (current != null && current.data.hash == keyCode){
                            isLeft = false;
                            delTree = current;
                            break;
                        }
                    } else if (keyCode < currentCode) {
                        current = current.leftChild;
                        if (current != null && current.data.hash == keyCode){
                            isLeft = true;
                            delTree = current;
                            break;
                        }
                    }
                    if (current == null){
                        return;
                    }
                }
            }
            /**���Ҹ��ڵ��ɾ���ڵ����*/

            if (delTree.leftChild == null && delTree.rightChild == null){//ɾ��Ҷ�ӽڵ�
                if (delTree == root) {
                    root = null;
                }else{
                    if (isLeft){
                        parent.leftChild = null;
                    }else {
                        parent.rightChild = null;
                    }
                }
            }else if (delTree.leftChild == null){//ɾ��ֻ������Ҷ�Ľڵ�
                if (delTree == root){
                    root = delTree.rightChild;
                }else {
                    if (isLeft){
                        Tree<K, V> leftChild = delTree.rightChild;
                        parent.leftChild = leftChild;
                    }else {
                        Tree<K, V> rightChild = delTree.rightChild;
                        parent.rightChild = rightChild;
                    }
                    delTree.rightChild = null;
                }
            }else if (delTree.rightChild == null){//ɾ��ֻ����Ҷ�ӵĽڵ�
                if (delTree == root){
                    root = delTree.leftChild;
                }else {
                    if (isLeft){
                        parent.leftChild = delTree.leftChild;
                    }else {
                        parent.rightChild = delTree.leftChild;
                    }
                    delTree.leftChild = null;
                }
            }else {//ɾ���������ӽڵ�Ľڵ�
                /**
                 * ɾ���������ӽڵ������Ƚϸ���һЩ
                 * ��Ϊ���������ɾ�����ڵ��Ǹ��ڵ�
                 * 1��ɾ�����ڵ㣺���Һ�̽ڵ㣬��һ�����ɣ���̽ڵ�������ɾ���ڵ�����ӽڵ�����ӽڵ�������Ҷ�ӽڵ�
                 */
               if (delTree == root){//ɾ���ڵ�
                   Tree<K, V> rightChild = root.rightChild;
                   Tree<K, V> rep = rightChild.leftChild;
                   //���ӽڵ�û�����ӽڵ㣬ֱ�ӽ����ӽڵ��滻�ɸ��ڵ�
                   if (rep == null){
                       root.rightChild = root;
                       root = null;
                   }else {
                       //�ݹ�������ӽڵ�������Ҷ�ӽڵ㣬����Ҷ�ӽڵ��滻�����ڵ���
                       while (rep != null) {
                            leftChild = rep.leftChild;
                            parent = rep;
                            if (leftChild.leftChild == null){
                                root.data = leftChild.data;
                                parent.leftChild = null;
                                break;
                            }
                            rep = leftChild;
                       }
                   }
               }else {
                   //���Һ�̽ڵ㲢����ɾ����
                   Tree<K, V> replaceNode = findAndDeleteReplaceNode(delTree);
                   if (replaceNode == delTree.rightChild){//�滻�ڵ�Ϊɾ���ڵ��ҽڵ�
                       if (isLeft){
                           parent.leftChild = replaceNode;
                           replaceNode.leftChild = delTree.leftChild;
                       }else {
                           parent.rightChild = replaceNode;
                           replaceNode.leftChild = delTree.leftChild;
                       }
                   }else {//��ɾ���ڵ�����ӽڵ�
                       if (isLeft){
                           parent.leftChild.data = replaceNode.data;
                       }else {
                           parent.rightChild.data = replaceNode.data;
                       }
                   }
               }
            }
            size--;
        }

        /**
         * ���Ҷ������ĺ�̽ڵ�
         * @param delTree
         * @return
         */
        private Tree<K, V> findAndDeleteReplaceNode(Tree<K, V> delTree) {
            Tree<K, V> rightChild = delTree.rightChild;
            Tree<K, V> target = null;
            Tree<K, V> current = rightChild.leftChild;
            if (current == null){
                target = rightChild;
            }else {
                Tree<K,V> parent = rightChild;
                while (current.leftChild != null) {
                    parent = current;
                    current = current.leftChild;
                }
                target = current;
                parent.leftChild = null;
            }
            return target;
        }

        /**
         * ��ȡ���еĶ�����value���ݣ����ݼ���hashcode��������
         * @return
         */
        public MyArrayList<Node<K,V>> getTreeNodes(){
            MyArrayList<Node<K,V>> list = new MyArrayList<>();
            midIterate(root,list);
            return list;
        }

        /**
         * �������������
         */
        public void clear(){
           clear(root);
           root = null;
        }

        private void clear(Tree<K,V> node) {
            if (node != null){
                clear(node.leftChild);
                node.leftChild = null;
                clear(node.rightChild);
                node.rightChild = null;
            }
        }

        /**
         * �������
         * @param root ���ڵ�
         * @param list
         */
        public void midIterate(Tree<K, V> root, MyArrayList<Node<K,V>> list){
            if (root != null) {
                midIterate(root.leftChild, list);
                list.add(root.data);
                midIterate(root.rightChild, list);
            }
        }

        /**ǰ�����
         * @param root
         */
        public void beforeIterate(Tree<K, V> root){
            if (root != null) {
                this.root = root;
                beforeIterate(root.leftChild);
                root = null;
                beforeIterate(root.rightChild);
            }
        }

        /**
         * ��������
         * @param root
         * @param list
         */
        public void behindIterate(Tree<K, V> root, MyArrayList<Node<K,V>> list){
            if (root != null) {
                midIterate(root.leftChild, list);
                midIterate(root.rightChild, list);
                list.add(root.data);
            }
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "root=" + root +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    ", data=" + data +
                    '}';
        }
    }

    static class Node<K ,V>{
        int hash;//���ݵ�ʱ���õ�
        K key;
        V val;
        private Node<K,V> next;
        Node( int hash,K key, V val, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.val = val;
            this.setNext(next);
        }

        Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    ", next=" + next +
                    '}';
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }
    /**
     * ��ײ��
     * @return
     */
    public String boomRate(){
        double ds = Double.parseDouble(size + "");
        double boomCount = 0;
        for (Node<K, V> node : table) {
            if (node != null){
                boomCount++;
            }
        }
        String rate = String.valueOf(((ds - boomCount) / ds * 100));
        return rate + "%";
    }

    /**
     * ʹ����
     * @return
     */
    public String useRate(){
        double used = 0;
        for (Node<K, V> node : table) {
            if (node != null) used++;
        }
        double v = Double.parseDouble(threshold + "");
        return String.valueOf((used / v * 100)) + "%";
    }

    public V replace(Object key,V v){
        Node<K, V> node = getNode(key);
        if (node == null) return null;
        V oldVal = node.val;
        node.val = v;
        return oldVal;
    }

    public boolean replace(Object key,V oldValue,V newValue){
        Node<K, V> node = getNode(key);
        if (node == null) return false;
        if (node.val == oldValue || node.val.equals(oldValue)){
            node.val = newValue;
            return true;
        }
        return false;
    }

    /**
     * ��ȡ�ڵ�
     * @param key
     * @return
     */
    private Node<K,V> getNode(Object key){
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> node = table[index];
        if (node == null) return null;
        if (node instanceof Tree){
            return ((Tree) node).get(key);
        }
        Node<K, V> n = node;
        while (n != null){
            if (n.key.equals(key)){
                return n;
            }
            n = n.getNext();
        }
        return null;
    }

    /**
     * �ж��Ƿ����key
     * @param key
     * @return
     */
    public boolean containsKey(Object key){
        return getNode(key) != null;
    }

    /**
     * �����Ƿ����value
     * @param val
     * @return
     */
    public boolean containsValue(Object val){
        for (Node<K, V> node : entrySet()) {
            if ( val == node.val || node.val.equals(val))
                return true;
        }
        return false;
    }

    /**
     * ��ȡ���е�valueֵ
     * @return
     */
    public MyArrayList<V> values(){
        MyArrayList<V> vs = new MyArrayList<>();
        for (Node<K, V> node : entrySet()) {
            vs.add(node.val);
        }
        return vs;
    }

    public MyHashSet<K> keySet(){
        MyHashSet<K> hashSet = new MyHashSet<>();
        for (Node<K, V> node : entrySet()) {
            hashSet.add(node.key);
        }
        return hashSet;
    }

    /**
     * �������hash��
     */
    public void clear(){
        if (table != null && table.length > 0) {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
            size = 0;
        }
    }

    /**
     * ����key��ɾ������
     * @param key
     * @return
     */
    public void remove(Object key){
        int index = indexFor(hash(key));
        Node<K, V> oldNode = table[index];
        if (oldNode == null) {
            return;
        } else {
            //�Ӷ�������ɾ���ڵ�
            if (oldNode instanceof Tree){
                Tree<K,V> current = (Tree<K, V>) oldNode;
                current.delete(key);
                //������ת����
                if (current.size < UNTREEIFY_THRESHOLD){
                    transferChainList(current,key.hashCode());
                }
            }else {//�ӵ���������ɾ���ڵ�
                Node<K, V> current = oldNode;
                //����ֻ��һ���ڵ�
                if (current.getNext() == null){
                    table[index] = null;
                }else if (current.key.equals(key)){//ɾ����һ���ڵ�
                    table[index] = current.getNext();
                }else {
                    Node<K, V> n;
                    do {
                        n = current.getNext();
                        if (n == null){//�ڵ㲻����
                            return;
                        }
                        if (n.key.equals(key)){
                            if (n.getNext() != null) {//ɾ���м�ڵ�
                                current.setNext(n.getNext());
                            } else {//ɾ�����һ���ڵ�
                                current.setNext(null);
                            }
                        }
                    }while ((current = current.getNext()) != null);
                }
            }
            size--;
        }
    }

    /**
     * ������ת����
     * @param root
     */
    private void transferChainList(Tree<K, V> root,int hash) {
        MyArrayList<Node<K, V>> nodes = root.getTreeNodes();
        System.out.println("������ת����");
        int index = indexFor(hash);
        table[index] = null;
        for (int i = 0 ; i < nodes.size() ; i ++){
           insertIntoChainList(index,nodes.get(i));
        }
    }

    /**
     * �����������ݲ��뵽������
     * @param index
     * @param n
     */
    private void insertIntoChainList(int index, Node<K, V> n){
        Node<K, V> node = table[index];
        if (node == null){
            table[index] = n;
        }else {
            while (node != null){
                if (node.next == null){
                    node.next = n;
                    break;
                }
                node = node.next;
            }
        }
    }

    /**
     * ��ȡ����Node�ڵ������
     * @return
     */
    public Node<K,V>[] entrySet(){
        Node[] nodes = new Node[size];
        int index = 0;
        for (Node<K, V> node : table) {
            if (node != null){
                if (node instanceof Tree){
                    MyArrayList<Node<K,V>> all = ((Tree<K,V>) node).getTreeNodes();
                    for (int i = 0; i < all.size(); i++) {
                        nodes[index++] = all.get(i);
                    }
                }else {
                    while (node != null){
                        nodes[index++] = node;
                        node = node.next;
                    }
                }
            }
        }
        if (index < size)
            nodes = Arrays.copyOf(nodes,index);
        System.out.println(nodes.length);
        return nodes;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(size + 8);
        builder.append("{");
        String s = "";
        if (entrySet().length > 0) {
            for (Node<K, V> node : entrySet()) {
                if (node != null) {
                    builder.append(node.key);
                    builder.append("=");
                    builder.append(node.val);
                    builder.append(", ");
                }
            }
            s = builder.substring(0, builder.length() - 2) + "}";
        }else {
            s = "{}";
        }
        return s;
    }
}
