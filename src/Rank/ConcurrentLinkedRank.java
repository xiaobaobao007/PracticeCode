package Rank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *	特点
 *	1：读写锁分离
 *	2：即使最糟糕的情况发生，代价也很小
 *	3：从大到小排 *	4：能做到一些微操作来避免大范围操作
 *	5：需要保存自己的对象，但是换来了更快的更新速度
 *	数据结构（和hashmap数据结构很像，未使用指针碰撞和红黑树）
 *	1：数组+指针结构
 *	2：对数组进行二分查找
 *	3：数组使用了jdk的ArrayList,并无大碍
 *	4：数组节点下存储链表 以下是与Rankbord2的速度比较
 *	测试方法如下 ========================
 *	开始计时
 *	for (int j = 0; j < 测试N次数; j++) {
 *	新建排序对象
 *	插入M条随机数据
 *	对M条数据随机更新 }
 *	结束计时 =========================
 *	ConcurrentLinkedRank总耗时 : Rankbord2总耗时
 *	N=100，M=1000 125:688
 *	N=100，M=5000 437:26431
 * </pre>
 *
 * @author xiaobaobao
 * @date 2020/8/28，15:15
 */
public class ConcurrentLinkedRank implements Iterable<ConcurrentLinkedRank.Entry> {

	// 一个节点下的链表长度
	private final static int ONE_ENTRY_SIZE_LIMIT = 7;
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

	private EntryIndex getTopIndexByScore(int tail, int score) {
		if (INDEX_TOP == null) {
			EntryIndex entryIndex = new EntryIndex(0);
			(INDEX_TOP = new ArrayList<>()).add(entryIndex);
			return entryIndex;
		}
		int head = 0;
		int mid;
		if (tail == -1) {
			tail = INDEX_TOP.size() - 1;
		}
		if (tail == 0) {
			return INDEX_TOP.get(0);
		} else {
			while (head + 1 < tail) {
				mid = (head + tail) / 2;
				if (INDEX_TOP.get(mid).topEntry.score <= score) {
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
			for (;;) {
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
		 * @param depth
		 *            从1算
		 */
		public Entry getEntryByDepth(int depth) {
			Entry entry = topEntry;
			for (;;) {
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

	class Entry {
		Entry next;
		EntryIndex entryIndex;

		int score;
		int userId;

		public Entry(int score, int userId, EntryIndex entryIndex) {
			this.score = score;
			this.userId = userId;
			this.entryIndex = entryIndex;
		}

	}

	public void bb() {
		int i = Integer.MAX_VALUE;
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

					System.out.printf("%3d%5d%5d|", entry.entryIndex.index, entry.score, entry.userId);
					entry = entry.next;
				}
				System.out.println();
			}
		}
		if (INDEX_TOP != null) {
			System.out.print((INDEX_TOP.size() - 1) + " 校验 ");
		}
		if (p == -2) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} else if (i == -1) {
			System.out.println("==============================================================");
		} else {
			System.out.println("--------------------------------------------------------------");
		}
	}

	/**
	 * 当前节点长度超出进行移动或者调用list进行的底层copy操作
	 */
	private void resetIndex(EntryIndex entryIndex) {
		if (entryIndex.size <= ONE_ENTRY_SIZE_LIMIT) {
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
		for (;;) {
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
	public void addScoreByEntry(Entry entry, int addScore) {
		if (INDEX_TOP == null || entry == null) {
			return;
		}
		entry.score += addScore;
		try {
			WRITE_LOCK.lock();

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
				// TODO 此处待优化
				INDEX_TOP.remove(entry.entryIndex.index);
				for (int i = entry.entryIndex.index; i < INDEX_TOP.size(); i++) {
					INDEX_TOP.get(i).index--;
				}
			} else {
				entry.entryIndex.topEntry = entry.next;
				entry.entryIndex.size--;
				entry.next = null;
			}
			return;
		}
		for (;;) {
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
		READ_LOCK.lock();
		return new EntryIterator();
	}

	private class EntryIterator implements Iterator<Entry> {

		private boolean isFirst = true;
		private Entry entry;

		@Override
		public boolean hasNext() {
			if (isFirst) {
				entry = INDEX_TOP.get(0).topEntry;
				isFirst = false;
			}
			if (entry == null) {
				READ_LOCK.unlock();
				return false;
			}
			return true;
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

	public static void main(String[] args) {
		int testSize = 1000;
		Random random = new Random();

		long now = System.currentTimeMillis();
		ConcurrentLinkedRank rank = new ConcurrentLinkedRank();
		List<Entry> list = new ArrayList<>(testSize);

		for (int i = 0; i < testSize; i++) {
			list.add(rank.add(random.nextInt(testSize), i));
		}
		for (int i = 0; i < testSize; i++) {
			rank.addScoreByEntry(list.get(random.nextInt(testSize)), random.nextInt(testSize));
		}
		now = System.currentTimeMillis() - now;
		rank.bb();
		System.out.println("耗时" + now);
	}

}
