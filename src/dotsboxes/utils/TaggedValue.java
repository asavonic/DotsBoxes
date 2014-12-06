package dotsboxes.utils;

public class TaggedValue<T, Tag> {
	public TaggedValue(T _value, Tag _tag) {
		value = _value;
		tag = _tag;
	}
	public T value;
	public Tag tag;
}