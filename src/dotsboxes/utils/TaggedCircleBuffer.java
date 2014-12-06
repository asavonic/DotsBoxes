package dotsboxes.utils;

import java.util.Vector;

import dotsboxes.players.PlayerDesc;

public class TaggedCircleBuffer<T, Tag> extends CircleBuffer< TaggedValue<T, Tag> > {

	/**
	 * 
	 */
	private static final long serialVersionUID = -403718400409546999L;
	
	private Vector< TaggedValue<T, Tag> > create_tagged_buffer(Iterable<T> buffer, Tag tag)
	{
		Vector< TaggedValue<T, Tag> > tagged_buffer = new Vector< TaggedValue<T, Tag> >();
		for ( T value : buffer ) {
			tagged_buffer.add( new TaggedValue<T, Tag>(value, tag) );
		}
		
		return tagged_buffer;
	}
	
	public TaggedCircleBuffer(Iterable<T> buffer, int indx_begin, Tag tag) {
		setBuffer( create_tagged_buffer(buffer, tag), indx_begin);
	}

	public TaggedCircleBuffer(Iterable<T> buffer, Tag tag) {
		setBuffer( create_tagged_buffer(buffer, tag) );
	}
	
	public TaggedCircleBuffer() {
		super();
	}

	public void append( TaggedCircleBuffer<T, Tag> buffer)
	{
		append( buffer.getBuffer() );
	}

	public TaggedCircleBuffer<T, Tag> clone() throws CloneNotSupportedException
	{
		return (TaggedCircleBuffer<T, Tag>) super.clone();
	}
}
