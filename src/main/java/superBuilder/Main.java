package superBuilder;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString
class Parent {
	private String parentField;
}

@SuperBuilder
@ToString(callSuper = true)
class Child extends Parent {
	private String childField;
}

public class Main {
	public static void main(String[] args) {
		Child child = Child.builder()
				.childField("child fields")
				.parentField("parent field")
				.build();

		System.out.println(child);

	}
}
