/**
 * 
 */
package wicket.contrib.jasperreports.paging;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.repeater.AbstractRepeater;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.collections.ReadOnlyIterator;



/**
 * @author  Emmanuel A. Nollase
 * @created  Sep 19, 2008 - 5:13:18 PM
 */
public abstract class JRRepeater extends AbstractRepeater 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Index of the first item to show */
	protected int firstIndex = 0;

	/**
	 * If true, re-rendering the list view is more efficient if the window doesn't get changed at
	 * all or if it gets scrolled (compared to paging). But if you modify the listView model object,
	 * than you must manually call listView.removeAll() in order to rebuild the ListItems. If you
	 * nest a ListView in a Form, ALWAYS set this property to true, as otherwise validation will not
	 * work properly.
	 */
	private boolean reuseItems = false;

	/** Max number (not index) of items to show */
	private int viewSize = Integer.MAX_VALUE;

	/**
	 * @see org.apache.wicket.Component#Component(String)
	 */
	public JRRepeater(final String id)
	{
		super(id);
	}

	/**
	 * @see org.apache.wicket.Component#Component(String, IModel)
	 */
	public JRRepeater(final String id, final IModel model)
	{
		super(id, model);

		if (model == null)
		{
			throw new IllegalArgumentException(
				"Null models are not allowed. If you have no model, you may prefer a Loop instead");
		}

		// A reasonable default for viewSize can not be determined right now,
		// because list items might be added or removed until ListView
		// gets rendered.
	}

	/**
	 * @param id
	 *            See Component
	 * @param list
	 *            List to cast to Serializable
	 * @see org.apache.wicket.Component#Component(String, IModel)
	 */
	public JRRepeater(final String id, final List list)
	{
		this(id, new Model((Serializable)list));
	}

	/**
	 * Gets the list of items in the listView. This method is final because it is not designed to be
	 * overridden. If it were allowed to be overridden, the values returned by getModelObject() and
	 * getList() might not coincide.
	 * 
	 * @return The list of items in this list view.
	 */
	public final List getList()
	{
		final List list = (List)getModelObject();
		if (list == null)
		{
			return Collections.EMPTY_LIST;
		}
		return list;
	}

	/**
	 * If true re-rendering the list view is more efficient if the windows doesn't get changed at all or if it gets scrolled (compared to paging). But if you modify the listView model object, than you must manually call listView.removeAll() in order to rebuild the ListItems. If you nest a ListView in a Form, ALLWAYS set this property to true, as otherwise validation will not work properly.
	 * @return  Whether to reuse items
	 * @uml.property  name="reuseItems"
	 */
	public boolean getReuseItems()
	{
		return reuseItems;
	}

	/**
	 * Get index of first cell in page. Default is: 0.
	 * 
	 * @return Index of first cell in page. Default is: 0
	 */
	public final int getStartIndex()
	{
		return firstIndex;
	}

	/**
	 * Based on the model object's list size, firstIndex and view size, determine what the view size really will be. E.g. default for viewSize is Integer.MAX_VALUE, if not set via setViewSize(). If the underlying list has 10 elements, the value returned by getViewSize() will be 10 if startIndex = 0.
	 * @return  The number of items to be populated and rendered.
	 * @uml.property  name="viewSize"
	 */
	public int getViewSize()
	{
		int size = viewSize;

		final Object modelObject = getModelObject();
		if (modelObject == null)
		{
			return size == Integer.MAX_VALUE ? 0 : size;
		}

		// Adjust view size to model object's list size
		final int modelSize = getList().size();
		if (firstIndex > modelSize)
		{
			return 0;
		}

		if ((size == Integer.MAX_VALUE) || ((firstIndex + size) > modelSize))
		{
			size = modelSize - firstIndex;
		}

		// firstIndex + size must be smaller than Integer.MAX_VALUE
		if ((Integer.MAX_VALUE - size) < firstIndex)
		{
			throw new IllegalStateException(
				"firstIndex + size must be smaller than Integer.MAX_VALUE");
		}

		return size;
	}

	
	/**
	 * Sets the model as the provided list and removes all children, so that the next render will be
	 * using the contents of the model.
	 * 
	 * @param list
	 *            The list for the new model. The list must implement {@link Serializable}.
	 * @return This for chaining
	 */
	public Component setList(List list)
	{
		return setModel(new Model((Serializable)list));
	}

	/**
	 * Sets the model and removes all current children, so that the next render will be using the
	 * contents of the model.
	 * 
	 * @param model
	 *            The new model
	 * @return This for chaining
	 * 
	 * @see org.apache.wicket.MarkupContainer#setModel(org.apache.wicket.model.IModel)
	 */
	public Component setModel(IModel model)
	{
		return super.setModel(model);
	}

	/**
	 * If true re-rendering the list view is more efficient if the windows doesn't get changed at all or if it gets scrolled (compared to paging). But if you modify the listView model object, than you must manually call listView.removeAll() in order to rebuild the ListItems. If you nest a ListView in a Form, ALLWAYS set this property to true, as otherwise validation will not work properly.
	 * @param reuseItems  Whether to reuse the child items.
	 * @return  this
	 * @uml.property  name="reuseItems"
	 */
	public JRRepeater setReuseItems(boolean reuseItems)
	{
		this.reuseItems = reuseItems;
		return this;
	}

	/**
	 * Set the index of the first item to render
	 * 
	 * @param startIndex
	 *            First index of model object's list to display
	 * @return This
	 */
	public JRRepeater setStartIndex(final int startIndex)
	{
		firstIndex = startIndex;

		if (firstIndex < 0)
		{
			firstIndex = 0;
		}
		else if (firstIndex > getList().size())
		{
			firstIndex = 0;
		}

		return this;
	}

	/**
	 * Define the maximum number of items to render. Default: render all.
	 * @param size  Number of items to display
	 * @return  This
	 * @uml.property  name="viewSize"
	 */
	public JRRepeater setViewSize(final int size)
	{
		viewSize = size;

		if (viewSize < 0)
		{
			viewSize = Integer.MAX_VALUE;
		}

		return this;
	}

	/**
	 * Subclasses may provide their own ListItemModel with extended functionality. The default
	 * ListItemModel works fine with mostly static lists where index remains valid. In cases where
	 * the underlying list changes a lot (many users using the application), it may not longer be
	 * appropriate. In that case your own ListItemModel implementation should use an id (e.g. the
	 * database' record id) to identify and load the list item model object.
	 * 
	 * @param listViewModel
	 *            The ListView's model
	 * @param index
	 *            The list item index
	 * @return The ListItemModel created
	 */
	protected IModel getListItemModel(final IModel listViewModel, final int index)
	{
		return new JRModel(this, index);
	}

	/**
	 * Create a new ListItem for list item at index.
	 * 
	 * @param index
	 * @return ListItem
	 */
	protected ListItem newItem(final int index)
	{
		return new ListItem(index, getListItemModel(getModel(), index));
	}

	/**
	 * @see org.apache.wicket.markup.repeater.AbstractRepeater#onPopulate()
	 */
	protected abstract void onPopulate();
	

	/**
	 * Populate a given item.
	 * <p>
	 * <b>be careful</b> to add any components to the list item. So, don't do:
	 * 
	 * <pre>
	 * add(new Label(&quot;foo&quot;, &quot;bar&quot;));
	 * </pre>
	 * 
	 * but:
	 * 
	 * <pre>
	 * item.add(new Label(&quot;foo&quot;, &quot;bar&quot;));
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param item
	 *            The item to populate
	 */
	//protected abstract void populateItem(final ListItem item);

	/**
	 * @see org.apache.wicket.markup.repeater.AbstractRepeater#renderChild(org.apache.wicket.Component)
	 */
	protected final void renderChild(Component child)
	{
		renderItem((ListItem)child);
	}


	/**
	 * Render a single item.
	 * 
	 * @param item
	 *            The item to be rendered
	 */
	protected void renderItem(final ListItem item)
	{
		item.render(getMarkupStream());
	}

	/**
	 * @see org.apache.wicket.markup.repeater.AbstractRepeater#renderIterator()
	 */
	protected Iterator renderIterator()
	{

		final int size = size();
		return new ReadOnlyIterator()
		{
			private int index = 0;

			public boolean hasNext()
			{
				return index < size;
			}

			public Object next()
			{
				final String id = Integer.toString(firstIndex + index);
				index++;
				Component c = get(id);
				return c;
			}
		};
	}

}
