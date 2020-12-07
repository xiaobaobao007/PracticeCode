import com.alibaba.fastjson.JSONObject;

import org.junit.Test;

public class Main {

	class Fight {
		private long heroId;//被攻击方的英雄id
		private boolean isDead = true;// 0未死，1死亡
		private boolean crit;// 是否暴击
		private boolean miss;// 是否miss
		private float hp;//被攻击方剩余的血量
		private float spSub;//血量增加正值，减少负值
		private int[][] xy;//移动


		public long getHeroId() {
			return heroId;
		}

		public void setHeroId(long heroId) {
			this.heroId = heroId;
		}

		public boolean isDead() {
			return isDead;
		}

		public void setDead(boolean dead) {
			isDead = dead;
		}

		public boolean isCrit() {
			return crit;
		}

		public void setCrit(boolean crit) {
			this.crit = crit;
		}

		public boolean isMiss() {
			return miss;
		}

		public void setMiss(boolean miss) {
			this.miss = miss;
		}

		public float getHp() {
			return hp;
		}

		public void setHp(float hp) {
			this.hp = hp;
		}

		public float getSpSub() {
			return spSub;
		}

		public void setSpSub(float spSub) {
			this.spSub = spSub;
		}

		public int[][] getXy() {
			return xy;
		}

		public void setXy(int[][] xy) {
			this.xy = xy;
		}
	}

	@Test
	public void main() {
		System.out.println(JSONObject.toJSONString(new Fight()));
	}

}