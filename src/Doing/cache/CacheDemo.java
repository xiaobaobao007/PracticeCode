package Doing.cache;

/**
 * @author bao meng yang <932824098@qq.com>
 * @date 2021/1/16，17:41
 */
public class CacheDemo implements Cache {

	@CacheListOperation(key = "123")
	@Override
	public BaseDao getOne(Object... param) {
		return null;
	}

	@Override
	public void updateOne(BaseDao one) {

	}

	@Override
	public void deleteOne(BaseDao one) {

	}

}
