import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaobaobao
 * @date 2020/4/28，19:48
 */
public class Main {
	public static void main(String[] args) {
		List<Map<String, Object>> tsManHourStatisticsResultMaps = new ArrayList<>();
		List<Integer> projectIds = new ArrayList<>();
		Map<String, Object> resultMap = new HashMap<>();
		for (Integer projectId : projectIds) {
			List<Map<String, Object>> resultList = new ArrayList<>();
			for (Map<String, Object> tsManHourStatisticsResultMap : tsManHourStatisticsResultMaps) {
				if (projectId.equals(tsManHourStatisticsResultMap.get("projectId"))) {
					resultList.add(tsManHourStatisticsResultMap);
				}
			}
			resultMap.put(projectId.toString(), resultList);
		}

		projectIds.stream().forEach((a) -> {
			List<Map<String, Object>> resultList = new ArrayList<>();
			tsManHourStatisticsResultMaps.stream().forEach((b) -> {
				if (a.equals(b.get("projectId"))) {
					resultList.add(b);
				}
			});
			resultMap.put(a.toString(), resultList);
		});

	}
}
