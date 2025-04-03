package per.bmy.Rank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *  使用方法
 * 	1：写锁自动调用，但是读锁需要手动调 {@link #setReadLock()} 获取锁，和{@link #releaseReadLock()} 释放锁
 * 	2：初始化{@link #init(long, int)}，允许插入已排序好的数据，顺序错误则抛 {@link InsertSortError},建议使用sql的desc排序再插入。
 * 	3：支持 Iterable
 * 	4：在调用{@link #init(long, int)}和{@link #add(int, int)}会返回在排序里的对象，需要自己保存，用于快速查找更新
 * 	5：包含两个测试输出  	{@link #sout()}输出score和userId，
 *                          {@link #soutByJudge()} ()}输出当前数组节点下坐标、score和userId。
 * 	特点
 * 	1：读写锁分离
 * 	2：即使最糟糕的情况发生，代价也很小
 * 	3：从大到小排
 * 	4：能做到一些微操作来避免大范围操作
 * 	5：需要保存自己的对象，但是换来了更快的更新速度
 * 	数据结构（和hashmap数据结构很像，未使用指针碰撞和红黑树）
 * 	1：数组+指针结构
 * 	2：对数组进行二分查找
 * 	3：数组使用了jdk的ArrayList,并无大碍
 * 	4：数组节点下存储链表 以下是与Rankbord2的速度比较
 * 	测试方法如下 ========================
 * 	开始计时
 * 	for (int j = 0; j < 测试N次数; j++) {
 * 	新建排序对象
 * 	插入M条随机数据
 * 	对M条数据随机更新 }
 * 	结束计时 =========================
 * 	ConcurrentLinkedRank总耗时 : Rankbord2总耗时(单位毫秒)
 * 	N=100，M=1000 125:688
 * 	N=100，M=5000 437:26431
 * </pre>
 * <p>
 * 包含测试方法 RankTest
 *
 * @author xiaobaobao
 * @date 2020/8/28，15:15
 */
public class ConcurrentLinkedRank implements Iterable<ConcurrentLinkedRank.Entry> {

    // 一个节点下的链表长度
    private final static int ONE_ENTRY_SIZE_LIMIT = 7;
    private final static int INIT_ENTRY_SIZE = 5;
    // 节点长度超出，为避免数组的copy，尝试将节点下的最后一位移动到下个节点的第一位的次数
    private final static int MOVE_ENTRY_TIMES = 3;
    private List<EntryIndex> INDEX_TOP = null;
    // 读写锁分离
    private final ReentrantReadWriteLock.ReadLock READ_LOCK;
    private final ReentrantReadWriteLock.WriteLock WRITE_LOCK;

    public ConcurrentLinkedRank() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        READ_LOCK = readWriteLock.readLock();
        WRITE_LOCK = readWriteLock.writeLock();
    }

    public void setReadLock() {
        READ_LOCK.lock();
    }

    public void releaseReadLock() {
        READ_LOCK.unlock();
    }

    /**
     * 严格按照分数从大到小的排序，插入,否则抛出 InsertSortError
     */
    public Entry init(long score, int userId) {
        if (INDEX_TOP == null) {
            INDEX_TOP = new ArrayList<>();
        }
        int index = INDEX_TOP.size() - 1;
        EntryIndex entryIndex;
        if (index < 0) {
            index = 0;
            entryIndex = new EntryIndex(0);
            INDEX_TOP.add(entryIndex);
        } else {
            entryIndex = INDEX_TOP.get(index);
        }
        if (entryIndex.size >= INIT_ENTRY_SIZE) {
            index++;
            entryIndex = new EntryIndex(index);
            INDEX_TOP.add(entryIndex);
        }
        Entry theLast = entryIndex.getEntryByDepth(INIT_ENTRY_SIZE);
        if (theLast != null && score > theLast.score) {
            throw new InsertSortError(theLast, score);
        }
        Entry addNewWntry = new Entry(score, userId, entryIndex);
        if (entryIndex.topEntry == null) {
            entryIndex.topEntry = addNewWntry;
        } else {
            Entry e = entryIndex.topEntry;
            while (e.next != null) {
                e = e.next;
            }
            e.next = addNewWntry;
        }
        entryIndex.size++;
        return addNewWntry;
    }

    public void clear() {
        try {
            WRITE_LOCK.lock();
            INDEX_TOP = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public Entry add(int score, int userId) {
        Entry addNewEntry = null;
        try {
            WRITE_LOCK.lock();
            EntryIndex entryIndex = getTopIndexByScore(-1, score);
            addNewEntry = new Entry(score, userId, entryIndex);
            addEntryByEntryIndex(entryIndex, addNewEntry);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            WRITE_LOCK.unlock();
        }
        return addNewEntry;
    }

    private EntryIndex getTopIndexByScore(int tail, long score) {
        if (INDEX_TOP == null) {
            EntryIndex entryIndex = new EntryIndex(0);
            (INDEX_TOP = new ArrayList<>()).add(entryIndex);
            return entryIndex;
        }
        int head = 0;
        int mid;
        if (tail < 0) {
            tail = INDEX_TOP.size() - 1;
        }
        if (tail == 0) {
            return INDEX_TOP.get(0);
        } else {
            while (head + 1 < tail) {
                mid = (head + tail) / 2;
                if (INDEX_TOP.get(mid).topEntry.score < score) {
                    tail = mid;
                } else {
                    head = mid;
                }
            }
            if (score <= INDEX_TOP.get(tail).topEntry.score) {
                return INDEX_TOP.get(tail);
            } else {
                return INDEX_TOP.get(head);
            }
        }
    }

    private void addEntryByEntryIndex(EntryIndex entryIndex, Entry addNewEntry) {
        if (entryIndex.topEntry == null) {
            entryIndex.topEntry = addNewEntry;
        } else {
            Entry entry = entryIndex.topEntry;
            for (; ; ) {
                if (entry.next != null) {
                    if (addNewEntry.score > entry.score) {
                        addNewEntry.next = entryIndex.topEntry;
                        entryIndex.topEntry = addNewEntry;
                        break;
                    } else if (addNewEntry.score > entry.next.score) {
                        addNewEntry.next = entry.next;
                        entry.next = addNewEntry;
                        break;
                    }
                } else {
                    if (entryIndex.size == 1) {
                        if (addNewEntry.score > entry.score) {
                            addNewEntry.next = entryIndex.topEntry;
                            entryIndex.topEntry = addNewEntry;
                        } else {
                            entry.next = addNewEntry;
                        }
                    } else {
                        entry.next = addNewEntry;
                    }
                    break;
                }
                entry = entry.next;
            }
        }
        addNewEntry.entryIndex = entryIndex;
        entryIndex.size++;
        resetIndex(entryIndex);
    }

    class EntryIndex {
        int index;
        Entry topEntry;
        int size;

        public EntryIndex(int index) {
            this.index = index;
        }

        /**
         * @param depth 从1算
         */
        public Entry getEntryByDepth(int depth) {
            if (topEntry == null) {
                return null;
            }
            Entry entry = topEntry;
            for (; ; ) {
                if (--depth == 0) {
                    return entry;
                }
                if (entry.next == null) {
                    // 找不到则返回最后一个
                    return entry;
                }
                entry = entry.next;
            }
        }

    }

    public class Entry {
        Entry next;
        EntryIndex entryIndex;

        public long score;
        public int userId;

        public Entry(long score, int userId, EntryIndex entryIndex) {
            this.score = score;
            this.userId = userId;
            this.entryIndex = entryIndex;
        }

        /**
         * 不在排行榜显示的范围内，对排名进行估算
         * 注意此接口一定返回的是模拟排名
         */
        public int getMyFakeRank(int minRank, int minRankIndex) {
            if (INDEX_TOP == null) {
                return 0;
            }
            return (int) (minRank + 1 + (entryIndex.index - minRankIndex) * ONE_ENTRY_SIZE_LIMIT + (entryIndex.topEntry.score - score) % ONE_ENTRY_SIZE_LIMIT);
        }

        public int getEntryIndex() {
            return entryIndex.index;
        }

        @Override
        public String toString() {
            return "Entry{" + "entryIndex=" + (entryIndex == null ? -1 : entryIndex.index) + ", score=" + score + ", userId=" + userId + '}';
        }
    }

    public void soutByJudge() {
        long i = Integer.MAX_VALUE;
        int j = -1;
        int p = 0;
        if (INDEX_TOP != null) {
            Entry entry;
            for (EntryIndex index : INDEX_TOP) {
                entry = index.topEntry;
                p = -1;
                while (entry != null) {
                    if (p == -1) {
                        p = entry.entryIndex.index;
                    } else if (p != entry.entryIndex.index) {
                        p = -2;
                    }
                    if (entry.score > i) {
                        i = -1;
                    } else {
                        i = entry.score;
                    }
                    if (entry.userId == j) {
                        throw new IndexOutOfBoundsException();
                    }
                    j = entry.userId;

                    System.out.printf("%2d  %5d  %5d|", entry.entryIndex.index, entry.score, entry.userId);
                    entry = entry.next;
                }
                System.out.println();
            }
        }
        if (INDEX_TOP != null) {
            System.out.print("最大index " + (INDEX_TOP.size() - 1));
        }
        if (p == -2) {
            System.out.println(" +++++++++++++++++++++++++++index排序错误++++++++++++++++++++++++++++");
        } else if (i == -1) {
            System.out.println(" ==============================排序异常==============================");
        } else {
            System.out.println(" -------------------------------无异常-------------------------------");
        }
    }

    public void sout() {
        sout(-1);
    }

    public void sout(int rank) {
        if (INDEX_TOP != null) {
            Entry entry;
            _CIRCLE_LABEL:
            for (EntryIndex index : INDEX_TOP) {
                entry = index.topEntry;
                while (entry != null) {
                    System.out.printf("%5d  %5d|", entry.score, entry.userId);
                    if (rank != -1 && rank-- <= 0) {
                        System.out.println();
                        break _CIRCLE_LABEL;
                    }
                    entry = entry.next;
                }
                System.out.println();
            }
        }
        System.out.println("--------------------------------------------------------------");
    }

    /**
     * 当前节点长度超出进行移动或者调用list进行的底层copy操作
     */
    private void resetIndex(EntryIndex entryIndex) {
        if (INDEX_TOP == null || entryIndex.size <= ONE_ENTRY_SIZE_LIMIT) {
            return;
        }
        for (int times = MOVE_ENTRY_TIMES; times > 0; times--) {// 尝试进行微操作
            if (entryIndex.index + 1 < INDEX_TOP.size()) {
                Entry theSecondLast = entryIndex.getEntryByDepth(ONE_ENTRY_SIZE_LIMIT);
                Entry theFirstLast = theSecondLast.next;
                theSecondLast.next = null;
                entryIndex.size--;

                entryIndex = INDEX_TOP.get(entryIndex.index + 1);
                theFirstLast.next = entryIndex.topEntry;
                theFirstLast.entryIndex = entryIndex;
                entryIndex.topEntry = theFirstLast;
                if (++entryIndex.size <= ONE_ENTRY_SIZE_LIMIT) {
                    return;
                }
            } else {
                break;
            }
        }
        // 从正中间分开
        int index = ONE_ENTRY_SIZE_LIMIT / 2 + 1;
        Entry entry = entryIndex.getEntryByDepth(index);
        EntryIndex newEntryIndex = new EntryIndex(entryIndex.index + 1);
        // 4-0
        newEntryIndex.topEntry = entry.next;
        // 8-5
        entry.next = null;
        newEntryIndex.size = entryIndex.size - index;
        entryIndex.size = index;
        for (int i = entryIndex.index + 1; i < INDEX_TOP.size(); i++) {
            INDEX_TOP.get(i).index++;
        }
        INDEX_TOP.add(newEntryIndex.index, newEntryIndex);
        entry = newEntryIndex.topEntry;
        for (; ; ) {
            if (entry == null) {
                return;
            }
            entry.entryIndex = newEntryIndex;
            entry = entry.next;
        }
    }

    /**
     * 对已存在的节点进行更新
     */
    public void addScoreByEntry(Entry entry, long addScore) {
        if (INDEX_TOP == null || entry == null) {
            return;
        }
        try {
            WRITE_LOCK.lock();

            entry.score += addScore;
            EntryIndex entryIndex;
            if (entry.entryIndex.index == 0 || (entry.entryIndex.topEntry != entry && entry.entryIndex.topEntry.score >= entry.score)) {
                if (entry.entryIndex.topEntry == entry) {
                    return;
                } else {
                    entryIndex = entry.entryIndex;
                }
            } else {
                entryIndex = getTopIndexByScore(entry.entryIndex.index - 1, entry.score);
            }
            removeEntry(entry);
            addEntryByEntryIndex(entryIndex, entry);
        } catch (Exception e) {
            entry.score -= addScore;
            e.printStackTrace();
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    private void removeEntry(Entry entry) {
        Entry e = entry.entryIndex.topEntry;
        if (e == entry) {
            // 当前节点删除
            if (entry.next == null) {
                EntryIndex entryIndex = entry.entryIndex;
                Entry topEntry;
                if (entryIndex.index > 0 && (topEntry = INDEX_TOP.get(e.entryIndex.index - 1).topEntry).next != null) {
                    topEntry = topEntry.entryIndex.getEntryByDepth(ONE_ENTRY_SIZE_LIMIT);
                    removeEntry(topEntry);
                    entryIndex.topEntry = topEntry;
                    topEntry.entryIndex = entryIndex;
                } else if (entry.entryIndex.index + 1 < INDEX_TOP.size() && (topEntry = INDEX_TOP.get(e.entryIndex.index + 1).topEntry).next != null) {
                    topEntry = topEntry.entryIndex.getEntryByDepth(ONE_ENTRY_SIZE_LIMIT);
                    removeEntry(topEntry);
                    entryIndex.topEntry = topEntry;
                    topEntry.entryIndex = entryIndex;
                } else {
                    INDEX_TOP.remove(entry.entryIndex.index);
                    for (int i = entry.entryIndex.index; i < INDEX_TOP.size(); i++) {
                        INDEX_TOP.get(i).index--;
                    }
                }
            } else {
                entry.entryIndex.topEntry = entry.next;
                entry.entryIndex.size--;
                entry.next = null;
            }
            return;
        }
        for (; ; ) {
            if (e.next == null) {
                break;
            }
            if (e.next == entry) {
                if (e.next.next != null) {
                    e.next = e.next.next;
                } else {
                    e.next = null;
                }
                entry.entryIndex.size--;
                entry.next = null;
                break;
            }
            e = e.next;
        }
    }

    @SuppressWarnings("ALL")
    @Override
    public Iterator iterator() {
        return new EntryIterator();
    }

    private class EntryIterator implements Iterator<Entry> {

        private boolean isFirst = true;
        private Entry entry;

        @Override
        public boolean hasNext() {
            if (isFirst) {
                if (INDEX_TOP == null) {
                    return false;
                }
                if (INDEX_TOP.size() == 0) {
                    return false;
                }
                entry = INDEX_TOP.get(0).topEntry;
                isFirst = false;
            }
            return entry != null;
        }

        @Override
        public Entry next() {
            Entry e = entry;
            if (entry.next == null) {
                if (entry.entryIndex.index + 1 < INDEX_TOP.size()) {
                    entry = INDEX_TOP.get(entry.entryIndex.index + 1).topEntry;
                } else {
                    entry = null;
                }
            } else {
                entry = entry.next;
            }
            return e;
        }
    }

    public List<Entry> getRankList(int topRank) {
        if (INDEX_TOP == null) {
            return null;
        }
        List<Entry> list = new ArrayList<>(topRank < 0 ? INDEX_TOP.size() * INIT_ENTRY_SIZE : topRank);
        try {
            READ_LOCK.lock();
            int index = 0;
            EntryIndex entryIndex;
            Entry entry;
            for (; ; ) {
                entryIndex = INDEX_TOP.get(index++);
                if (entryIndex == null) {
                    break;
                }
                entry = entryIndex.topEntry;
                while (entry != null) {
                    // System.out.println(entry.get);
                    list.add(entry);
                    if (topRank != -1 && --topRank <= 0) {
                        break;
                    }
                    entry = entry.next;
                }
                if (index >= INDEX_TOP.size()) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            READ_LOCK.unlock();
        }
        return list;
    }

    class InsertSortError extends RuntimeException {
        public InsertSortError(Entry entry, long score) {
            super("the last one is " + entry.toString() + " but you add score is " + score);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int testSize = 20;
        ConcurrentLinkedRank rank = new ConcurrentLinkedRank();
        for (int i = 0; i < testSize; i++) {
            rank.add(random.nextInt(testSize), i);
        }
        rank.soutByJudge();
    }
}
