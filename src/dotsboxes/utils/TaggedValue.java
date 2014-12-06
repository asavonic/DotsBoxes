package dotsboxes.utils;

import java.io.Serializable;

public class TaggedValue<T, Tag> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2801504424267824887L;
	public TaggedValue(T _value, Tag _tag) {
		value = _value;
		tag = _tag;
	}
	public T value;
	public Tag tag;
}