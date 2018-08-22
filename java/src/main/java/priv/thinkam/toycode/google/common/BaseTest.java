package priv.thinkam.toycode.google.common;

import com.google.common.base.Optional;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author thinkam
 * @date 2018/07/29
 */
public class BaseTest {

	public static void main(String[] args) {
		Optional<Integer> possible = Optional.of(5);
		System.out.println(possible.isPresent());
		System.out.println(possible.get());

		int i = 1;
		int j = 2;
		checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
		checkArgument(i < j, "Expected i < j, but %s >= %s", i, j);

		System.out.println(Objects.equals(null, 1));
		System.out.println(com.google.common.base.Objects.equal(null, 1));
	}

}
