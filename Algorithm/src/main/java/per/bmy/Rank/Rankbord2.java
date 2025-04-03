package per.bmy.Rank;

import java.util.*;
import java.util.Map.Entry;

/**
 * 排行榜,基于Collections.sort
 *
 * @author Administrator
 */
public class Rankbord2 {

    protected List<TopVo> rankData = new ArrayList<>();
    protected Map<Integer, TopVo> rankIdMap = new HashMap<>();

    protected String rankType;

    public Rankbord2() {
    }

    public Rankbord2(String type) {
        this(type, true);
    }

    public Rankbord2(String type, boolean reset) {
        this.rankType = type;
        // if (reset) {
        // this.reset();
        // }
    }

    public String getId() {
        return rankType;
    }

    public synchronized void reset() {
        rankData = new ArrayList<>();
        rankIdMap = new HashMap<>();
    }

    public synchronized void sort() {
        Collections.sort(rankData, Comparator.reverseOrder());
    }

    /**
     * <userId,score>
     *
     * @param userScore
     */
    public void initScore(Map<Integer, Integer> userScore) {
        if (userScore == null || userScore.size() == 0) {
            return;
        }
        synchronized (this) {
            for (Entry<Integer, Integer> entry : userScore.entrySet()) {
                int userId = entry.getKey();
                int score = entry.getValue();
                TopVo topVo = rankIdMap.get(userId);
                if (topVo == null) {
                    topVo = this.onAdd(userId, score);
                } else {
                    topVo.score = score;
                }
            }
            this.sort();
        }
    }

    private synchronized TopVo onAdd(int userId, int score) {
        TopVo topVo = new TopVo(0, userId, score);

        rankData.add(topVo);
        rankIdMap.put(userId, topVo);
        return topVo;
    }

    public int getRankNum() {
        return rankData.size();
    }

    /**
     * 更新替换当前数值
     *
     * @param userId
     * @param score
     */
    public synchronized void updateScore(int userId, int score) {
        TopVo topVo = rankIdMap.get(userId);
        if (topVo == null) {
            topVo = this.onAdd(userId, score);
        } else {
            topVo.score = score;
        }
        this.sort();
    }

    /**
     * 增加当前数值
     *
     * @param userId
     * @param score
     */
    public synchronized void incrbyScore(int userId, int score) {
        TopVo topVo = rankIdMap.get(userId);
        if (topVo == null) {
            topVo = this.onAdd(userId, score);
        } else {
            topVo.score += score;
        }
        this.sort();
    }

    public synchronized void removeMember(int userId) {
        TopVo topVo = rankIdMap.remove(userId);
        if (topVo != null) {
            rankData.remove(topVo);
        }
        this.sort();
    }

    public int getRank(int userId) {
        if (rankIdMap.get(userId) == null) {
            return -1;
        }

        TopVo topVo = null;
        int rank = 0;
        synchronized (this) {
            for (TopVo top : rankData) {
                rank++;
                if (top.userId == userId) {
                    topVo = top;
                    topVo.rank = rank;
                    break;
                }
            }
        }
        if (topVo == null) {
            return -1;
        } else {
            return topVo.rank;
        }
    }

    public TopVo getTopVoByUserId(int userId) {
        if (rankIdMap.get(userId) == null) {
            return null;
        }

        TopVo topVo = null;
        int rank = 0;
        synchronized (this) {
            for (TopVo top : rankData) {
                rank++;
                if (top.userId == userId) {
                    topVo = top;
                    topVo.rank = rank;
                    break;
                }
            }
        }
        return topVo;
    }

    public List<TopVo> getAllTop() {
        return getTop(rankData.size());
    }

    /**
     * 取排行傍几位
     *
     * @param start 起始0
     * @param num   取数量
     * @return
     */
    public List<TopVo> getTop(int start, int num) {

        List<TopVo> topList = new ArrayList<>();

        synchronized (this) {
            int end = start + num;
            if (end > rankData.size()) {
                end = rankData.size();
            }
            for (int i = start; i < end; i++) {
                TopVo top = rankData.get(i);
                top.rank = i + 1;
                topList.add(top);
            }
        }
        return topList;
    }

    public TopVo getTopByRank(int rank) {
        if (rankData.size() < rank || rank < 1) {
            return null;
        }
        synchronized (this) {
            TopVo topVo = rankData.get(rank - 1);
            if (topVo != null) {
                topVo.rank = rank;
                return topVo;
            }
        }
        return null;
    }

    public List<TopVo> getTop(int num) {
        return getTop(0, num);
    }

    public int getScoreByUserId(int userId) {
        TopVo topVo = rankIdMap.get(userId);
        return topVo != null ? (int) topVo.score : 0;
    }

    public static class TopVo implements Comparable<TopVo> {
        public int rank;
        public int userId;
        public int score;

        public TopVo() {
        }

        public TopVo(int rank, int userId, int score) {
            this.rank = rank;
            this.userId = userId;
            this.score = score;
        }

        @Override
        public int compareTo(TopVo o) {
            return (this.score < o.score) ? -1 : ((this.score == o.score) ? 0 : 1);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TopVo) {
                return this.userId == ((TopVo) obj).userId;
            }
            return false;
        }

    }

}
