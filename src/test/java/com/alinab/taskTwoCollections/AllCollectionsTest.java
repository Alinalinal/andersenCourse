package com.alinab.taskTwoCollections;

import com.alinab.taskTwoCollections.arrayList.MySimpleArrayListTest;
import com.alinab.taskTwoCollections.hashMap.MySimpleHashMapTest;
import com.alinab.taskTwoCollections.linkedList.MySimpleLinkedListTest;
import com.alinab.taskTwoCollections.queue.MySimpleQueueTest;
import com.alinab.taskTwoCollections.treeMap.MySimpleTreeMapTest;
import com.alinab.taskTwoCollections.treeSet.MySimpleTreeSetTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MySimpleArrayListTest.class,
        MySimpleHashMapTest.class,
        MySimpleLinkedListTest.class,
        MySimpleQueueTest.class,
        MySimpleTreeMapTest.class,
        MySimpleTreeSetTest.class
})
public class AllCollectionsTest {
}
