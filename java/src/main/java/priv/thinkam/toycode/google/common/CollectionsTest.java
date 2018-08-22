package priv.thinkam.toycode.google.common;

import com.google.common.collect.*;

import java.util.List;
import java.util.Map;

/**
 * @author thinkam
 * @date 2018/07/29
 */
public class CollectionsTest {

	public static void main(String[] args) {
		ImmutableList<String> immutableList = ImmutableList.of("a", "b", "c");
		// immutableList.add("a");
		immutableList.forEach(System.out::print);
		System.out.println();

		Multiset<String> multiset = HashMultiset.create(Lists.newArrayList("a", "b", "a", "c", "d"));
		System.out.println(multiset.count("a"));
		System.out.println(multiset.count("b"));

		List<User> userList = Lists.newArrayList(new User("aaa", "101"),
				new User("bbb", "102"),
				new User("ccc", "101"),
				new User("ddd", "103"));
		ListMultimap<String, User> domainIdUserMap = ArrayListMultimap.create();
		userList.forEach(user -> {
			domainIdUserMap.put(user.getDomaminId(), user);
		});
		System.out.println(domainIdUserMap.get("101"));
		for (Map.Entry<String, User> entry : domainIdUserMap.entries()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}

	}

	private static class User {
		private String username;
		private String domaminId;

		public User(String username, String domaminId) {
			this.username = username;
			this.domaminId = domaminId;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getDomaminId() {
			return domaminId;
		}

		public void setDomaminId(String domaminId) {
			this.domaminId = domaminId;
		}

		@Override
		public String toString() {
			return "User{" +
					"username='" + username + '\'' +
					", domaminId='" + domaminId + '\'' +
					'}';
		}
	}
}
