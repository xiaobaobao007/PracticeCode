package Doing.cache;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/1/16，18:08
 */
public interface Cache {

	BaseDao getOne(Object... param);

	void updateOne(BaseDao one);

	void deleteOne(BaseDao one);

}
